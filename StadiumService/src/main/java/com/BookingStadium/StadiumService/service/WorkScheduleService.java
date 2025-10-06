package com.BookingStadium.StadiumService.service;


import com.BookingStadium.StadiumService.dto.request.WorkSchelude.CreateWorkScheduleRequest;
import com.BookingStadium.StadiumService.dto.request.WorkSchelude.UpdateWorkScheduleRequest;
import com.BookingStadium.StadiumService.dto.response.WorkScheduleResponse;

import java.util.UUID;

public interface WorkScheduleService {
    WorkScheduleResponse createWorkSchedule(CreateWorkScheduleRequest request);

    WorkScheduleResponse findWorkScheduleByLocation(UUID id);

    WorkScheduleResponse updateWorkSchedule(UUID id, UpdateWorkScheduleRequest request);

    void deleteWorkSchedule(UUID id);
}
