package com.foodtruck.shared.event;

/**
 * Integrations-Event: Ein Artikel wurde wieder bis ueber den Meldebestand aufgefuellt
 * (typischerweise nach einem RESTOCK). Schliesst im Procurement-Kontext die laufende Lieferung ab.
 */
public record StockReplenishedEvent(
        String truckId,
        String articleId) {
}
