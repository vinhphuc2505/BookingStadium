package com.BookingStadium.IdentityService.service;


import com.BookingStadium.IdentityService.dto.request.AuthenticationRequest;
import com.BookingStadium.IdentityService.dto.request.IntrospectRequest;
import com.BookingStadium.IdentityService.dto.response.AuthenticationResponse;
import com.BookingStadium.IdentityService.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws JOSEException;

    public void logout(String token) throws ParseException;

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
