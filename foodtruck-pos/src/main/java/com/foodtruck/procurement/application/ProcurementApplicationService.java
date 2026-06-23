package com.foodtruck.procurement.application;

import com.foodtruck.procurement.domain.RestockRequest;
import com.foodtruck.procurement.domain.RestockRequestItem;
import com.foodtruck.procurement.domain.RestockRequestRepository;
import com.foodtruck.procurement.domain.RestockRequestStatus;
import com.foodtruck.procurement.domain.TransportOrder;
import com.foodtruck.procurement.domain.TransportOrderRepository;
import com.foodtruck.referencedata.domain.Article;
import com.foodtruck.referencedata.domain.ArticleRepository;
import com.foodtruck.referencedata.domain.Truck;
import com.foodtruck.referencedata.domain.TruckRepository;
import com.foodtruck.shared.event.StockBelowThresholdEvent;
import com.foodtruck.shared.event.StockReplenishedEvent;
import com.foodtruck.shared.web.ConflictException;
import com.foodtruck.shared.web.NotFoundException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Anwendungsfall-Orchestrierung des Procurement-Kontexts.
 * Reagiert auf Inventory-Events, fuehrt RestockRequests zusammen und erzeugt TransportOrders.
 */
@Service
public class ProcurementApplicationService {

    private static final String EXTERNAL_SYSTEM = "MultiRoute Tour!";

    private final RestockRequestRepository restockRequestRepository;
    private final TransportOrderRepository transportOrderRepository;
    private final ArticleRepository articleRepository;
    private final TruckRepository truckRepository;
    private final MultiRouteTourGateway multiRouteTourGateway;

    public ProcurementApplicationService(RestockRequestRepository restockRequestRepository,
                                         TransportOrderRepository transportOrderRepository,
                                         ArticleRepository articleRepository,
                                         TruckRepository truckRepository,
                                         MultiRouteTourGateway multiRouteTourGateway) {
        this.restockRequestRepository = restockRequestRepository;
        this.transportOrderRepository = transportOrderRepository;
        this.articleRepository = articleRepository;
        this.truckRepository = truckRepository;
        this.multiRouteTourGateway = multiRouteTourGateway;
    }

    /**
     * Reagiert auf Meldebestand-Unterschreitung. Es wird pro Truck hoechstens EIN offener Request gefuehrt
     * (Business Rule 1: kein Doppel-Nachschub) und in diesen alle Positionen gesammelt (Business Rule 2).
     */
    @EventListener
    @Transactional
    public void onStockBelowThreshold(StockBelowThresholdEvent event) {
        RestockRequest request = restockRequestRepository
                .findFirstByTruckIdAndStatus(event.truckId(), RestockRequestStatus.OPEN)
                .orElseGet(() -> new RestockRequest(UUID.randomUUID().toString(), event.truckId()));

        request.upsertItem(event.articleId(), event.requiredQuantity());

        // Business Rule 3: Hauptzutat auf 0 -> Notfall-Lieferung.
        if (event.outOfStock() && isMainIngredient(event.articleId())) {
            request.escalateToEmergency();
        }

        restockRequestRepository.save(request);
    }

    /** Schliesst die laufende Lieferung ab, sobald wieder aufgefuellt wurde. */
    @EventListener
    @Transactional
    public void onStockReplenished(StockReplenishedEvent event) {
        restockRequestRepository
                .findFirstByTruckIdAndStatus(event.truckId(), RestockRequestStatus.TRANSPORT_REQUESTED)
                .ifPresent(request -> {
                    request.markFulfilled();
                    restockRequestRepository.save(request);
                    transportOrderRepository.findFirstByRestockRequestId(request.getId())
                            .ifPresent(order -> {
                                order.markDelivered();
                                transportOrderRepository.save(order);
                            });
                });
    }

    @Transactional(readOnly = true)
    public List<RestockRequest> findRequestsByStatus(RestockRequestStatus status) {
        return restockRequestRepository.findByStatus(status);
    }

    /**
     * Erstellt aus einem offenen RestockRequest einen TransportOrder und uebergibt ihn an MultiRoute Tour!.
     * In der Demo wird dieser Schritt bewusst explizit ausgeloest (siehe Doku 2.4).
     */
    @Transactional
    public TransportOrder createTransportOrder(String restockRequestId) {
        RestockRequest request = restockRequestRepository.findById(restockRequestId)
                .orElseThrow(() -> new NotFoundException(
                        "RestockRequest '%s' nicht gefunden".formatted(restockRequestId)));

        if (!request.isOpen()) {
            throw new ConflictException(
                    "RestockRequest '%s' ist nicht im Status OPEN (aktuell: %s)"
                            .formatted(restockRequestId, request.getStatus()));
        }

        Truck truck = truckRepository.findById(request.getTruckId())
                .orElseThrow(() -> new NotFoundException(
                        "Truck '%s' nicht gefunden".formatted(request.getTruckId())));

        TransportOrder order = new TransportOrder(
                UUID.randomUUID().toString(), request.getId(), request.getTruckId(), EXTERNAL_SYSTEM);

        List<MultiRouteDispatchCommand.Item> commandItems = request.getItems().stream()
                .map(this::toCommandItem)
                .toList();
        MultiRouteDispatchCommand command = new MultiRouteDispatchCommand(
                truck.getId(), truck.getCurrentLatitude(), truck.getCurrentLongitude(),
                request.getPriority().name(), commandItems);

        try {
            MultiRouteDispatchResult result = multiRouteTourGateway.dispatch(command);
            order.markSentToMultiRoute(result.externalOrderId());
            order.markPlanned(result.plannedDeliveryTime());
        } catch (RuntimeException ex) {
            order.markFailed();
        }
        transportOrderRepository.save(order);

        request.markTransportRequested();
        restockRequestRepository.save(request);

        return order;
    }

    @Transactional(readOnly = true)
    public TransportOrder getTransportOrder(String transportOrderId) {
        return transportOrderRepository.findById(transportOrderId)
                .orElseThrow(() -> new NotFoundException(
                        "TransportOrder '%s' nicht gefunden".formatted(transportOrderId)));
    }

    private MultiRouteDispatchCommand.Item toCommandItem(RestockRequestItem item) {
        return new MultiRouteDispatchCommand.Item(item.getArticleId(), item.getRequiredQuantity());
    }

    private boolean isMainIngredient(String articleId) {
        return articleRepository.findById(articleId)
                .map(Article::isMainIngredient)
                .orElse(false);
    }
}
