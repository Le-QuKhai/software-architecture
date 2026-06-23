package com.foodtruck.inventory.application;

import com.foodtruck.inventory.domain.ChangeType;

import java.time.Instant;

/** Eingangs-Command der Anwendungsschicht (entkoppelt von der Web-DTO). */
public record StockChangeCommand(
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
