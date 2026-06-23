package com.foodtruck.inventory.web;

import java.util.List;

public record TruckInventoryResponse(
        String truckId,
        List<InventoryItemView> items) {
}
