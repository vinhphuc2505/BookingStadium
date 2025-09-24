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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .result(authenticationService.authenticate(request))
                .build();
    }

    @PostMapping("/verify")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .result(authenticationService.introspect(request))
                .build();
    }

}