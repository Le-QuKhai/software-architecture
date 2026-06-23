package com.foodtruck.procurement.application;

import java.util.List;

/**
 * Neutrales Uebergabeobjekt fuer das externe Tourenplanungssystem.
 * Bewusst KEIN Domaenenobjekt: interne Aggregate werden nicht nach aussen gereicht
 * (Clean-Architecture-Entscheidung der Doku).
 */
public record MultiRouteDispatchCommand(
        String truckId,
        double latitude,
        double longitude,
        String priority,
        List<Item> items) {

    public record Item(String articleId, int quantity) {
    }
}
