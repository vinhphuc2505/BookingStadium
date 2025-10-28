package com.BookingStadium.BookingService.mapper;

import com.BookingStadium.BookingService.dto.request.booking.CreateBookingRequest;
import com.BookingStadium.BookingService.dto.request.booking.UpdateBookingRequest;
import com.BookingStadium.BookingService.dto.response.BookingResponse;
import com.BookingStadium.BookingService.entity.Booking;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-27T15:11:16+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public Booking toBooking(CreateBookingRequest request) {
        if ( request == null ) {
            return null;
        }

        Booking.BookingBuilder booking = Booking.builder();

        booking.locationId( request.getLocationId() );
        booking.totalPrice( request.getTotalPrice() );
        booking.bookingStatus( request.getBookingStatus() );
        booking.dateCreated( request.getDateCreated() );

        return booking.build();
    }

    @Override
    public BookingResponse toBookingResponse(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingResponse.BookingResponseBuilder bookingResponse = BookingResponse.builder();

        bookingResponse.bookingId( booking.getBookingId() );
        bookingResponse.userId( booking.getUserId() );
        bookingResponse.locationId( booking.getLocationId() );
        bookingResponse.totalPrice( booking.getTotalPrice() );
        bookingResponse.bookingStatus( booking.getBookingStatus() );
        bookingResponse.dateCreated( booking.getDateCreated() );

        return bookingResponse.build();
    }

    @Override
    public List<BookingResponse> toBookingResponse(List<Booking> bookings) {
        if ( bookings == null ) {
            return null;
        }

        List<BookingResponse> list = new ArrayList<BookingResponse>( bookings.size() );
        for ( Booking booking : bookings ) {
            list.add( toBookingResponse( booking ) );
        }

        return list;
    }

    @Override
    public void updateBooking(Booking booking, UpdateBookingRequest request) {
        if ( request == null ) {
            return;
        }

        booking.setBookingStatus( request.getBookingStatus() );
        booking.setLastUpdated( request.getLastUpdated() );
    }
}
