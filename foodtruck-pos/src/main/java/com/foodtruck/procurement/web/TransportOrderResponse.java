package com.foodtruck.procurement.web;

import com.foodtruck.procurement.domain.TransportOrder;

import java.time.Instant;

public record TransportOrderResponse(
        String transportOrderId,
        String restockRequestId,
        String truckId,
        String externalSystem,
        String externalOrderId,
        String status,
        Instant createdAt,
        Instant plannedDeliveryTime) {

    public static TransportOrderResponse from(TransportOrder order) {
        return new TransportOrderResponse(
                order.getId(),
                order.getRestockRequestId(),
                order.getTruckId(),
                order.getExternalSystem(),
                order.getExternalOrderId(),
                order.getStatus().name(),
                order.getCreatedAt(),
                order.getPlannedDeliveryTime());
    }
}
