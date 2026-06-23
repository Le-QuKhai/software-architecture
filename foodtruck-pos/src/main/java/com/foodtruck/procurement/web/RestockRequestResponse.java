package com.foodtruck.procurement.web;

import com.foodtruck.procurement.domain.RestockRequest;

import java.time.Instant;
import java.util.List;

public record RestockRequestResponse(
        String restockRequestId,
        String truckId,
        String status,
        String priority,
        Instant createdAt,
        List<Item> items) {

    public record Item(String articleId, int requiredQuantity) {
    }

    public static RestockRequestResponse from(RestockRequest request) {
        List<Item> items = request.getItems().stream()
                .map(i -> new Item(i.getArticleId(), i.getRequiredQuantity()))
                .toList();
        return new RestockRequestResponse(
                request.getId(),
                request.getTruckId(),
                request.getStatus().name(),
                request.getPriority().name(),
                request.getCreatedAt(),
                items);
    }
}
