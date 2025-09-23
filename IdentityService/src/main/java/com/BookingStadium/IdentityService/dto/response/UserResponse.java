package com.BookingStadium.IdentityService.dto.response;


import com.BookingStadium.IdentityService.dto.external.profile.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private RoleResponse role;

    private String userId;

    private String email;

    private String username;

//    private ProfileResponse profileResponse;

    private LocalDateTime dateCreated;
}
