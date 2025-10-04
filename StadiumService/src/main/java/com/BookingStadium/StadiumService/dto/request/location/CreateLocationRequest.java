package com.BookingStadium.StadiumService.dto.request.location;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateLocationRequest {
    private UUID userId;

    private String locationName;

    private String address;

    private String ward;

    private String district;

    private String city;

    private String description;
}
