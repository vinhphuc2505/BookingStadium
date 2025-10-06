package com.BookingStadium.StadiumService.controller;


import com.BookingStadium.StadiumService.dto.request.stadium.CreateStadiumRequest;
import com.BookingStadium.StadiumService.dto.request.stadium.UpdateStadiumRequest;
import com.BookingStadium.StadiumService.dto.response.ApiResponse;
import com.BookingStadium.StadiumService.dto.response.StadiumResponse;
import com.BookingStadium.StadiumService.service.StadiumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stadiums")
public class StadiumController {
    @Autowired
    private StadiumService stadiumService;

    @PostMapping
    public ApiResponse<StadiumResponse> createStadium(@RequestBody @Valid CreateStadiumRequest request){
        return ApiResponse.<StadiumResponse>builder()
                .code(1000)
                .result(stadiumService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<StadiumResponse>> getStadium(){
        return ApiResponse.<List<StadiumResponse>>builder()
                .code(1000)
                .result(stadiumService.getStadium())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<StadiumResponse> updateStadium(@PathVariable("id") UUID id,
                                                      @RequestBody @Valid UpdateStadiumRequest request){
        return ApiResponse.<StadiumResponse>builder()
                .code(1000)
                .result(stadiumService.updateStadium(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteStadium(@PathVariable("id") UUID id){
        stadiumService.deleteStadium(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Stadium has been deleted")
                .build();
    }

}
