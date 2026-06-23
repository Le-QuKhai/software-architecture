package com.foodtruck.procurement.infrastructure;

import com.foodtruck.procurement.application.MultiRouteDispatchCommand;
import com.foodtruck.procurement.application.MultiRouteDispatchResult;
import com.foodtruck.procurement.application.MultiRouteTourGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

/**
 * Adapter (Anti-Corruption-Layer) zum externen System "MultiRoute Tour!".
 * Uebersetzt unseren neutralen DispatchCommand in das (hier simulierte) externe Auftragsformat.
 * Ein echter Adapter wuerde z. B. einen REST-/Message-Client kapseln.
 */
@Component
public class MultiRouteTourAdapter implements MultiRouteTourGateway {

    private static final Logger log = LoggerFactory.getLogger(MultiRouteTourAdapter.class);

    @Override
    public MultiRouteDispatchResult dispatch(MultiRouteDispatchCommand command) {
        // Hier wuerde die Uebersetzung in das MultiRoute-Auftragsformat und der externe Aufruf stattfinden.
        log.info("Uebergebe Transportauftrag an {}: truck={}, prioritaet={}, pos=({},{}), positionen={}",
                "MultiRoute Tour!", command.truckId(), command.priority(),
                command.latitude(), command.longitude(), command.items().size());

        String externalOrderId = "MR-" + UUID.randomUUID();
        Instant plannedDeliveryTime = Instant.now().plus(Duration.ofHours(2));
        return new MultiRouteDispatchResult(externalOrderId, plannedDeliveryTime);
    }
}
