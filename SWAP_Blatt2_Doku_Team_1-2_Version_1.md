# Food Truck PoS System

## Software Architecture Documentation (arc42) – Aufgabe 2: Verfeinerung

**Group:** Group 1–2

**Members:**
- Hoang Linh Pham
- Dilan Ucan
- Quang Khai Le
- Delilah Richter

**Version:** 3.0

---

> **Hinweis:** Dieses Dokument ergänzt die bestehende Architekturdokumentation (Version 2.0) um die detaillierte Verfeinerung zweier Artefakte gemäß Aufgabenblatt 2.

---

# Inhaltsverzeichnis

1. [Einleitung](#1-einleitung)
2. [Artefakt 1: Inventory Sync – Bestandsänderungsmeldungen](#2-artefakt-1-inventory-sync--bestandsänderungsmeldungen)
   - 2.1 Blackbox-Sicht
   - 2.2 Whitebox-Sicht (Ebene 1)
   - 2.3 Schnittstellenspezifikation
   - 2.4 Kommunikationsentscheidung
3. [Artefakt 2: Promotion Engine – Rabattberechnung](#3-artefakt-2-promotion-engine--rabattberechnung)
   - 3.1 Blackbox-Sicht
   - 3.2 Whitebox-Sicht (Ebene 1)
   - 3.3 Schnittstellenspezifikation
   - 3.4 Kommunikationsentscheidung
4. [Annahmen](#4-annahmen)
5. [Glossar (Ergänzungen)](#5-glossar-ergänzungen)
6. [Quellen und KI-Werkzeuge](#6-quellen-und-ki-werkzeuge)

---

# 1. Einleitung

Im Rahmen von Aufgabenblatt 2 werden zwei Artefakte des Food-Truck-PoS-Systems im Detail verfeinert:

1. **Inventory Sync** – Der Teil des Systems, der Bestandsänderungen aus den Trucks an die Zentrale meldet und dort verarbeitet.
2. **Promotion Engine** – Der Teil des Systems, der Rabattaktionen auf einen Warenkorb anwendet und den Endpreis berechnet.

Für beide Artefakte wird zunächst die Blackbox-Sicht (Aufgaben, Schnittstellen, Qualitätsanforderungen) beschrieben, anschließend das Innenleben als Whitebox (Ebene 1) dargestellt. Die Kommunikationsbeziehungen werden mit maschinenlesbaren Schnittstellenspezifikationen (OpenAPI / AsyncAPI) dokumentiert.

---

# 2. Artefakt 1: Inventory Sync – Bestandsänderungsmeldungen

## 2.1 Blackbox-Sicht

### Zweck / Verantwortung

<!-- 
Beschreibt, was dieses Artefakt tut, OHNE das Innenleben zu erklären.
Beispiel: "Das Artefakt nimmt Bestandsänderungen von den Food Trucks entgegen, 
leitet sie an die Zentrale weiter und stellt sicher, dass auch bei Offline-Betrieb 
keine Meldung verloren geht."
-->

TODO: Beschreibung einfügen

### Zugeordnete Anforderungen

| ID | Anforderung | Bezug |
|----|-------------|-------|
| F13 | Bestandsänderungen pro Truck werden erfasst | Funktional |
| F14 | Das System löst automatisch Nachschub aus der Zentrale aus | Funktional |
| F15 | Zentralbestand wird geführt und Nachbestellungen erfolgen automatisch | Funktional |
| NF1 | Verkäufe können bei Verbindungsabbruch lokal gespeichert und später synchronisiert werden | Reliability |
| NF2 | Kein Datenverlust bei Verbindungsabbruch | Reliability |
| NF3 | Das PoS läuft flüssig auf leistungsschwacher Hardware | Performance |

### Schnittstellen (extern)

| Schnittstelle | Richtung | Beschreibung |
|---------------|----------|--------------|
| TODO | Eingehend | TODO: z. B. PoS App meldet Bestandsänderung nach Verkauf |
| TODO | Ausgehend | TODO: z. B. Inventory Service empfängt aggregierte Meldungen |
| TODO | Ausgehend | TODO: z. B. Procurement Service wird über niedrigen Bestand informiert |

### Qualitäts- und Leistungsmerkmale

| Merkmal | Beschreibung | Messbar |
|---------|--------------|---------|
| Offline-Fähigkeit | Bestandsänderungen werden lokal gepuffert und bei Reconnect synchronisiert | Kein Datenverlust nach Verbindungsabbruch |
| Konsistenz | Eventual Consistency zwischen Truck und Zentrale | TODO: Zeitfenster definieren |
| Fehlertoleranz | Fehlgeschlagene Synchronisationen werden wiederholt | TODO: Max. Retries / Backoff |
| Performance | TODO | TODO: z. B. Verarbeitung innerhalb von x ms |

### Ablageort / Deployment

<!-- Wo läuft dieses Artefakt? Truck-seitig, Backend, oder beides? -->

TODO: z. B. "Truck-seitige Komponenten laufen auf dem Tablet/PoS-Device. Die Empfangsseite läuft im Cloud-Backend."

---

## 2.2 Whitebox-Sicht (Ebene 1)

### Übersichtsdiagramm

<!-- 
Fügen Sie hier ein UML-Komponentendiagramm ein, das die internen Bausteine zeigt.
Beispielkomponenten:
- InventoryChangeDetector (erkennt Bestandsänderung nach Verkauf)
- LocalChangeQueue (puffert Änderungen offline)
- SyncDispatcher (sendet Änderungen bei Konnektivität)
- InventoryReceiver (Backend, nimmt Meldungen entgegen)
- StockLevelCalculator (berechnet neuen Zentralbestand)
- ReorderTrigger (prüft Meldebestand und löst Nachbestellung aus)
-->

![Whitebox Inventory Sync](images/whitebox_inventory_sync.png)

TODO: Diagramm erstellen und einbinden

### Enthaltene Bausteine

| Baustein | Verantwortung |
|----------|---------------|
| TODO: z. B. InventoryChangeDetector | TODO: Erkennt Bestandsänderungen nach jedem Verkauf und erzeugt ein InventoryChangeEvent |
| TODO: z. B. LocalChangeQueue | TODO: Puffert Änderungsereignisse lokal (FIFO), persistiert sie auf dem Gerät |
| TODO: z. B. SyncDispatcher | TODO: Überwacht Konnektivität, sendet gepufferte Ereignisse ans Backend |
| TODO: z. B. InventoryReceiver | TODO: Backend-Komponente, nimmt Meldungen entgegen, validiert, persistiert |
| TODO: z. B. StockLevelCalculator | TODO: Berechnet aktuellen Zentralbestand aus eingehenden Meldungen |
| TODO: z. B. ReorderTrigger | TODO: Prüft Meldebestand, löst automatische Nachbestellung aus |

### Interne Kommunikationsbeziehungen

<!-- Beschreiben Sie, wie die Bausteine intern miteinander kommunizieren. -->

| Von | Nach | Art | Beschreibung |
|-----|------|-----|--------------|
| TODO | TODO | TODO: sync/async | TODO |
| TODO | TODO | TODO | TODO |

### Sequenzdiagramm: Bestandsänderung nach Verkauf (Online-Fall)

<!-- 
UML-Sequenzdiagramm, das den Ablauf zeigt:
1. Verkauf erfolgt → PoS App informiert InventoryChangeDetector
2. InventoryChangeDetector erzeugt Event
3. SyncDispatcher sendet an InventoryReceiver
4. InventoryReceiver aktualisiert StockLevelCalculator
5. ReorderTrigger prüft Meldebestand
-->

![Sequenzdiagramm Inventory Sync Online](images/seq_inventory_sync_online.png)

TODO: Diagramm erstellen und einbinden

### Sequenzdiagramm: Bestandsänderung nach Verkauf (Offline-Fall)

<!-- 
UML-Sequenzdiagramm für den Offline-Fall:
1. Verkauf erfolgt → Event wird in LocalChangeQueue gepuffert
2. Konnektivität wird wiederhergestellt
3. SyncDispatcher sendet alle gepufferten Events
4. Backend bestätigt Empfang
-->

![Sequenzdiagramm Inventory Sync Offline](images/seq_inventory_sync_offline.png)

TODO: Diagramm erstellen und einbinden

---

## 2.3 Schnittstellenspezifikation

### Kommunikationsentscheidung

<!-- 
Begründen Sie hier, WARUM Sie diese Kommunikationsart gewählt haben.
z. B. asynchron via Message Queue vs. synchron via REST.
-->

**Gewählte Kommunikationsart:** TODO: z. B. Asynchron via MQTT / Message Broker / REST mit Retry

**Begründung:**

TODO: Begründung einfügen, z. B.:
- Offline-Fähigkeit erfordert entkoppelte Kommunikation
- Bestandsänderungen sind nicht zeitkritisch (Eventual Consistency akzeptabel)
- Asynchrone Verarbeitung erlaubt Batching und reduziert Netzwerklast

**Verworfene Alternativen:**

| Alternative | Grund der Ablehnung |
|-------------|---------------------|
| TODO: z. B. Synchroner REST-Call | TODO: z. B. Blockiert bei Offline-Betrieb; nicht fehlertolerant |
| TODO: z. B. WebSocket-Verbindung | TODO: z. B. Zu aufwändig für einfache Statusmeldungen; Verbindungsabbrüche problematisch |

### API-Spezifikation (AsyncAPI / OpenAPI)

<!-- 
Fügen Sie hier die maschinenlesbare Schnittstellendefinition ein.
Wählen Sie OpenAPI (YAML) für REST-basierte Kommunikation 
oder AsyncAPI (YAML) für nachrichtenbasierte Kommunikation.
-->

#### Option A: AsyncAPI (empfohlen bei asynchroner Kommunikation)

```yaml
# TODO: Anpassen und vervollständigen
asyncapi: '2.6.0'
info:
  title: Inventory Sync API
  version: '1.0.0'
  description: >
    API für die asynchrone Übermittlung von Bestandsänderungen
    aus den Food Trucks an die Zentrale.

channels:
  inventory/changes/{truckId}:
    description: Kanal für Bestandsänderungsmeldungen eines Trucks
    parameters:
      truckId:
        description: Eindeutige Truck-ID
        schema:
          type: string
    publish:
      summary: Truck sendet Bestandsänderung
      operationId: publishInventoryChange
      message:
        $ref: '#/components/messages/InventoryChangeMessage'

components:
  messages:
    InventoryChangeMessage:
      name: InventoryChangeMessage
      title: Bestandsänderungsmeldung
      summary: Eine einzelne Bestandsänderung nach einem Verkauf
      contentType: application/json
      payload:
        $ref: '#/components/schemas/InventoryChangeEvent'

  schemas:
    InventoryChangeEvent:
      type: object
      required:
        - eventId
        - truckId
        - timestamp
        - changes
      properties:
        eventId:
          type: string
          format: uuid
          description: Eindeutige Event-ID (Idempotenz-Schlüssel)
        truckId:
          type: string
          description: Eindeutige Truck-Kennung
        timestamp:
          type: string
          format: date-time
          description: Zeitpunkt der Bestandsänderung
        orderId:
          type: string
          format: uuid
          description: Zugehörige Bestell-ID
        changes:
          type: array
          items:
            $ref: '#/components/schemas/StockChange'
          minItems: 1

    StockChange:
      type: object
      required:
        - productId
        - quantityChange
        - reason
      properties:
        productId:
          type: string
          description: Produkt-ID
        productName:
          type: string
          description: Produktname (für Logging/Debugging)
        quantityChange:
          type: integer
          description: "Änderung der Menge (negativ = Abgang, positiv = Zugang)"
        unit:
          type: string
          description: "Mengeneinheit (z. B. Stück, Liter, kg)"
        reason:
          type: string
          enum: [SALE, WASTE, CORRECTION, RESTOCK]
          description: Grund der Bestandsänderung
```

#### Option B: OpenAPI (bei synchroner REST-Kommunikation)

```yaml
# TODO: Nur verwenden, falls REST gewählt wird. Anpassen und vervollständigen.
openapi: '3.0.3'
info:
  title: Inventory Sync REST API
  version: '1.0.0'
  description: >
    REST-API für die Übermittlung von Bestandsänderungen
    aus den Food Trucks an die Zentrale.

paths:
  /api/v1/inventory/changes:
    post:
      summary: Bestandsänderung melden
      operationId: reportInventoryChange
      description: >
        Wird vom Truck aufgerufen, um eine oder mehrere Bestandsänderungen
        an die Zentrale zu melden.
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/InventoryChangeBatch'
      responses:
        '202':
          description: Änderungen akzeptiert und zur Verarbeitung eingereiht
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/AcceptedResponse'
        '400':
          description: Ungültige Anfrage
        '409':
          description: Duplikat erkannt (Idempotenz)
        '500':
          description: Serverfehler

  /api/v1/inventory/trucks/{truckId}/stock:
    get:
      summary: Aktuellen Bestand eines Trucks abrufen
      operationId: getTruckStock
      parameters:
        - name: truckId
          in: path
          required: true
          schema:
            type: string
      responses:
        '200':
          description: Aktueller Bestand
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TruckStockResponse'

components:
  schemas:
    InventoryChangeBatch:
      type: object
      required:
        - truckId
        - changes
      properties:
        truckId:
          type: string
        batchId:
          type: string
          format: uuid
          description: Idempotenz-Schlüssel für das gesamte Batch
        changes:
          type: array
          items:
            $ref: '#/components/schemas/InventoryChangeEvent'
          minItems: 1

    InventoryChangeEvent:
      type: object
      required:
        - eventId
        - truckId
        - timestamp
        - changes
      properties:
        eventId:
          type: string
          format: uuid
        truckId:
          type: string
        timestamp:
          type: string
          format: date-time
        orderId:
          type: string
          format: uuid
        changes:
          type: array
          items:
            $ref: '#/components/schemas/StockChange'

    StockChange:
      type: object
      required:
        - productId
        - quantityChange
        - reason
      properties:
        productId:
          type: string
        productName:
          type: string
        quantityChange:
          type: integer
        unit:
          type: string
        reason:
          type: string
          enum: [SALE, WASTE, CORRECTION, RESTOCK]

    AcceptedResponse:
      type: object
      properties:
        status:
          type: string
          example: accepted
        processedCount:
          type: integer
        duplicateCount:
          type: integer

    TruckStockResponse:
      type: object
      properties:
        truckId:
          type: string
        lastUpdated:
          type: string
          format: date-time
        items:
          type: array
          items:
            type: object
            properties:
              productId:
                type: string
              productName:
                type: string
              currentQuantity:
                type: integer
              unit:
                type: string
              reorderThreshold:
                type: integer
```

### Vor- und Nachbedingungen

**Vorbedingungen:**

| # | Bedingung |
|---|-----------|
| 1 | TODO: z. B. Ein Verkauf oder eine manuelle Bestandskorrektur wurde durchgeführt |
| 2 | TODO: z. B. Die Bestandsänderung ist lokal persistiert |
| 3 | TODO: z. B. Die Event-ID ist eindeutig (UUID) |

**Nachbedingungen (Erfolgsfall):**

| # | Bedingung |
|---|-----------|
| 1 | TODO: z. B. Die Bestandsänderung ist im zentralen Inventory Service persistiert |
| 2 | TODO: z. B. Der Zentralbestand ist aktualisiert |
| 3 | TODO: z. B. Bei Unterschreitung des Meldebestands wurde eine Nachbestellung ausgelöst |

**Nachbedingungen (Fehlerfall):**

| # | Bedingung |
|---|-----------|
| 1 | TODO: z. B. Die Änderung verbleibt in der lokalen Queue und wird beim nächsten Versuch erneut gesendet |
| 2 | TODO: z. B. Duplikate werden anhand der Event-ID erkannt und ignoriert |

---

## 2.4 Architekturentscheidung (ADR)

### ADR-X: Kommunikationsart für Bestandsänderungsmeldungen

| Feld | Inhalt |
|------|--------|
| **Status** | TODO: Vorgeschlagen / Akzeptiert |
| **Kontext** | Food Trucks haben instabile Internetverbindungen. Bestandsänderungen müssen zuverlässig an die Zentrale übermittelt werden, auch wenn der Truck zeitweise offline ist. |
| **Entscheidung** | TODO: z. B. Asynchrone Kommunikation via REST mit lokaler Queue und Retry-Mechanismus |
| **Begründung** | TODO |
| **Konsequenzen** | TODO: z. B. Eventual Consistency muss akzeptiert werden; Idempotenz muss sichergestellt sein |
| **Alternativen** | TODO: z. B. MQTT, WebSocket, gRPC – mit jeweiliger Begründung der Ablehnung |

---

# 3. Artefakt 2: Promotion Engine – Rabattberechnung

## 3.1 Blackbox-Sicht

### Zweck / Verantwortung

<!-- 
Beschreibt, was dieses Artefakt tut.
Beispiel: "Die Promotion Engine berechnet den Endpreis eines Warenkorbs 
unter Berücksichtigung aller aktiven Rabattaktionen. Sie gibt den reduzierten 
Preis sowie eine detaillierte Aufschlüsselung der angewendeten Rabatte zurück, 
damit der gewährte Vorteil auf dem PoS und Bon angezeigt werden kann."
-->

TODO: Beschreibung einfügen

### Konkrete Beispiel-Rabattaktion

<!-- 
Wählen Sie eine konkrete Rabattaktion als Beispiel und beschreiben Sie diese.
Die Aufgabenstellung verlangt mindestens ein konkretes Beispiel.
-->

**Gewählte Rabattaktion:** TODO: z. B. "Kaufe 3 Softdrinks, zahle nur 2" (3-für-2)

**Beschreibung:**

TODO: Detaillierte Beschreibung der Aktion, z. B.:
- Aktionszeitraum: 01.06.2026 – 30.06.2026
- Betroffene Produkte: Alle Produkte der Kategorie "Softdrinks"
- Regel: Bei 3 oder mehr Softdrinks im Warenkorb wird der günstigste kostenlos
- Kombinierbar: Ja/Nein mit anderen Aktionen
- Anzeige auf dem Bon: "Aktion '3 für 2 Softdrinks': Sie sparen X,XX €!"

### Zugeordnete Anforderungen

| ID | Anforderung | Bezug |
|----|-------------|-------|
| F2 | Das System berechnet automatisch den Endpreis unter Berücksichtigung aktiver Rabattaktionen | Funktional |
| F10 | Rabattaktionen können zentral gepflegt werden | Funktional |
| F11 | Unterstützung verschiedener Aktionstypen | Funktional |
| F12 | Aktualisierungen werden zeitnah an alle Trucks verteilt | Funktional |
| NF4 | Preisberechnung und Rabattprüfung erfolgen in unter x Millisekunden | Performance |
| NF8 | Neue Aktionstypen können ohne manuelle Truck-Updates eingeführt werden | Maintainability |

### Schnittstellen (extern)

| Schnittstelle | Richtung | Beschreibung |
|---------------|----------|--------------|
| TODO | Eingehend | TODO: z. B. PoS App sendet Warenkorb zur Preisberechnung |
| TODO | Ausgehend | TODO: z. B. Antwort mit berechnetem Endpreis und Rabattdetails |
| TODO | Eingehend | TODO: z. B. Promotion Service liefert aktive Aktionsdefinitionen |

### Datenfluss: Was muss der Client senden?

<!-- 
Hier explizit beschreiben, welche Daten die PoS App senden muss,
damit die Rabattberechnung durchgeführt werden kann.
-->

| Datum | Typ | Beschreibung |
|-------|-----|--------------|
| TODO: z. B. cartItems | Array | TODO: Liste der Artikel im Warenkorb mit Produkt-ID, Menge, Einzelpreis |
| TODO: z. B. truckId | String | TODO: Truck-Kennung (für standortspezifische Aktionen) |
| TODO: z. B. timestamp | DateTime | TODO: Zeitpunkt des Verkaufs (für zeitbasierte Aktionen wie Happy Hour) |
| TODO | TODO | TODO |

### Datenfluss: Was wird an den Client zurückgesendet?

<!-- 
Hier explizit beschreiben, welche Daten zurückgegeben werden.
Denken Sie an die Anforderung: Marketing möchte den Rabattvorteil 
auf dem PoS und dem Bon anzeigen!
-->

| Datum | Typ | Beschreibung |
|-------|-----|--------------|
| TODO: z. B. totalPrice | Decimal | TODO: Endpreis des Warenkorbs nach allen Rabatten |
| TODO: z. B. totalDiscount | Decimal | TODO: Gesamter Rabattbetrag |
| TODO: z. B. appliedPromotions | Array | TODO: Liste angewendeter Aktionen mit Name, Typ, gespartem Betrag |
| TODO: z. B. lineItems | Array | TODO: Aufgeschlüsselte Einzelposten mit Original- und reduziertem Preis |
| TODO: z. B. savingsMessage | String | TODO: Anzeigetext für Bon, z. B. "Durch unsere Aktion '3 für 2' haben Sie 2,50 € gespart!" |

### Qualitäts- und Leistungsmerkmale

| Merkmal | Beschreibung | Messbar |
|---------|--------------|---------|
| Performance | Rabattberechnung muss schnell sein, da sie im Verkaufsprozess stattfindet | TODO: < x ms |
| Korrektheit | Preisberechnung muss exakt und nachvollziehbar sein | Cent-genaue Berechnung |
| Erweiterbarkeit | Neue Aktionstypen ohne Code-Änderung am Truck | TODO |
| Offline-Fähigkeit | Rabattberechnung muss auch offline funktionieren | Lokale Aktionsdaten verfügbar |

### Ablageort / Deployment

TODO: z. B. "Die Promotion Engine läuft lokal auf dem Tablet/PoS-Device, um Offline-Fähigkeit sicherzustellen. Aktionsdefinitionen werden vom zentralen Promotion Service synchronisiert."

---

## 3.2 Whitebox-Sicht (Ebene 1)

### Übersichtsdiagramm

<!-- 
UML-Komponentendiagramm der internen Bausteine.
Beispielkomponenten:
- CartAnalyzer (analysiert den Warenkorb)
- PromotionMatcher (findet passende Aktionen)
- DiscountCalculator (berechnet den Rabatt pro Aktion)
- PriceAggregator (berechnet Endpreis und erstellt Aufschlüsselung)
- PromotionCache (lokaler Cache der Aktionsdefinitionen)
- BonMessageGenerator (erstellt Anzeige-/Bontext)
-->

![Whitebox Promotion Engine](images/whitebox_promotion_engine.png)

TODO: Diagramm erstellen und einbinden

### Enthaltene Bausteine

| Baustein | Verantwortung |
|----------|---------------|
| TODO: z. B. CartAnalyzer | TODO: Analysiert den Warenkorb, gruppiert Artikel nach Kategorien und Produkten |
| TODO: z. B. PromotionMatcher | TODO: Gleicht Warenkorbinhalte mit aktiven Aktionsdefinitionen ab |
| TODO: z. B. DiscountCalculator | TODO: Berechnet den konkreten Rabattbetrag je Aktion (Strategy Pattern) |
| TODO: z. B. PriceAggregator | TODO: Summiert Rabatte, berechnet Endpreis, erstellt Aufschlüsselung |
| TODO: z. B. PromotionCache | TODO: Hält lokal synchronisierte Aktionsdefinitionen für Offline-Betrieb vor |
| TODO: z. B. BonMessageGenerator | TODO: Erzeugt den Anzeige-/Bontext ("Durch unsere Aktion XYZ haben Sie X € gespart!") |

### Interne Kommunikationsbeziehungen

| Von | Nach | Art | Beschreibung |
|-----|------|-----|--------------|
| TODO | TODO | TODO | TODO |
| TODO | TODO | TODO | TODO |

### Klassendiagramm: Rabattaktionstypen

<!-- 
UML-Klassendiagramm, das zeigt, wie verschiedene Aktionstypen modelliert werden.
z. B. Strategy Pattern mit:
- PromotionStrategy (Interface)
- PercentageDiscount implements PromotionStrategy
- BuyXGetYFree implements PromotionStrategy
- BundlePrice implements PromotionStrategy
- HappyHourDiscount implements PromotionStrategy
-->

![Klassendiagramm Promotions](images/class_promotion_types.png)

TODO: Diagramm erstellen und einbinden

### Sequenzdiagramm: Rabattberechnung am Beispiel "3 für 2"

<!-- 
UML-Sequenzdiagramm, das den konkreten Ablauf für die gewählte Beispiel-Rabattaktion zeigt:
1. PoS App sendet Warenkorb an Promotion Engine
2. CartAnalyzer analysiert Inhalte
3. PromotionMatcher findet "3 für 2"-Aktion
4. DiscountCalculator berechnet Rabatt
5. PriceAggregator erstellt Gesamtergebnis
6. BonMessageGenerator erzeugt Anzeigetext
7. Ergebnis wird an PoS App zurückgegeben
-->

![Sequenzdiagramm Rabattberechnung](images/seq_promotion_calculation.png)

TODO: Diagramm erstellen und einbinden

---

## 3.3 Schnittstellenspezifikation

### API-Spezifikation (OpenAPI)

```yaml
# TODO: Anpassen und vervollständigen
openapi: '3.0.3'
info:
  title: Promotion Engine API
  version: '1.0.0'
  description: >
    API zur Berechnung von Rabatten auf einen Warenkorb.
    Wird von der PoS App aufgerufen, um den Endpreis inkl. aller
    aktiven Aktionen zu berechnen.

paths:
  /api/v1/promotions/calculate:
    post:
      summary: Warenkorb-Rabatte berechnen
      operationId: calculateCartPromotions
      description: >
        Berechnet den Endpreis des Warenkorbs unter Berücksichtigung 
        aller aktiven Rabattaktionen. Gibt den reduzierten Preis sowie 
        eine detaillierte Aufschlüsselung der angewendeten Rabatte zurück.
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/CartRequest'
            example:
              truckId: "truck-042"
              timestamp: "2026-06-15T12:30:00Z"
              items:
                - productId: "softdrink-cola"
                  productName: "Cola 0,33l"
                  quantity: 3
                  unitPrice: 2.50
                  category: "softdrinks"
                - productId: "burger-classic"
                  productName: "Classic Burger"
                  quantity: 1
                  unitPrice: 8.90
                  category: "food"
      responses:
        '200':
          description: Erfolgreiche Berechnung
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/CartResponse'
              example:
                originalTotal: 16.40
                totalDiscount: 2.50
                finalTotal: 13.90
                appliedPromotions:
                  - promotionId: "promo-3for2-softdrinks"
                    promotionName: "3 für 2 Softdrinks"
                    promotionType: "BUY_X_GET_Y_FREE"
                    discountAmount: 2.50
                    affectedItems:
                      - productId: "softdrink-cola"
                        quantity: 3
                    savingsMessage: "Aktion '3 für 2 Softdrinks': Sie sparen 2,50 €!"
                lineItems:
                  - productId: "softdrink-cola"
                    productName: "Cola 0,33l"
                    quantity: 3
                    unitPrice: 2.50
                    originalSubtotal: 7.50
                    discountApplied: 2.50
                    finalSubtotal: 5.00
                  - productId: "burger-classic"
                    productName: "Classic Burger"
                    quantity: 1
                    unitPrice: 8.90
                    originalSubtotal: 8.90
                    discountApplied: 0.00
                    finalSubtotal: 8.90
        '400':
          description: Ungültiger Warenkorb
        '500':
          description: Serverfehler

  /api/v1/promotions/active:
    get:
      summary: Aktive Aktionen abrufen
      operationId: getActivePromotions
      description: >
        Gibt alle derzeit aktiven Rabattaktionen zurück.
        Wird vom Truck zur Synchronisierung des lokalen Caches verwendet.
      parameters:
        - name: truckId
          in: query
          required: false
          schema:
            type: string
          description: Optional – für truck-spezifische Aktionen
        - name: since
          in: query
          required: false
          schema:
            type: string
            format: date-time
          description: Nur Aktionen liefern, die seit diesem Zeitpunkt geändert wurden (Delta-Sync)
      responses:
        '200':
          description: Liste aktiver Aktionen
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ActivePromotionsResponse'

components:
  schemas:
    CartRequest:
      type: object
      required:
        - truckId
        - timestamp
        - items
      properties:
        truckId:
          type: string
          description: Truck-Kennung
        timestamp:
          type: string
          format: date-time
          description: Zeitpunkt des Verkaufs (relevant für Happy Hour etc.)
        items:
          type: array
          items:
            $ref: '#/components/schemas/CartItem'
          minItems: 1

    CartItem:
      type: object
      required:
        - productId
        - productName
        - quantity
        - unitPrice
      properties:
        productId:
          type: string
        productName:
          type: string
        quantity:
          type: integer
          minimum: 1
        unitPrice:
          type: number
          format: decimal
          description: Einzelpreis in Euro
        category:
          type: string
          description: Produktkategorie (für kategoriebasierte Aktionen)

    CartResponse:
      type: object
      required:
        - originalTotal
        - totalDiscount
        - finalTotal
        - appliedPromotions
        - lineItems
      properties:
        originalTotal:
          type: number
          format: decimal
          description: Gesamtpreis ohne Rabatte
        totalDiscount:
          type: number
          format: decimal
          description: Gesamter Rabattbetrag
        finalTotal:
          type: number
          format: decimal
          description: Endpreis nach allen Rabatten
        appliedPromotions:
          type: array
          items:
            $ref: '#/components/schemas/AppliedPromotion'
        lineItems:
          type: array
          items:
            $ref: '#/components/schemas/LineItem'

    AppliedPromotion:
      type: object
      required:
        - promotionId
        - promotionName
        - promotionType
        - discountAmount
        - savingsMessage
      properties:
        promotionId:
          type: string
        promotionName:
          type: string
          description: Anzeigename der Aktion
        promotionType:
          type: string
          enum:
            - PERCENTAGE
            - BUY_X_GET_Y_FREE
            - BUNDLE_PRICE
            - HAPPY_HOUR
          description: Typ der Rabattaktion
        discountAmount:
          type: number
          format: decimal
          description: Durch diese Aktion gesparter Betrag
        affectedItems:
          type: array
          items:
            type: object
            properties:
              productId:
                type: string
              quantity:
                type: integer
        savingsMessage:
          type: string
          description: >
            Anzeigetext für PoS und Bon.
            Beispiel: "Aktion '3 für 2 Softdrinks': Sie sparen 2,50 €!"

    LineItem:
      type: object
      properties:
        productId:
          type: string
        productName:
          type: string
        quantity:
          type: integer
        unitPrice:
          type: number
          format: decimal
        originalSubtotal:
          type: number
          format: decimal
        discountApplied:
          type: number
          format: decimal
        finalSubtotal:
          type: number
          format: decimal

    ActivePromotionsResponse:
      type: object
      properties:
        promotions:
          type: array
          items:
            $ref: '#/components/schemas/PromotionDefinition'
        lastUpdated:
          type: string
          format: date-time

    PromotionDefinition:
      type: object
      required:
        - promotionId
        - name
        - type
        - rules
        - validFrom
        - validTo
        - active
      properties:
        promotionId:
          type: string
        name:
          type: string
        description:
          type: string
        type:
          type: string
          enum:
            - PERCENTAGE
            - BUY_X_GET_Y_FREE
            - BUNDLE_PRICE
            - HAPPY_HOUR
        rules:
          type: object
          description: >
            Regelwerk der Aktion (typ-abhängig).
            TODO: Je nach Aktionstyp unterschiedliche Felder definieren.
        validFrom:
          type: string
          format: date-time
        validTo:
          type: string
          format: date-time
        active:
          type: boolean
        applicableTrucks:
          type: array
          items:
            type: string
          description: >
            Liste der Truck-IDs, für die die Aktion gilt.
            Leer = gilt für alle Trucks.
        applicableCategories:
          type: array
          items:
            type: string
        applicableProducts:
          type: array
          items:
            type: string
```

### Vor- und Nachbedingungen

**Vorbedingungen (Rabattberechnung):**

| # | Bedingung |
|---|-----------|
| 1 | TODO: z. B. Der Warenkorb enthält mindestens einen Artikel |
| 2 | TODO: z. B. Alle Artikel haben gültige Produkt-IDs und positive Preise |
| 3 | TODO: z. B. Aktionsdefinitionen sind lokal verfügbar (Cache) |
| 4 | TODO: z. B. Der Zeitstempel ist gesetzt (für zeitbasierte Aktionen) |

**Nachbedingungen (Erfolgsfall):**

| # | Bedingung |
|---|-----------|
| 1 | TODO: z. B. Der Endpreis ist korrekt berechnet und ≥ 0 |
| 2 | TODO: z. B. Jede angewendete Aktion ist in appliedPromotions dokumentiert |
| 3 | TODO: z. B. Jeder Posten hat eine nachvollziehbare Preisaufschlüsselung |
| 4 | TODO: z. B. Ein savingsMessage-Text ist für jeden angewendeten Rabatt vorhanden |
| 5 | TODO: z. B. originalTotal = Summe aller originalSubtotals |
| 6 | TODO: z. B. finalTotal = originalTotal - totalDiscount |

**Nachbedingungen (Fehlerfall):**

| # | Bedingung |
|---|-----------|
| 1 | TODO: z. B. Bei ungültigem Warenkorb wird HTTP 400 mit Fehlerbeschreibung zurückgegeben |
| 2 | TODO: z. B. Es werden keine Teilberechnungen zurückgegeben |

---

## 3.4 Kommunikationsentscheidung

**Gewählte Kommunikationsart:** TODO: z. B. Synchroner lokaler Funktionsaufruf (Offline-fähig) + REST-Sync für Aktionsdefinitionen

**Begründung:**

TODO: z. B.:
- Die Rabattberechnung ist Teil des Verkaufsprozesses und muss sofort ein Ergebnis liefern → synchron
- Offline-Betrieb erfordert, dass die Berechnung lokal auf dem Truck stattfindet
- Aktionsdefinitionen werden asynchron vom Promotion Service synchronisiert und lokal gecacht

**Verworfene Alternativen:**

| Alternative | Grund der Ablehnung |
|-------------|---------------------|
| TODO: z. B. Zentrale Berechnung via REST | TODO: z. B. Nicht offline-fähig; zu hohe Latenz im Verkaufsprozess |
| TODO | TODO |

### ADR-Y: Rabattberechnung lokal vs. zentral

| Feld | Inhalt |
|------|--------|
| **Status** | TODO: Vorgeschlagen / Akzeptiert |
| **Kontext** | Die Rabattberechnung muss im Verkaufsprozess stattfinden. Food Trucks sind häufig offline. Die Berechnung muss in unter x ms abgeschlossen sein. |
| **Entscheidung** | TODO |
| **Begründung** | TODO |
| **Konsequenzen** | TODO |
| **Alternativen** | TODO |

---

# 4. Annahmen

<!-- 
Dokumentieren Sie hier alle Annahmen, die Sie bei der Ausarbeitung getroffen haben.
Die Aufgabenstellung verlangt dies explizit.
-->

| # | Annahme | Begründung |
|---|---------|------------|
| A1 | TODO: z. B. Ein Truck hat maximal ~50 verschiedene Produkte im Sortiment | TODO: Vereinfacht die lokale Bestandsverwaltung |
| A2 | TODO: z. B. Rabattaktionen ändern sich höchstens einige Male pro Tag | TODO: Delta-Sync ist ausreichend |
| A3 | TODO: z. B. Es können maximal 10 Aktionen gleichzeitig aktiv sein | TODO: Begrenzung der Berechnungskomplexität |
| A4 | TODO: z. B. Rabattaktionen sind nicht kumulierbar (nur eine pro Artikel) | TODO: Oder: Regeln zur Priorisierung definieren |
| A5 | TODO | TODO |

---

# 5. Glossar (Ergänzungen)

| Begriff | Definition |
|---------|------------|
| Promotion Engine | Komponente zur Berechnung von Rabatten auf einen Warenkorb |
| Inventory Sync | Mechanismus zur Übermittlung von Bestandsänderungen vom Truck an die Zentrale |
| Eventual Consistency | Konsistenzmodell, bei dem Daten zeitverzögert synchronisiert werden |
| Idempotenz | Eigenschaft einer Operation, bei mehrfacher Ausführung dasselbe Ergebnis zu liefern |
| Strategy Pattern | Entwurfsmuster zur Kapselung austauschbarer Algorithmen |
| Delta-Sync | Synchronisierung, bei der nur Änderungen seit dem letzten Abgleich übertragen werden |

---

# 6. Quellen und KI-Werkzeuge

## Externe Quellen

| Quelle | Verwendung |
|--------|------------|
| TODO | TODO |
| arc42 Template | Strukturierung der Dokumentation |
| OpenAPI Specification 3.0.3 | Schnittstellendefinition |
| AsyncAPI Specification 2.6.0 | Nachrichtenbasierte Schnittstellendefinition |

## KI-Werkzeuge

| Werkzeug | Einsatzzweck | Erfahrung |
|----------|-------------|-----------|
| TODO: z. B. Claude | TODO: z. B. Erstellung der Dokumentationsvorlage | TODO: z. B. Gute Strukturierung, Details mussten angepasst werden |
| TODO | TODO | TODO |

### Prompts und Interaktionsprozess

TODO: Dokumentieren Sie hier Ihre Prompts, Ergebnisse und Erfahrungen beim Einsatz von KI-Tools.
