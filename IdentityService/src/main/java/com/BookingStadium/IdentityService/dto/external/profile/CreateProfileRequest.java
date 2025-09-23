package com.BookingStadium.IdentityService.dto.external.profile;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProfileRequest {
    private String userId;

    private String firstname;

    private String lastname;

    private String phone;

    private String date_of_birth;
}