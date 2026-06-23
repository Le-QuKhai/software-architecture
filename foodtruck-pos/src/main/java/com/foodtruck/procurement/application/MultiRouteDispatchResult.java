package com.foodtruck.procurement.application;

import java.time.Instant;

public record MultiRouteDispatchResult(
        String externalOrderId,
        Instant plannedDeliveryTime) {
}
