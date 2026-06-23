package com.foodtruck.referencedata.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Stammdaten: ein Artikel (Zutat). {@code mainIngredient} kennzeichnet eine Hauptzutat
 * und steuert die Notfall-Prioritaet bei Bestand 0 (Business Rule 3).
 */
@Entity
@Table(name = "article")
public class Article {

    @Id
    private String id;

    private String name;

    private String unit;

    @Column(name = "main_ingredient", nullable = false)
    private boolean mainIngredient;

    protected Article() {
    }

    public Article(String id, String name, String unit, boolean mainIngredient) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.mainIngredient = mainIngredient;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public boolean isMainIngredient() {
        return mainIngredient;
    }
}
