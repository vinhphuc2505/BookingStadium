package com.BookingStadium.BookingService.repository;

import com.BookingStadium.BookingService.entity.Booking;
import com.BookingStadium.BookingService.entity.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, UUID> {

    List<BookingDetails> findAllByBooking(Booking booking);
}
