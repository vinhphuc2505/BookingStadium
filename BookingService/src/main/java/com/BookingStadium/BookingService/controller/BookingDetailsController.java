package com.BookingStadium.BookingService.controller;


import com.BookingStadium.BookingService.dto.request.details.CreateBookingDetailsRequest;
import com.BookingStadium.BookingService.dto.request.details.UpdateBookingDetailsRequest;
import com.BookingStadium.BookingService.dto.response.ApiResponse;
import com.BookingStadium.BookingService.dto.response.BookingDetailsResponse;
import com.BookingStadium.BookingService.service.BookingDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/details")
public class BookingDetailsController {
    @Autowired
    private BookingDetailsService bookingDetailsService;


    @PostMapping
    public ApiResponse<BookingDetailsResponse> createBookingDetails(@RequestBody @Valid CreateBookingDetailsRequest request){
        return ApiResponse.<BookingDetailsResponse>builder()
                .code(1000)
                .result(bookingDetailsService.createBookingDetails(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<List<BookingDetailsResponse>> getBookingDetails(@PathVariable("id") UUID id){
        return ApiResponse.<List<BookingDetailsResponse>>builder()
                .code(1000)
                .result(bookingDetailsService.getBookingDetailsByBooking(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<BookingDetailsResponse> updateBookingDetails(@PathVariable("id") UUID id,
                                                                   @RequestBody @Valid UpdateBookingDetailsRequest request) {
        return ApiResponse.<BookingDetailsResponse>builder()
                .code(1000)
                .result(bookingDetailsService.updateBookingDetails(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBookingDetails(@PathVariable("id") UUID id) {
        bookingDetailsService.deleteBookingDetails(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Booking details has been deleted")
                .build();
    }

}






