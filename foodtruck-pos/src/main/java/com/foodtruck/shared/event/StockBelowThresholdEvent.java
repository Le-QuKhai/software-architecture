package com.foodtruck.shared.event;

/**
 * Integrations-Event: Der Bestand eines Artikels in einem Truck liegt unter dem Meldebestand.
 * Wird vom Inventory-Kontext veroeffentlicht und vom Procurement-Kontext konsumiert.
 * Liegt bewusst im shared-Paket, damit kein Kontext die internen Domaenenobjekte des anderen kennt.
 */
public record StockBelowThresholdEvent(
        String truckId,
        String articleId,
        int requiredQuantity,
        boolean outOfStock) {
}
