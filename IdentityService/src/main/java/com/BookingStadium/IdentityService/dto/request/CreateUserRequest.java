package com.BookingStadium.IdentityService.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {

    @Email(message = "EMAIL_INVALID")
    @NotBlank(message = "FIELD_IS_NOT_EMPTY")
    private String email;

    @NotBlank(message = "FIELD_IS_NOT_EMPTY")
    @Size(min = 8, message = "USER_INVALID")
    private String username;

    @NotBlank(message = "FIELD_IS_NOT_EMPTY")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$", message = "PASSWORD_INVALID")
    private String password;

    @NotBlank(message = "FIELD_IS_NOT_EMPTY")
    private String passwordAgain;

    @NotBlank(message = "FIELD_IS_NOT_EMPTY")
    private String firstname;

    @NotBlank(message = "FIELD_IS_NOT_EMPTY")
    private String lastname;

    @NotBlank(message = "FIELD_IS_NOT_EMPTY")
    private String phone;

    @NotBlank(message = "FIELD_IS_NOT_EMPTY")
    private String date_of_birth;
}
