package com.BookingStadium.StadiumService.repository;


import com.BookingStadium.StadiumService.entity.StadiumLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StadiumLocationRepository extends JpaRepository<StadiumLocation, UUID> {
    boolean existsByUserId(UUID uuid);

    Optional<StadiumLocation> findByLocationIdAndUserId(UUID locationId, UUID userId);
}
