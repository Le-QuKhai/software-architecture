package com.foodtruck.inventory.web;

import com.foodtruck.inventory.application.InventoryApplicationService;
import com.foodtruck.inventory.application.StockChangeCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

    private final InventoryApplicationService inventoryService;

    public InventoryController(InventoryApplicationService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/inventory/stock-changes")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void reportStockChange(@RequestBody StockChangeRequest request) {
        inventoryService.applyStockChange(new StockChangeCommand(
                request.messageId(),
                request.sourceContext(),
                request.sourceReferenceId(),
                request.reversesMessageId(),
                request.truckId(),
                request.articleId(),
                request.changeType(),
                request.quantityDelta(),
                request.occurredAt()));
    }

    @GetMapping("/trucks/{truckId}/inventory")
    public TruckInventoryResponse getTruckInventory(@PathVariable String truckId) {
        List<InventoryItemView> items = inventoryService.getTruckInventory(truckId).stream()
                .map(item -> new InventoryItemView(
                        item.getArticleId(),
                        item.getCurrentQuantity(),
                        item.getReorderThreshold(),
                        item.getTargetQuantity(),
                        item.status().name()))
                .toList();
        return new TruckInventoryResponse(truckId, items);
    }
}
