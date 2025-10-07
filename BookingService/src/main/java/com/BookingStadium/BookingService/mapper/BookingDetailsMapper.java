package com.BookingStadium.BookingService.mapper;


import com.BookingStadium.BookingService.dto.request.details.CreateBookingDetailsRequest;
import com.BookingStadium.BookingService.dto.request.details.UpdateBookingDetailsRequest;
import com.BookingStadium.BookingService.dto.response.BookingDetailsResponse;
import com.BookingStadium.BookingService.entity.BookingDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = BookingMapper.class)
public interface BookingDetailsMapper {
    BookingDetails toBookingDetails(CreateBookingDetailsRequest request);

    BookingDetailsResponse toBookingDetails(BookingDetails bookingDetails);

    List<BookingDetailsResponse> toBookingDetails(List<BookingDetails> bookingDetails);

    void updateBookingDetails(@MappingTarget BookingDetails bookingDetails, UpdateBookingDetailsRequest request);
}
