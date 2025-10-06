package com.BookingStadium.StadiumService.repository;

import com.BookingStadium.StadiumService.entity.StadiumLocation;
import com.BookingStadium.StadiumService.entity.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, UUID> {
    Optional<WorkSchedule> findByLocation(StadiumLocation location);
}
