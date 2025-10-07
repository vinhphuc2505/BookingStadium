package com.BookingStadium.BookingService.service;


import com.BookingStadium.BookingService.dto.request.details.CreateBookingDetailsRequest;
import com.BookingStadium.BookingService.dto.request.details.UpdateBookingDetailsRequest;
import com.BookingStadium.BookingService.dto.response.BookingDetailsResponse;

import java.util.List;
import java.util.UUID;

public interface BookingDetailsService {
    BookingDetailsResponse createBookingDetails(CreateBookingDetailsRequest request);

    List<BookingDetailsResponse> getBookingDetailsByBooking(UUID id);

    BookingDetailsResponse updateBookingDetails(UUID id, UpdateBookingDetailsRequest request);

    void deleteBookingDetails(UUID id);
}
