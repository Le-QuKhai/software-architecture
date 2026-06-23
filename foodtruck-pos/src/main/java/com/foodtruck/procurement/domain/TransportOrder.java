package com.foodtruck.procurement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

/** Transportauftrag, der an das externe System "MultiRoute Tour!" uebergeben wird. */
@Entity
@Table(name = "transport_order")
public class TransportOrder {

    @Id
    private String id;

    @Column(name = "restock_request_id", nullable = false)
    private String restockRequestId;

    @Column(name = "truck_id", nullable = false)
    private String truckId;

    @Column(name = "external_system")
    private String externalSystem;

    @Column(name = "external_order_id")
    private String externalOrderId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransportOrderStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "planned_delivery_time")
    private Instant plannedDeliveryTime;

    protected TransportOrder() {
    }

    public TransportOrder(String id, String restockRequestId, String truckId, String externalSystem) {
        this.id = id;
        this.restockRequestId = restockRequestId;
        this.truckId = truckId;
        this.externalSystem = externalSystem;
        this.status = TransportOrderStatus.CREATED;
        this.createdAt = Instant.now();
    }

    public void markSentToMultiRoute(String externalOrderId) {
        this.externalOrderId = externalOrderId;
        this.status = TransportOrderStatus.SENT_TO_MULTIROUTE;
    }

    public void markPlanned(Instant plannedDeliveryTime) {
        this.plannedDeliveryTime = plannedDeliveryTime;
        this.status = TransportOrderStatus.PLANNED;
    }

    public void markFailed() {
        this.status = TransportOrderStatus.FAILED;
    }

    public void markDelivered() {
        this.status = TransportOrderStatus.DELIVERED;
    }

    public String getId() {
        return id;
    }

    public String getRestockRequestId() {
        return restockRequestId;
    }

    public String getTruckId() {
        return truckId;
    }

    public String getExternalSystem() {
        return externalSystem;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public TransportOrderStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getPlannedDeliveryTime() {
        return plannedDeliveryTime;
    }
}
