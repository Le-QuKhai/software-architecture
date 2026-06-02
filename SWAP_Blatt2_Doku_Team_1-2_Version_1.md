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

Die Promotion Engine ist die zentrale Komponente zur Rabattberechnung im Food-Truck-PoS-System. Sie nimmt einen Warenkorb entgegen, prüft alle aktiven Rabattaktionen auf Anwendbarkeit und berechnet den Endpreis unter Berücksichtigung aller gültigen Rabatte. Die Engine gibt den reduzierten Preis sowie eine detaillierte Aufschlüsselung der angewendeten Rabatte zurück, damit der gewährte Vorteil sowohl auf dem PoS-Display als auch auf dem Bon angezeigt werden kann (z. B. „Durch unsere Aktion '3 für 2 Softdrinks' haben Sie 2,50 € gespart!").

  
Die Promotion Engine läuft **lokal auf dem Tablet/PoS-Device**, um vollständige Offline-Fähigkeit zu gewährleisten. Aktionsdefinitionen werden vorab vom zentralen Promotion Service synchronisiert und in einem lokalen Cache vorgehalten.

---
### Neue / geänderte Anforderungen gegenüber Aufgabe 1

Durch die Verfeinerung der Promotion Engine ergeben sich folgende neue bzw. präzisierte Anforderungen:


| ID  | Anforderung                                                                                                                 | Neu/Geändert              | Begründung                                              |
| --- | --------------------------------------------------------------------------------------------------------------------------- | ------------------------- | ------------------------------------------------------- |
| F2a | Die Rabattberechnung muss eine vollständige Aufschlüsselung aller angewendeten Aktionen zurückgeben, nicht nur den Endpreis | Neu (Verfeinerung von F2) | Marketing möchte den Rabattvorteil auf dem Bon anzeigen |
| F2b | Für jede angewendete Aktion wird ein menschenlesbarer Bon-Text (savingsMessage) generiert | Neu | Anforderung aus Aufgabenstellung: „Durch unsere Aktion XYZ haben Sie X € gespart!" |
| F11a | Es muss definiert sein, in welcher Reihenfolge mehrere Aktionen auf denselben Warenkorb angewendet werden (Prioritätsregel) | Neu (Verfeinerung von F11) | Bei mehreren gleichzeitig gültigen Aktionen muss das Ergebnis deterministisch sein |
| F11b | Es muss definiert sein, ob Aktionen kombinierbar sind oder sich gegenseitig ausschließen | Neu (Verfeinerung von F11) | Verhindert doppelte Rabattierung und stellt Kalkulierbarkeit sicher |
| NF4a | Die Rabattberechnung muss in unter 50 ms abgeschlossen sein (für max. 20 Positionen, 10 aktive Aktionen) | Geändert (Konkretisierung von NF4) | Messbares Kriterium statt „unter x Millisekunden" |
| NF13 | Die Rabattberechnung muss deterministisch sein: identische Eingaben führen immer zum identischen Ergebnis | Neu | Notwendig für Nachvollziehbarkeit, Testbarkeit und TSE-Compliance |

### Neuer Anwendungsfall: UC3 – Rabattberechnung am PoS durchführen

  

**Kurzbeschreibung:** Die PoS App berechnet den Endpreis eines Warenkorbs unter Berücksichtigung aller aktiven Rabattaktionen, zeigt die Ersparnis an und gibt die Daten für den Bon weiter.

**Akteure:** KassiererIn, (indirekt: Marketing)

**Vorbedingungen:**
- PoS App ist gestartet und betriebsbereit
- Mindestens ein Artikel ist im Warenkorb
- Aktionsdefinitionen sind im lokalen Cache vorhanden (auch wenn Cache leer: Berechnung ohne Rabatte, kein Fehler)

**Hauptablauf:**
1. Kassierer:in hat Artikel im Warenkorb erfasst
2. Kassierer:in drückt „Bezahlen" oder die Berechnung wird automatisch bei jedem Artikelhinzufügen ausgelöst
3. PoS App sendet Warenkorb (Artikel, Mengen, Preise, Truck-ID, Zeitstempel) an die lokale Promotion Engine
4. Promotion Engine gruppiert Artikel und gleicht sie mit aktiven Aktionen ab
5. Für jede anwendbare Aktion wird der Rabatt berechnet (Prioritätsreihenfolge, Kombinierbarkeitsregeln)
6. Ergebnis mit Endpreis, Aufschlüsselung und Bon-Texten wird an die PoS App zurückgegeben
7. PoS App zeigt dem Kassierer den Endpreis und die Ersparnis an
8. Nach Zahlung werden die Bon-Texte auf den Beleg gedruckt

**Alternativabläufe:**
- 4a: Keine Aktion ist anwendbar -> Endpreis = Originalpreis, leere `appliedPromotions`-Liste
- 4b: Promotion Cache ist leer (z. B. Erstinbetriebnahme ohne Sync) -> Berechnung ohne Rabatte, Warnung im Log

**Nachbedingungen:**
- Endpreis ist berechnet und angezeigt
- Alle angewendeten Aktionen sind dokumentiert
- Bon-Texte sind für den Druck bereit

---

### Konkrete Beispiel-Rabattaktionen

**Rabattaktion 1: „Kaufe 3 Softdrinks, zahle nur 2" (3-für-2)**

| Eigenschaft | Wert |
|-------------|------|
| Aktions-ID | `promo-3for2-softdrinks-062026` |
| Aktionszeitraum | 01.06.2026 – 30.06.2026 |
| Betroffene Produkte | Alle Produkte der Kategorie „Softdrinks" |
| Regel | Bei je 3 Softdrinks wird der günstigste kostenlos. Wiederholbar: bei 6 Stück werden 2 frei usw. |
| Kombinierbar | Nein – nicht mit anderen Aktionen auf denselben Artikel |
| Priorität | 1 (wird zuerst angewendet) |
| Bon-Text | „Aktion '3 für 2 Softdrinks': Sie sparen X,XX €!" |
| Gültig für | Alle Trucks |

**Rabattaktion 2: „Happy Hour – 20 % auf alles" (Prozentualer Rabatt)**

| Eigenschaft | Wert |
|-------------|------|
| Aktions-ID | `promo-happyhour-20pct` |
| Aktionszeitraum | Täglich 14:00 – 16:00 Uhr |
| Betroffene Produkte | Alle Produkte |
| Regel | 20 % Rabatt auf den Preis jedes Artikels |
| Kombinierbar | Ja – wird nach produktspezifischen Aktionen auf den bereits reduzierten Preis angewendet |
| Priorität | 2 (wird nach Priorität 1 angewendet) |
| Bon-Text | „Happy Hour (14–16 Uhr): Sie sparen X,XX €!" |
| Gültig für | Alle Trucks |

---

### Zugeordnete Anforderungen


| ID | Anforderung | Bezug |
|----|-------------|-------|
| F2 | Das System berechnet automatisch den Endpreis unter Berücksichtigung aktiver Rabattaktionen | Funktional |
| F2a | Vollständige Aufschlüsselung aller angewendeten Aktionen in der Antwort | Funktional (neu) |
| F2b | Generierung von Bon-Texten (savingsMessage) pro Aktion | Funktional (neu) |
| F10 | Rabattaktionen können zentral gepflegt werden | Funktional |
| F11 | Unterstützung verschiedener Aktionstypen (Mengenrabatt, Bundle, Prozent, Happy Hour) | Funktional |
| F11a | Prioritätsbasierte Anwendungsreihenfolge bei mehreren Aktionen | Funktional (neu) |
| F11b | Kombinierbarkeitsregeln zwischen Aktionen | Funktional (neu) |
| F12 | Aktualisierungen werden zeitnah an alle Trucks verteilt | Funktional |
| NF4a | Preisberechnung und Rabattprüfung in unter 50 ms | Performance |
| NF8 | Neue Aktionstypen ohne manuelle Truck-Updates einführbar | Maintainability |
| NF13 | Deterministische Berechnung | Korrektheit (neu) |

---

### Schnittstellen (extern)

| Schnittstelle | Richtung | Protokoll | Beschreibung |
|---------------|----------|-----------|--------------|
| `POST /api/v1/promotions/calculate` | Eingehend (PoS App → Engine) | HTTP/REST (lokal) | PoS App sendet Warenkorb zur Preisberechnung. Synchroner Aufruf, Antwort enthält Endpreis und Rabattdetails. |
| `GET /api/v1/promotions/active` | Eingehend (Engine → Promotion Service) | HTTP/REST (Netzwerk) | Truck ruft aktive Aktionsdefinitionen vom zentralen Promotion Service ab (Delta-Sync). |
| Response `CartResponse` | Ausgehend (Engine → PoS App) | HTTP/REST (lokal) | Antwort mit `originalTotal`, `totalDiscount`, `finalTotal`, `appliedPromotions[]` und `lineItems[]`. |

---

### Datenfluss: Was muss der Client senden?

| Schnittstelle | Richtung | Protokoll | Beschreibung |
|---------------|----------|-----------|--------------|
| `POST /api/v1/promotions/calculate` | Eingehend (PoS App → Engine) | HTTP/REST (lokal) | PoS App sendet Warenkorb zur Preisberechnung. Synchroner Aufruf, Antwort enthält Endpreis und Rabattdetails. |
| `GET /api/v1/promotions/active` | Eingehend (Engine → Promotion Service) | HTTP/REST (Netzwerk) | Truck ruft aktive Aktionsdefinitionen vom zentralen Promotion Service ab (Delta-Sync). |
| Response `CartResponse` | Ausgehend (Engine → PoS App) | HTTP/REST (lokal) | Antwort mit `originalTotal`, `totalDiscount`, `finalTotal`, `appliedPromotions[]` und `lineItems[]`. |

**Annahme A6:** Die PoS App übergibt Bruttopreise (inkl. MwSt.) als `unitPrice`. Die Rabattberechnung erfolgt auf Bruttobasis.

---

### Datenfluss: Was wird an den Client zurückgesendet?

| Feld | Typ | Beschreibung |
|------|-----|--------------|
| `originalTotal` | Decimal | Gesamtpreis des Warenkorbs vor allen Rabatten |
| `totalDiscount` | Decimal | Summe aller gewährten Rabatte |
| `finalTotal` | Decimal | Endpreis nach Abzug aller Rabatte. Invariante: `finalTotal = originalTotal − totalDiscount` |
| `appliedPromotions` | Array\<AppliedPromotion\> | Liste aller angewendeten Aktionen mit `promotionId`, `promotionName`, `promotionType`, `discountAmount`, `affectedItems[]` und **`savingsMessage`** |
| `lineItems` | Array\<LineItem\> | Aufgeschlüsselte Einzelposten mit `originalSubtotal`, `discountApplied` und `finalSubtotal` pro Artikel |

Jede `AppliedPromotion` enthält ein Feld **`savingsMessage`** – einen vorgefertigten Anzeigetext für den Bon:
- „Aktion '3 für 2 Softdrinks': Sie sparen 2,00 €!"
- „Happy Hour (14–16 Uhr): Sie sparen 3,28 €!"

---

### Qualitäts- und Leistungsmerkmale

| Merkmal | Beschreibung | Messbar |
|---------|--------------|---------|
| Performance | Gesamte Berechnung darf den Verkaufsfluss nicht spürbar verzögern | < 50 ms für ≤ 20 Positionen und ≤ 10 aktive Aktionen |
| Korrektheit | Exakte, cent-genaue Berechnung. Keine Rundungsfehler. | `finalTotal = originalTotal − totalDiscount`; Summenprüfung über LineItems |
| Determinismus | Identische Eingaben → identisches Ergebnis | Automatisierte Tests mit festen Testwarenkörben |
| Erweiterbarkeit | Neue Aktionstypen ohne Code-Änderung am Truck einführbar | Strategy Pattern: neue Strategie über Aktionsdefinition vom Server |
| Offline-Fähigkeit | Berechnung funktioniert vollständig ohne Netzwerk | Lokaler PromotionCache hält alle Definitionen vor |

---

### Ablageort / Deployment

Die Promotion Engine läuft **lokal auf dem Tablet/PoS-Device** als eingebettetes Modul der PoS App. Aktionsdefinitionen werden bei bestehender Verbindung vom zentralen Promotion Service synchronisiert und im lokalen PromotionCache (SQLite) persistiert. Die Berechnung selbst benötigt keinen Netzwerkzugriff.

---

## 3.2 Whitebox-Sicht (Ebene 1)

### Übersichtsdiagramm


![[Pasted image 20260602072944.png]]

---


### Enthaltene Bausteine

  
| Baustein                | Verantwortung                                                                                                                                                                                                                                                                                                                                                     |
| ----------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **CartAnalyzer**        | Nimmt den `CartRequest` entgegen, validiert die Eingabedaten (nicht-leerer Warenkorb, positive Preise, gültige Mengen) und gruppiert die Artikel nach Produktkategorie und Produkt-ID. Die Gruppierung ermöglicht effizientes Matching mit Aktionsdefinitionen.                                                                                                   |
| **PromotionMatcher**    | Gleicht die gruppierten Warenkorbdaten mit den im PromotionCache gespeicherten aktiven Aktionsdefinitionen ab. Prüft für jede Aktion alle Anwendungsbedingungen: Zeitraum (validFrom/validTo), Uhrzeit (Happy Hour), Mindestmengen, betroffene Produkte/Kategorien und Truck-Zugehörigkeit. Gibt eine nach Priorität sortierte Liste anwendbarer Aktionen zurück. |
| **PromotionCache**      | Hält die vom zentralen Promotion Service synchronisierten Aktionsdefinitionen lokal auf dem Gerät vor. Persistiert in SQLite, sodass Daten auch nach Geräteneustart verfügbar sind. Unterstützt Delta-Sync über `lastSyncTimestamp` (nur Änderungen seit letztem Sync abrufen).                                                                                   |
| **DiscountCalculator**  | Zentrale Berechnungskomponente. Iteriert über die anwendbaren Aktionen in Prioritätsreihenfolge, prüft Kombinierbarkeitsregeln und delegiert die eigentliche Berechnung an die passende `PromotionStrategy`-Implementierung. Stellt sicher, dass nicht-kombinierbare Aktionen sich gegenseitig ausschließen.                                                      |
| **PriceAggregator**     | Summiert alle berechneten Einzelrabatte, berechnet `originalTotal`, `totalDiscount` und `finalTotal`, erstellt die `LineItem`-Aufschlüsselung und führt die Konsistenzprüfung durch (`finalTotal = originalTotal − totalDiscount`).                                                                                                                               |
| **BonMessageGenerator** | Erzeugt für jede angewendete Aktion einen menschenlesbaren Anzeigetext (`savingsMessage`) für das PoS-Display und den Bon. Erzeugt außerdem einen Gesamtersparnistext.                                                                                                                                                                                            |

---
### Interne Kommunikationsbeziehungen

  

| Von                         | Nach                | Art                                      | Daten                         | Beschreibung                                                           |
| --------------------------- | ------------------- | ---------------------------------------- | ----------------------------- | ---------------------------------------------------------------------- |
| PoS App                     | CartAnalyzer        | Synchroner HTTP-Call (lokal)             | `CartRequest`                 | Einstiegspunkt: PoS App ruft `POST /api/v1/promotions/calculate` auf   |
| CartAnalyzer                | PromotionMatcher    | Synchroner Funktionsaufruf               | `GroupedCart`                 | Übergibt validierte und gruppierte Warenkorbdaten                      |
| PromotionMatcher            | PromotionCache      | Synchroner Lesezugriff (SQLite)          | `PromotionDefinition[]`       | Liest alle aktiven Aktionsdefinitionen aus dem lokalen Cache           |
| PromotionMatcher            | DiscountCalculator  | Synchroner Funktionsaufruf               | `ApplicablePromotion[]`       | Übergibt sortierte Liste anwendbarer Aktionen mit zugehörigen Artikeln |
| DiscountCalculator          | PromotionStrategy   | Synchroner Funktionsaufruf (Polymorphie) | `CartItem[]`, `PromotionRule` | Delegiert Berechnung an konkrete Strategie (Strategy Pattern)          |
| DiscountCalculator          | PriceAggregator     | Synchroner Funktionsaufruf               | `DiscountResult[]`            | Übergibt berechnete Einzelrabatte zur Aggregation                      |
| PriceAggregator             | BonMessageGenerator | Synchroner Funktionsaufruf               | `AppliedPromotion[]`          | Übergibt angewendete Aktionen zur Textgenerierung                      |
| BonMessageGenerator         | PriceAggregator     | Rückgabewert                             | `String[]`                    | Liefert `savingsMessage`-Texte zurück                                  |
| PriceAggregator             | PoS App             | HTTP-Response                            | `CartResponse`                | Vollständige Antwort mit Endpreis, Aufschlüsselung und Bon-Texten      |
| Promotion Service (zentral) | PromotionCache      | REST GET (Netzwerk)                      | `PromotionDefinition[]`       | Delta-Sync: nur Änderungen seit letztem Sync                           |


---
### Klassendiagramm: Rabattaktionstypen

![[Pasted image 20260602072907.png]]
**Berechnungslogik je Strategie:**

| Strategie              | Eingabe                                                     | Logik                                                                                                                                                                                                    | Ausgabe                                                     |
| ---------------------- | ----------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------- |
| **BuyXGetYFree**       | Softdrink-Artikel im Warenkorb, `buyCount=3`, `freeCount=1` | Sortiere betroffene Artikel nach Preis aufsteigend. Für je `buyCount` Artikel: die `freeCount` günstigsten werden kostenlos. Wiederholbar bei Vielfachen.                                                | `discountAmount` = Summe der Preise der kostenlosen Artikel |
| **PercentageDiscount** | Betroffene Artikel, `percentage=20`                         | `rabatt = unitPrice × (percentage / 100)` pro Artikel. Auf jede Einheit einzeln angewendet.                                                                                                              | `discountAmount` = Summe aller Einzelrabatte                |
| **BundlePrice**        | Bundle-Artikel, `bundleItems`, `bundlePrice`                | Prüfe ob alle Bundle-Artikel im Warenkorb. Wenn ja: Gesamtpreis der Einzelpreise wird durch `bundlePrice` ersetzt.                                                                                       | `discountAmount` = Σ(Einzelpreise) − bundlePrice            |
| **HappyHourDiscount**  | Alle Artikel, `percentage`, `startTime`, `endTime`          | Wie PercentageDiscount, aber nur anwendbar wenn `timestamp` zwischen `startTime` und `endTime` liegt. Wird auf den ggf. bereits reduzierten Preis angewendet (weil kombinierbar + niedrigere Priorität). | `discountAmount` = Summe der prozentualen Rabatte           |

---
### Sequenzdiagramm: Rabattberechnung am Beispiel "3 für 2"

![[Pasted image 20260602074550.png]]
---
### Durchgerechnetes Berechnungsbeispiel
  
**Warenkorb (Eingabe):**
  
| Artikel           | Menge | Einzelpreis | Kategorie  | Zwischensumme |
| ----------------- | ----- | ----------- | ---------- | ------------- |
| Cola 0,33l        | 3     | 2,50 €      | Softdrinks | 7,50 €        |
| Fanta 0,33l       | 1     | 2,00 €      | Softdrinks | 2,00 €        |
| Classic Burger    | 1     | 8,90 €      | Food       | 8,90 €        |
| **originalTotal** |       |             |            | **18,40 €**   |

**Schritt 1 – „3 für 2 Softdrinks" (Priorität 1, nicht kombinierbar):**

4 Softdrinks im Warenkorb. Sortiert nach Preis: Fanta (2,00 €), Cola, Cola, Cola (je 2,50 €). Für je 3 Stück wird der günstigste frei -> Fanta (2,00 €) wird kostenlos. Der 4. Softdrink (Cola) bleibt unberührt, da keine weitere 3er-Gruppe vollständig ist.

-> **Rabatt Schritt 1: 2,00 €**

**Schritt 2 – „Happy Hour 20 %" (Priorität 2, kombinierbar):**

20 % auf alle Artikel, angewendet auf den Preis nach Schritt 1:

| Artikel             | Preis nach Schritt 1  | 20 % Rabatt |
| ------------------- | --------------------- | ----------- |
| Cola 0,33l (3×)     | 7,50 €                | 1,50 €      |
| Fanta 0,33l (1×)    | 0,00 € (bereits frei) | 0,00 €      |
| Classic Burger (1×) | 8,90 €                | 1,78 €      |

-> **Rabatt Schritt 2: 3,28 €**

**Endergebnis:**

|                               | Betrag      |
| ----------------------------- | ----------- |
| Originalpreis                 | 18,40 €     |
| − Rabatt „3 für 2 Softdrinks" | −2,00 €     |
| − Rabatt „Happy Hour 20 %"    | −3,28 €     |
| **Gesamtrabatt**              | **−5,28 €** |
| **Endpreis**                  | **13,12 €** |

**Bon-Texte:**
- „Aktion '3 für 2 Softdrinks': Sie sparen 2,00 €!"
- „Happy Hour (14–16 Uhr): Sie sparen 3,28 €!"
- „**Gesamtersparnis: 5,28 €**"


---

## 3.3 Schnittstellenspezifikation

### API-Spezifikation (OpenAPI)

```yaml
openapi: 3.1.1

info:

  title: Promotion Engine API

  version: 1.0.0

  description: >

    Lokale API zur Berechnung von Rabatten auf einen Warenkorb

    im Food-Truck-PoS-System. Laeuft lokal auf dem PoS-Device.

  contact:

    name: Group 1-2

servers:

  - url: http://localhost:8080

    description: Lokaler PoS-Device Server

paths:

  /api/v1/promotions/calculate:

    post:

      summary: Warenkorb-Rabatte berechnen

      operationId: calculateCartPromotions

      description: >

        Berechnet den Endpreis des Warenkorbs unter Beruecksichtigung

        aller aktiven Rabattaktionen.

      tags:

        - Rabattberechnung

      requestBody:

        required: true

        content:

          application/json:

            schema:

              $ref: '#/components/schemas/CartRequest'

      responses:

        '200':

          description: Erfolgreiche Berechnung

          content:

            application/json:

              schema:

                $ref: '#/components/schemas/CartResponse'

  

        '400':

          description: Ungueltiger Warenkorb

          content:

            application/json:

              schema:

                $ref: '#/components/schemas/ErrorResponse'

  

        '500':

          description: Interner Fehler

          content:

            application/json:

              schema:

                $ref: '#/components/schemas/ErrorResponse'

  

  /api/v1/promotions/active:

    get:

      summary: Aktive Aktionen abrufen

      operationId: getActivePromotions

      description: >

        Wird vom Truck aufgerufen, um Aktionsdefinitionen

        in den lokalen Cache zu synchronisieren.

      tags:

        - Aktionssynchronisierung

      parameters:

        - name: truckId

          in: query

          required: false

          schema:

            type: string

          description: Filtert auf truck-spezifische Aktionen

  

        - name: since

          in: query

          required: false

          schema:

            type: string

            format: date-time

          description: Delta-Sync seit diesem Zeitpunkt

  

      responses:

        '200':

          description: Aktive Aktionsdefinitionen

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

          description: Eindeutige Truck-Kennung

  

        timestamp:

          type: string

          format: date-time

          description: Verkaufszeitpunkt

  

        items:

          type: array

          minItems: 1

          items:

            $ref: '#/components/schemas/CartItem'

  

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

          format: double

          minimum: 0.01

          description: Bruttopreis in Euro

  

        category:

          type: string

          description: Produktkategorie

  

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

          format: double

  

        totalDiscount:

          type: number

          format: double

  

        finalTotal:

          type: number

          format: double

          description: finalTotal = originalTotal - totalDiscount

  

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

  

        promotionType:

          type: string

          enum:

            - PERCENTAGE

            - BUY_X_GET_Y_FREE

            - BUNDLE_PRICE

            - HAPPY_HOUR

  

        discountAmount:

          type: number

          format: double

  

        affectedItems:

          type: array

          items:

            $ref: '#/components/schemas/AffectedItem'

  

        savingsMessage:

          type: string

  

    AffectedItem:

      type: object

      properties:

        productId:

          type: string

  

        quantity:

          type: integer

  

    LineItem:

      type: object

      required:

        - productId

        - productName

        - quantity

        - unitPrice

        - originalSubtotal

        - discountApplied

        - finalSubtotal

      properties:

        productId:

          type: string

  

        productName:

          type: string

  

        quantity:

          type: integer

  

        unitPrice:

          type: number

          format: double

  

        originalSubtotal:

          type: number

          format: double

  

        discountApplied:

          type: number

          format: double

  

        finalSubtotal:

          type: number

          format: double

  

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

        - combinable

        - priority

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

          oneOf:

            - $ref: '#/components/schemas/BuyXGetYFreeRule'

            - $ref: '#/components/schemas/PercentageRule'

            - $ref: '#/components/schemas/BundlePriceRule'

            - $ref: '#/components/schemas/HappyHourRule'

  

        validFrom:

          type: string

          format: date-time

  

        validTo:

          type: string

          format: date-time

  

        active:

          type: boolean

  

        combinable:

          type: boolean

  

        priority:

          type: integer

  

        applicableTrucks:

          type: array

          items:

            type: string

  

        applicableCategories:

          type: array

          items:

            type: string

  

        applicableProducts:

          type: array

          items:

            type: string

  

    BuyXGetYFreeRule:

      type: object

      required:

        - buyCount

        - freeCount

      properties:

        buyCount:

          type: integer

          minimum: 2

  

        freeCount:

          type: integer

          minimum: 1

  

    PercentageRule:

      type: object

      required:

        - percentage

      properties:

        percentage:

          type: number

          minimum: 0.01

          maximum: 100

  

    BundlePriceRule:

      type: object

      required:

        - bundleItems

        - bundlePrice

      properties:

        bundleItems:

          type: array

          minItems: 2

          items:

            type: string

  

        bundlePrice:

          type: number

          minimum: 0.01

  

    HappyHourRule:

      type: object

      required:

        - percentage

        - startTime

        - endTime

      properties:

        percentage:

          type: number

          minimum: 0.01

          maximum: 100

  

        startTime:

          type: string

          pattern: "^\\d{2}:\\d{2}$"

  

        endTime:

          type: string

          pattern: "^\\d{2}:\\d{2}$"

  

    ErrorResponse:

      type: object

      required:

        - error

        - message

      properties:

        error:

          type: string

  

        message:

          type: string

  

        details:

          type: array

          items:

            type: string
```

### Vor- und Nachbedingungen

**Vorbedingungen (`POST /api/v1/promotions/calculate`):**
  

| # | Bedingung | Prüfung durch |
|---|-----------|---------------|
| V1 | Der Warenkorb enthält mindestens einen Artikel (`items.length ≥ 1`) | CartAnalyzer |
| V2 | Alle Artikel haben eine nicht-leere `productId` | CartAnalyzer |
| V3 | Alle Einzelpreise sind positiv (`unitPrice > 0`) | CartAnalyzer |
| V4 | Alle Mengen sind positiv (`quantity ≥ 1`) | CartAnalyzer |
| V5 | Der `timestamp` ist ein gültiger ISO-8601-Zeitstempel mit Zeitzone | CartAnalyzer |
| V6 | Die `truckId` ist gesetzt und nicht leer | CartAnalyzer |
| V7 | Aktionsdefinitionen sind im PromotionCache vorhanden (bei leerem Cache: Berechnung ohne Rabatte, kein Fehler) | PromotionMatcher |

**Nachbedingungen (Erfolgsfall, HTTP 200):**

| # | Bedingung |
|---|-----------|
| N1 | `finalTotal ≥ 0` – der Endpreis ist niemals negativ |
| N2 | `finalTotal = originalTotal − totalDiscount` (Konsistenzinvariante) |
| N3 | `originalTotal = Σ(lineItem.originalSubtotal)` für alle LineItems |
| N4 | `totalDiscount = Σ(appliedPromotion.discountAmount)` für alle Aktionen |
| N5 | Für jede angewendete Aktion existiert genau ein `savingsMessage`-Text |
| N6 | Für jeden Artikel im Request existiert genau ein LineItem in der Response |
| N7 | `lineItem.finalSubtotal = lineItem.originalSubtotal − lineItem.discountApplied` für jeden Posten |
| N8 | Nicht-kombinierbare Aktionen wurden nicht auf denselben Artikel zusammen mit anderen Aktionen angewendet |
| N9 | Aktionen wurden in Prioritätsreihenfolge angewendet |

**Nachbedingungen (Fehlerfall, HTTP 400/500):**

| # | Bedingung |
|---|-----------|
| NE1 | Bei ungültigem Warenkorb: HTTP 400 mit `ErrorResponse` (Fehlerkategorie + Details) |
| NE2 | Es werden keine Teilberechnungen zurückgegeben |
| NE3 | Bei internem Fehler: HTTP 500 mit `ErrorResponse` |
  
---

## 3.4 Kommunikationsentscheidung

### Gewählte Kommunikationsart

| Kommunikationspfad                               | Art                         | Protokoll               |
| ------------------------------------------------ | --------------------------- | ----------------------- |
| PoS App -> Promotion Engine (Berechnung)         | **Synchron**                | HTTP/REST auf localhost |
| Promotion Service → PromotionCache (Aktionssync) | **Synchron (Pull-basiert)** | HTTP/REST über Netzwerk |

---

### Begründung
  
**Rabattberechnung – synchron, lokal:**

Die Rabattberechnung ist ein synchroner Vorgang, weil der Kassierer im Verkaufsprozess den Endpreis sofort sehen muss, bevor die Zahlung eingeleitet wird. Der Vorgang ist blockierend: ohne Ergebnis kann der Verkauf nicht fortgesetzt werden. Eine asynchrone Berechnung (z. B. via Message Queue) würde den Verkaufsfluss unterbrechen und ist für einen Request-Response-Vorgang mit < 50 ms Antwortzeit nicht sinnvoll.

Die Berechnung läuft lokal auf dem PoS-Device, weil die Offline-Fähigkeit das wichtigste Qualitätsziel ist (Priorität 1). Ein zentraler Cloud-Dienst wäre bei Verbindungsabbruch nicht erreichbar.


**Aktionssynchronisierung - Pull-basiert, Delta-Sync:**

Der Truck fragt in regelmäßigen Intervallen (z. B. alle 5 Minuten bei bestehender Verbindung) den zentralen Promotion Service nach Änderungen. Der Parameter `since` ermöglicht Delta-Sync: nur Aktionen, die seit dem letzten Sync geändert wurden, werden übertragen.

---

### Verworfene Alternativen

| Alternative                                               | Vorteile                                                                        | Grund der Ablehnung                                                                                                                                                                                                                    |
| --------------------------------------------------------- | ------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Zentrale Rabattberechnung via REST (Cloud)**            | Einheitliche Berechnungslogik; keine lokale Logik nötig; Updates sofort wirksam | **Nicht offline-fähig.** Bei Verbindungsabbruch keine Preisberechnung möglich. Widerspricht direkt den Qualitätszielen 1 (Reliability/Offline) und 3 (Usability). Zudem erhöhte Latenz durch Netzwerk-Roundtrip.                       |
| **Asynchrone lokale Berechnung (Event-basiert)**          | Entkopplung der Komponenten; theoretisch besser skalierbar                      | **Unnötige Komplexität** für einen Vorgang, der sofort ein Ergebnis liefern muss. Der Kassierer kann nicht warten, bis ein Event verarbeitet wurde. Message-Queue-Overhead auf leistungsschwacher Hardware kontraproduktiv (NF3).      |
| **Push-basierte Aktionssynchronisierung (WebSocket/SSE)** | Aktionen sofort nach Erstellung auf Trucks verfügbar; minimale Verzögerung      | **Instabile Verbindung.** WebSocket/SSE erfordern dauerhafte Netzwerkverbindung, die bei Food Trucks nicht gewährleistet ist. Reconnect-Logik wäre komplex und fehleranfällig. Pull mit Delta-Sync ist robuster.                       |
| **gRPC statt REST**                                       | Höhere Performance durch binäres Protokoll; starke Typisierung                  | **Höherer Implementierungsaufwand** ohne signifikanten Vorteil bei lokaler Kommunikation. REST ist einfacher zu debuggen, die Tools sind verbreiteter, und die Performance-Differenz bei localhost-Kommunikation ist vernachlässigbar. |
| **GraphQL statt REST**                                    | Flexible Abfragen; Client bestimmt Datenumfang                                  | **Over-Engineering** für zwei fest definierte Endpoints. Die Datenstruktur ist fix (immer vollständige CartResponse). GraphQL-Overhead (Parser, Schema) auf leistungsschwacher Hardware unnötig.                                       |

---
## 3.5 Entwurfsentscheidungen

### ED-1: Strategy Pattern für Rabattaktionstypen

| Feld                 | Inhalt                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| -------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Entscheidung**     | Jeder Aktionstyp (BuyXGetYFree, PercentageDiscount, BundlePrice, HappyHourDiscount) wird als eigene Klasse implementiert, die ein gemeinsames `PromotionStrategy`-Interface erfüllt. Der `DiscountCalculator` delegiert die Berechnung über Polymorphie.                                                                                                                                                                                                                                                                                                                                                    |
| **Was bringt das**   | **Erweiterbarkeit:** Neue Aktionstypen können hinzugefügt werden, indem eine neue Klasse das Interface implementiert – ohne bestehenden Code zu ändern (Open/Closed-Prinzip). Erfüllt direkt NF8 (neue Aktionstypen ohne manuelle Truck-Updates). **Testbarkeit:** Jede Strategie kann isoliert mit Unit-Tests geprüft werden. **Lesbarkeit:** Die Berechnungslogik je Typ ist in einer eigenen Klasse gekapselt statt in einer großen if/else-Kette.                                                                                                                                                       |
| **Was gibt man auf** | **Mehr Klassen:** Jeder neue Typ erfordert eine neue Klasse + Registrierung. Bei sehr vielen Typen steigt die Anzahl der Dateien. **Indirektion:** Der Kontrollfluss ist weniger linear; man muss über das Interface die konkrete Implementierung finden. **Kein dynamisches Nachladen:** Obwohl die Aufgabenstellung NF8 fordert („ohne manuelle Updates"), erfordert ein wirklich neuer Aktionstyp dennoch ein Software-Update, da die Strategie-Klasse auf dem Device vorhanden sein muss. Für neue Instanzen bestehender Typen (z. B. „5 für 4" statt „3 für 2") reicht der Sync der Aktionsdefinition. |

---

### ED-2: Lokale Berechnung mit PromotionCache statt zentraler Service

| Feld                 | Inhalt                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| -------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Entscheidung**     | Die Rabattberechnung läuft vollständig lokal auf dem PoS-Device. Aktionsdefinitionen werden vorab vom Promotion Service synchronisiert und in einem lokalen SQLite-Cache gespeichert.                                                                                                                                                                                                                                                                           |
| **Was bringt das**   | **Offline-Fähigkeit:** Berechnung funktioniert ohne Netzwerk – das wichtigste Qualitätsziel (Priorität 1). **Performance:** Kein Netzwerk-Roundtrip; Berechnung in < 50 ms. **Unabhängigkeit:** Truck ist nicht vom Zustand des Cloud-Backends abhängig; ein Backend-Ausfall beeinträchtigt nicht den laufenden Verkauf.                                                                                                                                        |
| **Was gibt man auf** | **Eventual Consistency:** Aktionsdefinitionen können zwischen Trucks und Zentrale zeitlich abweichen. Ein gerade erstellter Rabatt ist erst nach dem nächsten Sync auf dem Truck verfügbar (typisch: wenige Minuten). **Duplikation:** Die Berechnungslogik liegt auf jedem Device; ein Bug muss auf allen Geräten gefixt werden. **Speicher:** SQLite-Datenbank belegt Platz auf leistungsschwacher Hardware (bei erwarteten ≤ 10 Aktionen: vernachlässigbar). |

---

### ED-3: Prioritätsbasierte Anwendungsreihenfolge mit Kombinierbarkeitsregeln

| Feld                 | Inhalt                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| -------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Entscheidung**     | Jede Aktionsdefinition erhält ein `priority`-Feld (niedrigere Zahl = höhere Priorität) und ein `combinable`-Flag. Aktionen werden in Prioritätsreihenfolge angewendet. Nicht-kombinierbare Aktionen schließen andere Aktionen auf denselben Artikel aus. Kombinierbare Aktionen werden auf den bereits reduzierten Preis angewendet.                                                                                                                                                                                                                                                                                    |
| **Was bringt das**   | **Determinismus:** Identische Warenkörbe führen immer zum identischen Ergebnis, unabhängig von der Reihenfolge, in der Aktionen im Cache gespeichert sind (NF13). **Kontrolle für Marketing:** Marketing kann über Priorität und Kombinierbarkeit steuern, welche Aktionen Vorrang haben und ob sie sich gegenseitig verstärken dürfen. **Nachvollziehbarkeit:** Die Anwendungsreihenfolge ist dokumentiert und reproduzierbar.                                                                                                                                                                                         |
| **Was gibt man auf** | **Komplexität:** Die Kombinierbarkeitslogik ist nicht trivial – insbesondere wenn mehrere nicht-kombinierbare Aktionen auf denselben Artikel passen und die „beste" für den Kunden gewählt werden muss. **Risiko für Marketing:** Falsch konfigurierte Prioritäten können unbeabsichtigte Ergebnisse erzeugen (z. B. zu hohe oder zu niedrige Rabatte). Dies muss durch Validierung im zentralen Promotion Service abgefangen werden. **Einschränkung:** Das Modell deckt keine komplexeren Abhängigkeiten ab, z. B. „Aktion A nur anwendbar, wenn Aktion B nicht greift" – dafür wäre eine regelbasierte Engine nötig. |

---
### ED-4: REST mit HTTP auf localhost statt direktem Funktionsaufruf

| Feld | Inhalt |
|------|--------|
| **Entscheidung** | Die PoS App kommuniziert mit der Promotion Engine über eine lokale REST-API (HTTP auf localhost:8080) statt über einen direkten In-Process-Funktionsaufruf. |
| **Was bringt das** | **Austauschbarkeit:** Die Promotion Engine kann unabhängig von der PoS App aktualisiert oder ersetzt werden. **Standardisierte Schnittstelle:** OpenAPI-Spezifikation ermöglicht Code-Generierung, automatisierte Tests und Dokumentation. **Technologiefreiheit:** PoS App und Promotion Engine können in verschiedenen Sprachen/Frameworks implementiert sein. **Testbarkeit:** Die API kann isoliert mit HTTP-Requests getestet werden (z. B. via Postman, curl). |
| **Was gibt man auf** | **Overhead:** HTTP-Serialisierung/-Deserialisierung kostet Zeit (~1-5 ms), die bei einem direkten Funktionsaufruf entfallen würde. Bei < 50 ms Gesamtbudget ist dies relevant, aber akzeptabel. **Komplexität:** Ein lokaler HTTP-Server muss gestartet und verwaltet werden. **Fehlerquellen:** Netzwerk-Fehler (auch auf localhost), Portkonflikte und Timeout-Handling müssen berücksichtigt werden. |
| **Alternative** | Direkter In-Process-Aufruf (z. B. als Library). Wurde abgelehnt, weil dies die technologische Kopplung erhöht und die unabhängige Aktualisierung der Engine verhindert – was bei einer geplanten Betriebsdauer von ≥ 3 Jahren (NF9) ein relevanter Nachteil ist. |

---

### ED-5: Delta-Sync (Pull) statt Full-Sync für Aktionsdefinitionen

| Feld | Inhalt |
|------|--------|
| **Entscheidung** | Der Truck synchronisiert Aktionsdefinitionen über `GET /api/v1/promotions/active?since={lastSyncTimestamp}`. Nur seit dem letzten Sync geänderte Aktionen werden übertragen. |
| **Was bringt das** | **Bandbreiteneffizienz:** Bei erwarteten ≤ 10 aktiven Aktionen und seltenen Änderungen (einige Male pro Tag) wird nur minimaler Traffic erzeugt. **Offline-Robustheit:** Bei Reconnect nach langer Offline-Phase wird nur die Differenz synchronisiert. **Einfachheit:** Einfacher GET-Request mit einem Zeitstempel-Parameter; keine komplexe Sync-Infrastruktur nötig. |
| **Was gibt man auf** | **Verzögerung:** Neue Aktionen sind erst nach dem nächsten Sync-Intervall (z. B. 5 Minuten) auf dem Truck verfügbar. Für zeitkritische Flash-Sales ist dies ggf. zu langsam. **Keine sofortige Deaktivierung:** Wenn eine Aktion fehlerhaft ist und sofort gestoppt werden muss, dauert es bis zum nächsten Sync-Intervall. (Mitigation: Sync-Intervall bei Konnektivität verkürzen oder manuellen Sync-Trigger anbieten.) **Keine Lösch-Semantik:** Delta-Sync mit `since`-Parameter kann gelöschte Aktionen nicht kommunizieren. Lösung: `active=false` statt Löschen, oder regelmäßiger Full-Sync (z. B. einmal täglich). |

---
### Zusammenfassung der Entwurfsentscheidungen

| ID | Entscheidung | Hauptvorteil | Hauptnachteil |
|----|-------------|--------------|---------------|
| ED-1 | Strategy Pattern für Aktionstypen | Erweiterbarkeit (NF8), Testbarkeit | Echte neue Typen erfordern Software-Update |
| ED-2 | Lokale Berechnung + Cache | Offline-Fähigkeit (NF1/NF2) | Eventual Consistency |
| ED-3 | Prioritäten + Kombinierbarkeit | Determinismus (NF13), Kontrolle | Konfigurationskomplexität |
| ED-4 | REST auf localhost | Austauschbarkeit, Standardisierung | HTTP-Overhead (~1-5 ms) |
| ED-5 | Delta-Sync (Pull) | Bandbreiteneffizienz, Robustheit | Verzögerung bis zum nächsten Sync |
  
---
## 4. Annahmen

| #   | Annahme                                                                                                  | Begründung                                                                               |
| --- | -------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------- |
| A1  | Ein Truck hat maximal ~50 verschiedene Produkte im Sortiment                                             | Vereinfacht die lokale Bestandsverwaltung und begrenzt die Warenkorbgröße                |
| A2  | Rabattaktionen ändern sich höchstens einige Male pro Tag                                                 | Delta-Sync mit 5-Minuten-Intervall ist ausreichend                                       |
| A3  | Es können maximal 10 Aktionen gleichzeitig aktiv sein                                                    | Begrenzung der Berechnungskomplexität; < 50 ms Zielwert bleibt erreichbar                |
| A4  | Ein Warenkorb enthält maximal 20 verschiedene Positionen                                                 | Performance-Annahme für die 50-ms-Anforderung                                            |
| A5  | Die PoS App übergibt Bruttopreise (inkl. MwSt.) als unitPrice                                            | Rabattberechnung erfolgt auf Bruttobasis; MwSt.-Berechnung ist Aufgabe des Order Service |
| A6  | Nicht-kombinierbare Aktionen auf denselben Artikel: die mit höherer Priorität (niedrigerer Zahl) gewinnt | Verhindert Mehrdeutigkeit; Marketing muss Prioritäten korrekt setzen                     |
| A7  | Die „3 für 2"-Regel ist wiederholbar: bei 6 Softdrinks werden 2 frei, bei 9 werden 3 frei usw.           | Gängiges Marktverhalten; Alternative wäre „maximal einmal pro Warenkorb"                 |
| A8 | Zeitbasierte Aktionen (Happy Hour) verwenden die lokale Gerätezeit des Trucks | NTP-Synchronisierung der Geräte wird als gegeben vorausgesetzt |

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
| Vorlesungsfolien | Informationsbeschaffung |
| arc42 Template | Strukturierung der Dokumentation |
| OpenAPI Specification 3.0.3 | Schnittstellendefinition |
| AsyncAPI Specification 2.6.0 | Nachrichtenbasierte Schnittstellendefinition |

## KI-Werkzeuge

| Werkzeug | Einsatzzweck | Erfahrung |
|----------|-------------|-----------|
| Claude (Opus 4.6, Effort: Medium) | Erstellung der Dokumentationsvorlage | Gute Strukturierung, Details mussten angepasst werden |
| Claude (Opus 4.6m Effort: Medium) | Erstellung des YAML Codes mit OpenAPI Version 3.1.1  | Überaschenderweise zunächst nicht Maschinenlesbar |
| Claude (Sonnet 4.6m Effort: Low) | Formulierungshilfe Technologische Dokumentation | Saubere aber Verbose Formulierungen. Teils umformuliert. |

### Prompts und Interaktionsprozess

#### Promp 1
Erstelle eine arc42-konforme Markdown-Dokumentationsvorlage 
für das Artefakt "Promotion Engine" eines Food Truck PoS System.

Fülle alle TODOs mit konkreten Beispielen aus dem Kontext aus.
Behalte die arc42-Kapitelstruktur bei:
- Blackbox-Sicht (Zweck, Schnittstellen, Qualitätsmerkmale)
- Whitebox-Sicht Ebene 1 (Bausteine, interne Kommunikation)
- Schnittstellenspezifikation mit Vor-/Nachbedingungen

Kontext: [Architektur aus A1]

**Antwort**: 

Hier ist eure Vorlage. Die Dokumentation ist fertig und orientiert sich vollständig an eurer bestehenden arc42-Struktur. Hier eine kurze Übersicht, was drin steckt:

**Blackbox-Sicht** beschreibt die Promotion Engine von außen: Zweck (Rabattverwaltung, -berechnung, -verteilung), alle fünf Schnittstellen (Promotion API, Admin API, Sync API, Order Service Callback, Reporting API) mit Richtungsangaben, und sechs Qualitätsmerkmale mit konkreten Zielwerten (z. B. < 50 ms Berechnungszeit, < 5 min Verteilungszeit).

**Whitebox-Sicht Ebene 1** zerlegt die Engine in fünf interne Bausteine — Promotion Manager, Rule Validator, Calculation Engine (mit Strategy Pattern für die Aktionstypen), Distribution Service und Promotion Repository — und zeigt deren Zusammenspiel anhand der beiden Hauptabläufe: Aktion anlegen (UC2) und Rabattberechnung am PoS (UC1). Dazu gibt es ein vereinfachtes Datenmodell mit konkreten Beispielparametern für alle vier Aktionstypen (Prozent, Bundle, 3-für-2, Happy Hour).

**Schnittstellenspezifikation** dokumentiert vier Schnittstellen mit vollständigen JSON-Beispielen aus eurem Food-Truck-Kontext (Currywurst, Pommes, Cola), formalen Vor-/Nachbedingungen und Fehlerfällen mit HTTP-Statuscodes. Am Ende ordnet ein Anhang alles den Anforderungen F2, F10–F12 und NF4, NF5, NF8 aus eurer Gesamtdokumentation zu.

Alle Stellen, die ihr ausfüllen müsst, sind mit **TODO** markiert. Außerdem sind Platzhalter für die UML-Diagramme, Vor-/Nachbedingungen, ADRs, und die Annahmen enthalten.

#### Promp 2

Erstelle eine valide OpenAPI 3.1.1
für folgende REST-API:

Endpunkte:
- POST `/api/v1/promotions/calculate` – Warenkorb senden → Rabatte berechnen → CartResponse zurück
- GET  `/api/v1/promotions/active` – Aktive Aktionsdefinitionen vom Server holen (Delta-Sync)

Anforderungen:
- Alle Schemas mit required-Feldern und descriptions
- Konkrete Beispiele (example) für Request und Response
- Fehlerfälle 400 und 500 mit ErrorResponse-Schema
- Maschinenlesbar: aus der Spec muss Code generierbar sein
- Validiere innerhalb der Antwort, dass alle $ref korrekt auflösen

Datenmodell: [UML Diagramme]

#### Promp 3

Formuliere folgenden technischen Sachverhalt für eine 
Softwarearchitektur-Dokumentation (arc42) um.

Zu formulieren: [Erstes Draft]

