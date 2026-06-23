package com.foodtruck.inventory.web;

import com.foodtruck.inventory.domain.ChangeType;

import java.time.Instant;

/** Web-DTO fuer POST /api/v1/inventory/stock-changes (entspricht dem JSON aus der Doku). */
public record StockChangeRequest(
        String messageId,
        String sourceContext,
        String sourceReferenceId,
        String reversesMessageId,
        String truckId,
        String articleId,
        ChangeType changeType,
        int quantityDelta,
        Instant occurredAt) {
}
