package com.BookingStadium.IdentityService.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntrospectRequest {

    @NotBlank(message = "FIELD_IS_NOT_EMPTY")
    private String token;
}
