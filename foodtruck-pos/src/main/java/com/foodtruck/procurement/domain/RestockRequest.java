package com.foodtruck.procurement.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Aggregate Root des Procurement-Kontexts. Buendelt den Nachschubbedarf eines Trucks.
 * Ein offener Request wirkt als "Sammelkorb" und realisiert so die Sammelbestellung (Business Rule 2),
 * ohne dass fuer jede einzelne kritische Zutat ein eigener Auftrag entsteht.
 */
@Entity
@Table(name = "restock_request")
public class RestockRequest {

    @Id
    private String id;

    @Column(name = "truck_id", nullable = false)
    private String truckId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestockRequestStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestockPriority priority;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "restockRequest", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private List<RestockRequestItem> items = new ArrayList<>();

    protected RestockRequest() {
    }

    public RestockRequest(String id, String truckId) {
        this.id = id;
        this.truckId = truckId;
        this.status = RestockRequestStatus.OPEN;
        this.priority = RestockPriority.NORMAL;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    /** Fuegt eine Position hinzu oder aktualisiert die Menge einer bestehenden Position. */
    public void upsertItem(String articleId, int requiredQuantity) {
        for (RestockRequestItem item : items) {
            if (item.getArticleId().equals(articleId)) {
                item.setRequiredQuantity(requiredQuantity);
                touch();
                return;
            }
        }
        items.add(new RestockRequestItem(UUID.randomUUID().toString(), this, articleId, requiredQuantity));
        touch();
    }

    public void escalateToEmergency() {
        if (this.priority != RestockPriority.EMERGENCY) {
            this.priority = RestockPriority.EMERGENCY;
            touch();
        }
    }

    public void markTransportRequested() {
        this.status = RestockRequestStatus.TRANSPORT_REQUESTED;
        touch();
    }

    public void markFulfilled() {
        this.status = RestockRequestStatus.FULFILLED;
        touch();
    }

    public boolean isOpen() {
        return this.status == RestockRequestStatus.OPEN;
    }

    private void touch() {
        this.updatedAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getTruckId() {
        return truckId;
    }

    public RestockRequestStatus getStatus() {
        return status;
    }

    public RestockPriority getPriority() {
        return priority;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public List<RestockRequestItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
