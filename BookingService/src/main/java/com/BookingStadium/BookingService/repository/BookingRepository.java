package com.BookingStadium.BookingService.repository;


import com.BookingStadium.BookingService.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findAllByLocationId(UUID uuid);

    List<Booking> findAllByUserId(UUID uuid);
}
