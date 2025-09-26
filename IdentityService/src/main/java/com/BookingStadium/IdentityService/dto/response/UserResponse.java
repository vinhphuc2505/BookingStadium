package com.BookingStadium.IdentityService.dto.response;


import com.BookingStadium.IdentityService.dto.external.profile.ProfileResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private RoleResponse role;

    private String userId;

    private String email;

    private String username;

    private ProfileResponse profileResponse;

    private LocalDateTime dateCreated;
}
