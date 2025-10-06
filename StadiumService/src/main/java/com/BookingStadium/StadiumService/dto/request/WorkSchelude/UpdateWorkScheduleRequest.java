package com.BookingStadium.StadiumService.dto.request.WorkSchelude;


import com.BookingStadium.StadiumService.enums.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateWorkScheduleRequest {

    private DayOfWeek[] dayOfWeeks;

    private LocalTime openTime;

    private LocalTime closeTime;

    private String note;
}
