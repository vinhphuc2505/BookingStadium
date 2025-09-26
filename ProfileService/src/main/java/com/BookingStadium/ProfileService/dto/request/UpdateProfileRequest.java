package com.BookingStadium.ProfileService.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProfileRequest {
    private String firstname;

    private String lastname;

    private String phone;

    private LocalDate date_of_birth;
}
