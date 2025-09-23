package com.BookingStadium.IdentityService.service.ServiceImpl;


import com.BookingStadium.IdentityService.repository.UserRepository;
import com.BookingStadium.IdentityService.service.AuthenticationService;
import com.BookingStadium.IdentityService.dto.request.AuthenticationRequest;
import com.BookingStadium.IdentityService.dto.response.AuthenticationResponse;
import com.BookingStadium.IdentityService.entity.User;
import com.BookingStadium.IdentityService.exception.AppException;
import com.BookingStadium.IdentityService.exception.ErrorCode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws JOSEException {
        User user = userRepository.findByUsernameOrEmail(authenticationRequest.getLoginName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_OR_EMAIL_NOT_EXISTED));
        var authenticate = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if(authenticate){
            authenticationResponse.setToken(generateToken(user));
            authenticationResponse.setAuthenticate(true);
        }else{
            authenticationResponse.setAuthenticate(false);
        }


        return authenticationResponse;
    }


    private String generateToken(User user) throws JOSEException {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserId())
                .issuer("booking.com")
                .jwtID(UUID.randomUUID().toString())
                .issueTime(new Date())
                .expirationTime(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

        return jwsObject.serialize();
    }



    private String buildScope(User user){
        return user.getRole().getRoleId().toUpperCase();
    }


}















