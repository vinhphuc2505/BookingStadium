package com.BookingStadium.StadiumService.mapper;

import com.BookingStadium.StadiumService.dto.request.WorkSchelude.CreateWorkScheduleRequest;
import com.BookingStadium.StadiumService.dto.request.WorkSchelude.UpdateWorkScheduleRequest;
import com.BookingStadium.StadiumService.dto.response.WorkScheduleResponse;
import com.BookingStadium.StadiumService.entity.WorkSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkScheduleMapper {
    WorkSchedule toWorkSchedule(CreateWorkScheduleRequest request);

    WorkScheduleResponse toWorkScheduleResponse(WorkSchedule workSchedule);

    @Mapping(target = "location", ignore = true)
    void updateWorkSchedule(@MappingTarget WorkSchedule workSchedule, UpdateWorkScheduleRequest request);
}
