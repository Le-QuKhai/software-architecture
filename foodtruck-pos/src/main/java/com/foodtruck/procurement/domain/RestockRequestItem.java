package com.foodtruck.procurement.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/** Teil des RestockRequest-Aggregates: welcher Artikel in welcher Menge nachgefuellt werden soll. */
@Entity
@Table(name = "restock_request_item")
public class RestockRequestItem {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restock_request_id", nullable = false)
    private RestockRequest restockRequest;

    @Column(name = "article_id", nullable = false)
    private String articleId;

    @Column(name = "required_quantity", nullable = false)
    private int requiredQuantity;

    protected RestockRequestItem() {
    }

    RestockRequestItem(String id, RestockRequest restockRequest, String articleId, int requiredQuantity) {
        this.id = id;
        this.restockRequest = restockRequest;
        this.articleId = articleId;
        this.requiredQuantity = requiredQuantity;
    }

    void setRequiredQuantity(int requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public String getArticleId() {
        return articleId;
    }

    public int getRequiredQuantity() {
        return requiredQuantity;
    }
}
