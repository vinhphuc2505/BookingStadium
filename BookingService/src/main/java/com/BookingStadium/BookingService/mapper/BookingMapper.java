package com.BookingStadium.BookingService.mapper;

import com.BookingStadium.BookingService.dto.request.booking.CreateBookingRequest;
import com.BookingStadium.BookingService.dto.request.booking.UpdateBookingRequest;
import com.BookingStadium.BookingService.dto.response.BookingResponse;
import com.BookingStadium.BookingService.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toBooking(CreateBookingRequest request);

    BookingResponse toBookingResponse(Booking booking);

    List<BookingResponse> toBookingResponse(List<Booking> bookings);

    void updateBooking(@MappingTarget Booking booking, UpdateBookingRequest request);

}
