package com.foodtruck.inventory.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.Instant;

/**
 * Aggregate Root des Inventory-Kontexts. Beschreibt den aktuellen Bestand eines Artikels in einem Truck
 * und kapselt die fachlichen Regeln rund um Meldebestand und Nachschubmenge.
 */
@Entity
@Table(name = "inventory_item",
        uniqueConstraints = @UniqueConstraint(name = "uq_inventory_truck_article",
                columnNames = {"truck_id", "article_id"}))
public class InventoryItem {

    @Id
    private String id;

    @Column(name = "truck_id", nullable = false)
    private String truckId;

    @Column(name = "article_id", nullable = false)
    private String articleId;

    @Column(name = "current_quantity", nullable = false)
    private int currentQuantity;

    @Column(name = "reorder_threshold", nullable = false)
    private int reorderThreshold;

    @Column(name = "target_quantity", nullable = false)
    private int targetQuantity;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected InventoryItem() {
    }

    public InventoryItem(String id, String truckId, String articleId,
                         int currentQuantity, int reorderThreshold, int targetQuantity) {
        this.id = id;
        this.truckId = truckId;
        this.articleId = articleId;
        this.currentQuantity = currentQuantity;
        this.reorderThreshold = reorderThreshold;
        this.targetQuantity = targetQuantity;
        this.updatedAt = Instant.now();
    }

    /** Wendet eine Bestandsaenderung an. Der physische Bestand wird nicht negativ. */
    public void applyDelta(int quantityDelta, Instant occurredAt) {
        this.currentQuantity = Math.max(0, this.currentQuantity + quantityDelta);
        this.updatedAt = (occurredAt != null) ? occurredAt : Instant.now();
    }

    /** Meldebestand unterschritten (Business Rule: "Unterschreitet eine Zutat den Meldebestand"). */
    public boolean isBelowThreshold() {
        return currentQuantity < reorderThreshold;
    }

    public boolean isOutOfStock() {
        return currentQuantity <= 0;
    }

    /** Benoetigte Menge, um wieder bis zum Zielbestand aufzufuellen. */
    public int requiredRefillQuantity() {
        return Math.max(0, targetQuantity - currentQuantity);
    }

    public InventoryStatus status() {
        if (currentQuantity <= 0) {
            return InventoryStatus.OUT_OF_STOCK;
        }
        if (currentQuantity < reorderThreshold) {
            return InventoryStatus.LOW_STOCK;
        }
        return InventoryStatus.OK;
    }

    public String getId() {
        return id;
    }

    public String getTruckId() {
        return truckId;
    }

    public String getArticleId() {
        return articleId;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public int getReorderThreshold() {
        return reorderThreshold;
    }

    public int getTargetQuantity() {
        return targetQuantity;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
