package com.BookingStadium.BookingService.controller;


import com.BookingStadium.BookingService.dto.request.booking.CreateBookingRequest;
import com.BookingStadium.BookingService.dto.request.booking.UpdateBookingRequest;
import com.BookingStadium.BookingService.dto.response.ApiResponse;
import com.BookingStadium.BookingService.dto.response.BookingResponse;
import com.BookingStadium.BookingService.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/location")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ApiResponse<BookingResponse> createBooking(@RequestBody @Valid CreateBookingRequest request){
        return ApiResponse.<BookingResponse>builder()
                .code(1000)
                .result(bookingService.createBooking(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<List<BookingResponse>> getBookingByUser(@PathVariable("id") UUID id){
        return ApiResponse.<List<BookingResponse>>builder()
                .code(1000)
                .result(bookingService.getBookingByUser(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<BookingResponse> updateBooking(@PathVariable("id") UUID id,
                                                      @RequestBody @Valid UpdateBookingRequest request){
        return ApiResponse.<BookingResponse>builder()
                .code(1000)
                .result(bookingService.updateBooking(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBooking(@PathVariable("id") UUID id){
        bookingService.deleteBooking(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Booking has been deleted")
                .build();
    }


}





