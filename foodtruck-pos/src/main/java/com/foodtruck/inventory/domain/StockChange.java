package com.foodtruck.inventory.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.Instant;

/**
 * Eine einzelne, fachlich nachvollziehbare Bestandsaenderung (Audit-Eintrag).
 * Die eindeutige {@code messageId} dient der Idempotenz (kein Doppel-Buchen bei Retries).
 */
@Entity
@Table(name = "stock_change",
        uniqueConstraints = @UniqueConstraint(name = "uq_stock_change_message", columnNames = "message_id"))
public class StockChange {

    @Id
    private String id;

    @Column(name = "message_id", nullable = false)
    private String messageId;

    @Column(name = "source_context")
    private String sourceContext;

    @Column(name = "source_reference_id")
    private String sourceReferenceId;

    @Column(name = "reverses_message_id")
    private String reversesMessageId;

    @Column(name = "truck_id", nullable = false)
    private String truckId;

    @Column(name = "article_id", nullable = false)
    private String articleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "change_type", nullable = false)
    private ChangeType changeType;

    @Column(name = "quantity_delta", nullable = false)
    private int quantityDelta;

    @Column(name = "occurred_at")
    private Instant occurredAt;

    @Column(name = "processed_at")
    private Instant processedAt;

    protected StockChange() {
    }

    public StockChange(String id, String messageId, String sourceContext, String sourceReferenceId,
                       String reversesMessageId, String truckId, String articleId, ChangeType changeType,
                       int quantityDelta, Instant occurredAt) {
        this.id = id;
        this.messageId = messageId;
        this.sourceContext = sourceContext;
        this.sourceReferenceId = sourceReferenceId;
        this.reversesMessageId = reversesMessageId;
        this.truckId = truckId;
        this.articleId = articleId;
        this.changeType = changeType;
        this.quantityDelta = quantityDelta;
        this.occurredAt = occurredAt;
        this.processedAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getMessageId() {
        return messageId;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public int getQuantityDelta() {
        return quantityDelta;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }
}
