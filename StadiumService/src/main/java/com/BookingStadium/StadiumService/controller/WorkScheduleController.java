package com.BookingStadium.StadiumService.controller;


import com.BookingStadium.StadiumService.dto.request.WorkSchelude.CreateWorkScheduleRequest;
import com.BookingStadium.StadiumService.dto.request.WorkSchelude.UpdateWorkScheduleRequest;
import com.BookingStadium.StadiumService.dto.response.ApiResponse;
import com.BookingStadium.StadiumService.dto.response.WorkScheduleResponse;
import com.BookingStadium.StadiumService.service.WorkScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/work-schedule")
public class WorkScheduleController {
    @Autowired
    private WorkScheduleService workScheduleService;

    @PostMapping
    public ApiResponse<WorkScheduleResponse> createWorkSchedule(@RequestBody @Valid CreateWorkScheduleRequest request){
        return ApiResponse.<WorkScheduleResponse>builder()
                .code(1000)
                .result(workScheduleService.createWorkSchedule(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<WorkScheduleResponse> findWorkScheduleByLocation(@PathVariable("id") UUID id){
        return ApiResponse.<WorkScheduleResponse>builder()
                .code(1000)
                .result(workScheduleService.findWorkScheduleByLocation(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<WorkScheduleResponse> updateWorkSchedule(@PathVariable("id") UUID id,
                                                                @RequestBody UpdateWorkScheduleRequest request){
        return ApiResponse.<WorkScheduleResponse>builder()
                .code(1000)
                .result(workScheduleService.updateWorkSchedule(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteWorkSchedule(@PathVariable("id") UUID id){
        workScheduleService.deleteWorkSchedule(id);
        return ApiResponse.<Void>builder()
                .code(1000)
                .message("Work schedule has been deleted")
                .build();
    }
}







