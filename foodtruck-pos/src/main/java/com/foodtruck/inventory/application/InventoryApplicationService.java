package com.foodtruck.inventory.application;

import com.foodtruck.inventory.domain.InventoryItem;
import com.foodtruck.inventory.domain.InventoryItemRepository;
import com.foodtruck.inventory.domain.StockChange;
import com.foodtruck.inventory.domain.StockChangeRepository;
import com.foodtruck.shared.event.StockBelowThresholdEvent;
import com.foodtruck.shared.event.StockReplenishedEvent;
import com.foodtruck.shared.web.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Anwendungsfall-Orchestrierung des Inventory-Kontexts.
 * Verantwortlich fuer Bestandsfuehrung, Idempotenz und das Ausloesen von Nachschub-Events.
 */
@Service
public class InventoryApplicationService {

    private final InventoryItemRepository inventoryItemRepository;
    private final StockChangeRepository stockChangeRepository;
    private final ApplicationEventPublisher eventPublisher;

    public InventoryApplicationService(InventoryItemRepository inventoryItemRepository,
                                       StockChangeRepository stockChangeRepository,
                                       ApplicationEventPublisher eventPublisher) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.stockChangeRepository = stockChangeRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void applyStockChange(StockChangeCommand command) {
        // Idempotenz: identische Nachricht (z. B. durch Retry) nur einmal verbuchen.
        if (stockChangeRepository.existsByMessageId(command.messageId())) {
            return;
        }

        InventoryItem item = inventoryItemRepository
                .findByTruckIdAndArticleId(command.truckId(), command.articleId())
                .orElseThrow(() -> new NotFoundException(
                        "Kein InventoryItem fuer Truck '%s' und Artikel '%s'"
                                .formatted(command.truckId(), command.articleId())));

        boolean wasBelowThreshold = item.isBelowThreshold();

        item.applyDelta(command.quantityDelta(), command.occurredAt());
        inventoryItemRepository.save(item);

        StockChange change = new StockChange(
                UUID.randomUUID().toString(),
                command.messageId(),
                command.sourceContext(),
                command.sourceReferenceId(),
                command.reversesMessageId(),
                command.truckId(),
                command.articleId(),
                command.changeType(),
                command.quantityDelta(),
                command.occurredAt());
        stockChangeRepository.save(change);

        if (item.isBelowThreshold()) {
            // Bei jeder Aenderung unterhalb des Meldebestands den (ggf. aktualisierten) Bedarf melden.
            eventPublisher.publishEvent(new StockBelowThresholdEvent(
                    item.getTruckId(), item.getArticleId(),
                    item.requiredRefillQuantity(), item.isOutOfStock()));
        } else if (wasBelowThreshold) {
            // Wieder ueber dem Meldebestand -> laufende Lieferung kann abgeschlossen werden.
            eventPublisher.publishEvent(new StockReplenishedEvent(item.getTruckId(), item.getArticleId()));
        }
    }

    @Transactional(readOnly = true)
    public List<InventoryItem> getTruckInventory(String truckId) {
        return inventoryItemRepository.findByTruckId(truckId);
    }
}
