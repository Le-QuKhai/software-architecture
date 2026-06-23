package com.foodtruck.inventory.web;

public record InventoryItemView(
        String articleId,
        int currentQuantity,
        int reorderThreshold,
        int targetQuantity,
        String status) {
}
