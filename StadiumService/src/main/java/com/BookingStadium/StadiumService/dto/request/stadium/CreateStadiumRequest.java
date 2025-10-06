package com.BookingStadium.StadiumService.dto.request.stadium;


import com.BookingStadium.StadiumService.entity.StadiumLocation;
import com.BookingStadium.StadiumService.entity.TypeOfStadium;
import com.BookingStadium.StadiumService.enums.StadiumStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStadiumRequest {

    private UUID locationId;

    private int typeId;

    private String stadiumName;

    private BigDecimal price;

    private StadiumStatus status = StadiumStatus.AVAILABLE;

    private String description;
}
