package com.foodtruck.procurement.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransportOrderRepository extends JpaRepository<TransportOrder, String> {

    Optional<TransportOrder> findFirstByRestockRequestId(String restockRequestId);
}
