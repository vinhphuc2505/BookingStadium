package com.BookingStadium.BookingService.service;


import com.BookingStadium.BookingService.dto.request.booking.CreateBookingRequest;
import com.BookingStadium.BookingService.dto.request.booking.UpdateBookingRequest;
import com.BookingStadium.BookingService.dto.response.BookingResponse;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    BookingResponse createBooking(CreateBookingRequest request);

    List<BookingResponse> getBookingByUser(UUID id);

    List<BookingResponse> getBookingByLocation(UUID id);

    BookingResponse updateBooking(UUID bookingId, UpdateBookingRequest request);

    void deleteBooking(UUID bookingId);
}
