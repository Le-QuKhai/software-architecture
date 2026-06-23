package com.foodtruck.bootstrap;

import com.foodtruck.inventory.domain.InventoryItem;
import com.foodtruck.inventory.domain.InventoryItemRepository;
import com.foodtruck.referencedata.domain.Article;
import com.foodtruck.referencedata.domain.ArticleRepository;
import com.foodtruck.referencedata.domain.Truck;
import com.foodtruck.referencedata.domain.TruckRepository;
import com.foodtruck.referencedata.domain.TruckStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/** Legt Demo-Stammdaten und Anfangsbestaende an (statt data.sql, robuster gegenueber Schema-Aenderungen). */
@Component
public class DataSeeder implements CommandLineRunner {

    private final TruckRepository truckRepository;
    private final ArticleRepository articleRepository;
    private final InventoryItemRepository inventoryItemRepository;

    public DataSeeder(TruckRepository truckRepository,
                      ArticleRepository articleRepository,
                      InventoryItemRepository inventoryItemRepository) {
        this.truckRepository = truckRepository;
        this.articleRepository = articleRepository;
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (truckRepository.count() > 0) {
            return;
        }

        truckRepository.save(new Truck("truck-42", "Burger Truck Alster", 53.5511, 9.9937, TruckStatus.ACTIVE));
        truckRepository.save(new Truck("truck-7", "Veggie Truck Schanze", 53.5630, 9.9610, TruckStatus.ACTIVE));

        articleRepository.save(new Article("burger-patty", "Burger Patty", "Stueck", true));
        articleRepository.save(new Article("burger-bun", "Burger Bun", "Stueck", true));
        articleRepository.save(new Article("salad", "Salat", "Portion", false));
        articleRepository.save(new Article("sauce", "Sosse", "Portion", false));

        // Anfangsbestaende fuer truck-42 (entspricht dem Beispiel aus der Doku).
        inventoryItemRepository.save(item("truck-42", "burger-patty", 51, 50, 100));
        inventoryItemRepository.save(item("truck-42", "burger-bun", 80, 40, 120));
        inventoryItemRepository.save(item("truck-42", "salad", 30, 20, 60));
        inventoryItemRepository.save(item("truck-7", "salad", 25, 20, 60));
    }

    private InventoryItem item(String truckId, String articleId, int current, int threshold, int target) {
        return new InventoryItem(UUID.randomUUID().toString(), truckId, articleId, current, threshold, target);
    }
}
