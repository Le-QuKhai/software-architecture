package com.foodtruck.inventory.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, String> {

    Optional<InventoryItem> findByTruckIdAndArticleId(String truckId, String articleId);

    List<InventoryItem> findByTruckId(String truckId);
}
