package com.BookingStadium.IdentityService.dto.external.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {

    private String firstname;

    private String lastname;

    private String phone;

    private LocalDate date_of_birth;

}
