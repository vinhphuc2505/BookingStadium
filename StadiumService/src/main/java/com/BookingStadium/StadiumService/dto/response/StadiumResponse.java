package com.BookingStadium.StadiumService.dto.response;


import com.BookingStadium.StadiumService.enums.StadiumStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StadiumResponse {

    private UUID stadiumId;

    private StadiumLocationResponse location;

    private TypeResponse type;

    private String stadiumName;

    private BigDecimal price;

    private StadiumStatus status = StadiumStatus.AVAILABLE;

    private String description;

    private LocalDate dateCreated;
}
