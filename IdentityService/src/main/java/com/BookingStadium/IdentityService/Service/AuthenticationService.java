package com.BookingStadium.IdentityService.Service;


import com.BookingStadium.IdentityService.dto.request.AuthenticationRequest;
import com.BookingStadium.IdentityService.dto.response.AuthenticationResponse;
import com.nimbusds.jose.JOSEException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws JOSEException;
}
