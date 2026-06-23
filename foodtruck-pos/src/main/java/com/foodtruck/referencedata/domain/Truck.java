package com.foodtruck.referencedata.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Stammdaten: ein Food-Truck. Wird von Inventory- und Procurement-Kontext nur ueber die ID referenziert. */
@Entity
@Table(name = "truck")
public class Truck {

    @Id
    private String id;

    private String name;

    @Column(name = "current_latitude")
    private double currentLatitude;

    @Column(name = "current_longitude")
    private double currentLongitude;

    @Enumerated(EnumType.STRING)
    private TruckStatus status;

    protected Truck() {
    }

    public Truck(String id, String name, double currentLatitude, double currentLongitude, TruckStatus status) {
        this.id = id;
        this.name = name;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCurrentLatitude() {
        return currentLatitude;
    }

    public double getCurrentLongitude() {
        return currentLongitude;
    }

    public TruckStatus getStatus() {
        return status;
    }
}
