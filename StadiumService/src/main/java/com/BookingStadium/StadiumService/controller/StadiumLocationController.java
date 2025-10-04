package com.BookingStadium.StadiumService.controller;


import com.BookingStadium.StadiumService.dto.request.location.CreateLocationRequest;
import com.BookingStadium.StadiumService.dto.request.location.UpdateLocationRequest;
import com.BookingStadium.StadiumService.dto.response.ApiResponse;
import com.BookingStadium.StadiumService.dto.response.StadiumLocationResponse;
import com.BookingStadium.StadiumService.service.StadiumLocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/location")
public class StadiumLocationController {
    @Autowired
    private StadiumLocationService stadiumLocationService;


    @PostMapping
    public ApiResponse<StadiumLocationResponse> createLocation(@RequestBody @Valid CreateLocationRequest request){
        return ApiResponse.<StadiumLocationResponse>builder()
                .code(1000)
                .result(stadiumLocationService.createLocation(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<StadiumLocationResponse>> getLocation(){
        return ApiResponse.<List<StadiumLocationResponse>>builder()
                .code(1000)
                .result(stadiumLocationService.getLocation())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<StadiumLocationResponse> updateLocation(@PathVariable("id") UUID id,
                                                               @RequestBody UpdateLocationRequest request){
        return ApiResponse.<StadiumLocationResponse>builder()
                .code(1000)
                .result(stadiumLocationService.updateLocation(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteLocation(@PathVariable("id") UUID id){
        stadiumLocationService.deleteLocation(id);
        return ApiResponse.<String>builder()
                .code(1000)
                .result("Stadium location has been deleted")
                .build();
    }
}
