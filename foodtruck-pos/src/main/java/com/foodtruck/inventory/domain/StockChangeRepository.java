package com.foodtruck.inventory.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockChangeRepository extends JpaRepository<StockChange, String> {

    boolean existsByMessageId(String messageId);
}
