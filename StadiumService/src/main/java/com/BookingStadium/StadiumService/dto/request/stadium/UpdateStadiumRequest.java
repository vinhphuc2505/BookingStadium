package com.BookingStadium.StadiumService.dto.request.stadium;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStadiumRequest {
    private int typeId;

    private String stadiumName;

    private double price;

    private String description;
}
