package com.BookingStadium.StadiumService.dto.request.location;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateLocationRequest {
    private String locationName;

    private String description;
}
