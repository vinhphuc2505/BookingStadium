package com.BookingStadium.BookingService.mapper;

import com.BookingStadium.BookingService.dto.request.details.CreateBookingDetailsRequest;
import com.BookingStadium.BookingService.dto.request.details.UpdateBookingDetailsRequest;
import com.BookingStadium.BookingService.dto.response.BookingDetailsResponse;
import com.BookingStadium.BookingService.entity.BookingDetails;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-27T15:11:16+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class BookingDetailsMapperImpl implements BookingDetailsMapper {

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public BookingDetails toBookingDetails(CreateBookingDetailsRequest request) {
        if ( request == null ) {
            return null;
        }

        BookingDetails.BookingDetailsBuilder bookingDetails = BookingDetails.builder();

        bookingDetails.stadiumId( request.getStadiumId() );
        bookingDetails.dateOfBooking( request.getDateOfBooking() );
        bookingDetails.startTime( request.getStartTime() );
        bookingDetails.endTime( request.getEndTime() );
        bookingDetails.status( request.getStatus() );

        return bookingDetails.build();
    }

    @Override
    public BookingDetailsResponse toBookingDetails(BookingDetails bookingDetails) {
        if ( bookingDetails == null ) {
            return null;
        }

        BookingDetailsResponse.BookingDetailsResponseBuilder bookingDetailsResponse = BookingDetailsResponse.builder();

        bookingDetailsResponse.bookingDetailsId( bookingDetails.getBookingDetailsId() );
        bookingDetailsResponse.booking( bookingMapper.toBookingResponse( bookingDetails.getBooking() ) );
        bookingDetailsResponse.stadiumId( bookingDetails.getStadiumId() );
        bookingDetailsResponse.dateOfBooking( bookingDetails.getDateOfBooking() );
        bookingDetailsResponse.startTime( bookingDetails.getStartTime() );
        bookingDetailsResponse.endTime( bookingDetails.getEndTime() );
        bookingDetailsResponse.totalHours( bookingDetails.getTotalHours() );
        bookingDetailsResponse.status( bookingDetails.getStatus() );
        bookingDetailsResponse.price( bookingDetails.getPrice() );

        return bookingDetailsResponse.build();
    }

    @Override
    public List<BookingDetailsResponse> toBookingDetails(List<BookingDetails> bookingDetails) {
        if ( bookingDetails == null ) {
            return null;
        }

        List<BookingDetailsResponse> list = new ArrayList<BookingDetailsResponse>( bookingDetails.size() );
        for ( BookingDetails bookingDetails1 : bookingDetails ) {
            list.add( toBookingDetails( bookingDetails1 ) );
        }

        return list;
    }

    @Override
    public void updateBookingDetails(BookingDetails bookingDetails, UpdateBookingDetailsRequest request) {
        if ( request == null ) {
            return;
        }

        bookingDetails.setStadiumId( request.getStadiumId() );
        bookingDetails.setDateOfBooking( request.getDateOfBooking() );
        bookingDetails.setStartTime( request.getStartTime() );
        bookingDetails.setEndTime( request.getEndTime() );
    }
}
