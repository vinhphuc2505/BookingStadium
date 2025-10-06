package com.BookingStadium.StadiumService.dto.response;

import com.BookingStadium.StadiumService.enums.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkScheduleResponse {

    private DayOfWeek[] dayOfWeeks;

    private LocalTime openTime;

    private LocalTime closeTime;

    private String note;
}
