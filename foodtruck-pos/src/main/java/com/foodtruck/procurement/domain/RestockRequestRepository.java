package com.foodtruck.procurement.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestockRequestRepository extends JpaRepository<RestockRequest, String> {

    Optional<RestockRequest> findFirstByTruckIdAndStatus(String truckId, RestockRequestStatus status);

    List<RestockRequest> findByStatus(RestockRequestStatus status);
}
