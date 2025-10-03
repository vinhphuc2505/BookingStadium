package com.BookingStadium.IdentityService.service;

import com.BookingStadium.IdentityService.dto.JwtInfo;
import com.BookingStadium.IdentityService.entity.User;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface JwtService {
    String generateToken(User user) throws JOSEException;

    JwtInfo parseToken(String token) throws ParseException;
}
