package com.BookingStadium.IdentityService.controller;


import com.BookingStadium.IdentityService.service.AuthenticationService;
import com.BookingStadium.IdentityService.dto.request.AuthenticationRequest;
import com.BookingStadium.IdentityService.dto.response.ApiResponse;
import com.BookingStadium.IdentityService.dto.response.AuthenticationResponse;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) throws JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(authenticationService.authenticate(request))
                .build();
    }

}
