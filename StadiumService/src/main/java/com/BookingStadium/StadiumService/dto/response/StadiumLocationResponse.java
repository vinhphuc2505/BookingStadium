package com.BookingStadium.StadiumService.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StadiumLocationResponse {
    private UUID userId;

    private UUID locationId;

    private String locationName;

    private String address;

    private String ward;

    private String district;

    private String city;

    private String description;

    private LocalDate dateCreated;
}
