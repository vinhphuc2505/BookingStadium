package com.BookingStadium.IdentityService.dto.response;


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

    private String email;

    private String username;

    private LocalDateTime dateCreated;
}
