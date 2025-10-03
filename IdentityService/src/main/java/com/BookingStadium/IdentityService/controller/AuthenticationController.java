package com.BookingStadium.IdentityService.controller;


import com.BookingStadium.IdentityService.dto.request.IntrospectRequest;
import com.BookingStadium.IdentityService.dto.response.IntrospectResponse;
import com.BookingStadium.IdentityService.service.AuthenticationService;
import com.BookingStadium.IdentityService.dto.request.AuthenticationRequest;
import com.BookingStadium.IdentityService.dto.response.ApiResponse;
import com.BookingStadium.IdentityService.dto.response.AuthenticationResponse;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) throws JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(authenticationService.login(request))
                .build();
    }

    @PostMapping("/verify")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .result(authenticationService.introspect(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestHeader("Authorization") String authHeader) throws ParseException {
        String token = authHeader.replace("Bearer", "");
        authenticationService.logout(token);
        return ApiResponse.<String>builder()
                .code(1000)
                .result("Logout success")
                .build();
    }

}