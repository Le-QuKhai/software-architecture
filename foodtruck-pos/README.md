# Food Truck – Bestand & Nachschub (Inventory + Procurement)

Spring-Boot-Umsetzung des Use Case **„Bestandsänderung verarbeiten"** mit *einfacher DDD*
und klassischer **Schichtenarchitektur**. Zwei fachliche Kontexte (Bounded Contexts):

- **Inventory** – führt den Bestand pro Truck/Artikel und erkennt Meldebestand-Unterschreitungen.
- **Procurement** – bündelt Nachschubbedarf zu `RestockRequest`s und erzeugt `TransportOrder`s
  für das externe System *MultiRoute Tour!*.

## Tech-Stack
- Java 21, Spring Boot 3.3.x (Web, Data JPA, Validation)
- H2 In-Memory-DB (Schema via `ddl-auto: create-drop`, Demo-Daten via `DataSeeder`)
- Build: Maven

## Schichten pro Kontext
```
web            -> REST-Controller + Request/Response-DTOs (Presentation)
application    -> Anwendungsfälle / Orchestrierung + Commands + Ports
domain         -> Aggregate, Entitäten, Value Objects, Enums, Repository-Interfaces
infrastructure -> Adapter zu externen Systemen (z. B. MultiRouteTourAdapter)
```
Abhängigkeitsrichtung: `web → application → domain`; `infrastructure` implementiert Ports aus `application`.
Die beiden Kontexte sind über **Domain-Events** (`shared/event`) entkoppelt, nicht über direkte Aufrufe.

## Starten
```bash
mvn spring-boot:run
# H2-Konsole: http://localhost:8080/h2-console  (JDBC URL: jdbc:h2:mem:foodtruck)
```

## Beispiel-Durchlauf (entspricht dem Beispiel aus der Doku)
Ausgangslage: `truck-42` hat 51 `burger-patty`, Meldebestand 50, Zielbestand 100.

```bash
# 1) Bestand ansehen
curl http://localhost:8080/api/v1/trucks/truck-42/inventory

# 2) Verkauf von 2 Patties melden -> Bestand 49, unter Meldebestand -> RestockRequest entsteht
curl -X POST http://localhost:8080/api/v1/inventory/stock-changes \
  -H "Content-Type: application/json" \
  -d '{"messageId":"msg-1002","sourceContext":"ORDER","sourceReferenceId":"order-77","truckId":"truck-42","articleId":"burger-patty","changeType":"SALE","quantityDelta":-2,"occurredAt":"2026-06-21T12:35:00Z"}'

# 3) Offene RestockRequests (requiredQuantity = 100 - 49 = 51)
curl "http://localhost:8080/api/v1/restock-requests?status=OPEN"

# 4) TransportOrder erzeugen (RESTOCK_REQUEST_ID aus Schritt 3 einsetzen)
curl -X POST http://localhost:8080/api/v1/restock-requests/{RESTOCK_REQUEST_ID}/transport-order

# 5) Status des Transportauftrags (TRANSPORT_ORDER_ID aus Schritt 4)
curl http://localhost:8080/api/v1/transport-orders/{TRANSPORT_ORDER_ID}

# 6) Lieferung als RESTOCK +51 melden -> Bestand 100, RestockRequest FULFILLED, TransportOrder DELIVERED
curl -X POST http://localhost:8080/api/v1/inventory/stock-changes \
  -H "Content-Type: application/json" \
  -d '{"messageId":"msg-2001","sourceContext":"PROCUREMENT","sourceReferenceId":"transportOrder-to-5001","truckId":"truck-42","articleId":"burger-patty","changeType":"RESTOCK","quantityDelta":51,"occurredAt":"2026-06-21T15:55:00Z"}'
```

## Abbildung der Business Rules
- **Regel 1 (kein Doppel-Nachschub):** pro Truck wird höchstens ein `RestockRequest` im Status `OPEN` geführt; neue Bedarfe gehen in diesen.
- **Regel 2 (Sammelbestellung):** der offene `RestockRequest` wirkt als Sammelkorb; statt eines 5-Minuten-Timers wird der `TransportOrder` über einen expliziten Endpunkt ausgelöst (siehe Doku 2.4).
- **Regel 3 (Lieferpriorität):** fällt eine Hauptzutat (`Article.mainIngredient = true`) auf 0, wird der Request auf `EMERGENCY` eskaliert.
- **Regel 4 (Zuordnung zum Truck):** jede `StockChange` und jedes `InventoryItem` trägt eine `truckId`.
- **Idempotenz:** identische `messageId` wird nur einmal verbucht (Retry-sicher).

## Hinweis
Das Projekt wurde in dieser Umgebung **nicht kompiliert** (kein Maven/kein Zugriff auf Maven Central).
Der Code ist auf Konsistenz geprüft; bitte lokal mit `mvn clean verify` bauen.
