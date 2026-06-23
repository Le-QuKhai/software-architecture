package com.foodtruck.procurement.web;

import com.foodtruck.procurement.application.ProcurementApplicationService;
import com.foodtruck.procurement.domain.RestockRequestStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProcurementController {

    private final ProcurementApplicationService procurementService;

    public ProcurementController(ProcurementApplicationService procurementService) {
        this.procurementService = procurementService;
    }

    @GetMapping("/restock-requests")
    public List<RestockRequestResponse> getRestockRequests(
            @RequestParam(name = "status", defaultValue = "OPEN") RestockRequestStatus status) {
        return procurementService.findRequestsByStatus(status).stream()
                .map(RestockRequestResponse::from)
                .toList();
    }

    @PostMapping("/restock-requests/{restockRequestId}/transport-order")
    @ResponseStatus(HttpStatus.CREATED)
    public TransportOrderResponse createTransportOrder(@PathVariable String restockRequestId) {
        return TransportOrderResponse.from(procurementService.createTransportOrder(restockRequestId));
    }

    @GetMapping("/transport-orders/{transportOrderId}")
    public TransportOrderResponse getTransportOrder(@PathVariable String transportOrderId) {
        return TransportOrderResponse.from(procurementService.getTransportOrder(transportOrderId));
    }
}
