package com.BookingStadium.IdentityService.service.ServiceImpl;

import com.BookingStadium.IdentityService.dto.JwtInfo;
import com.BookingStadium.IdentityService.entity.User;
import com.BookingStadium.IdentityService.repository.RedisTokenRepository;
import com.BookingStadium.IdentityService.service.JwtService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;


@Service
public class JwtServiceImpl implements JwtService {
    @Autowired
    private RedisTokenRepository redisTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;


    @Override
    public String generateToken(User user) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        Date issueTime = new Date();
        Date expirationTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserId())
                .issuer("booking.com")
                .jwtID(UUID.randomUUID().toString())
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

        return jwsObject.serialize();
    }

    @Override
    public boolean verifyToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiredTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        if (expiredTime.before(new Date())){
            throw new RuntimeException("Token đã hết hạn");
        }

        var jwtId = signedJWT.getJWTClaimsSet().getJWTID();

        if (redisTokenRepository.existsById(jwtId)){
            throw new RuntimeException("Token đã logout");
        }

        return signedJWT.verify(new MACVerifier(SIGNER_KEY.getBytes()));

    }

    @Override
    public JwtInfo parseToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);

        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date issueTime = signedJWT.getJWTClaimsSet().getIssueTime();
        Date expiredTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        return JwtInfo.builder()
                .jwtId(jwtId)
                .issueTime(issueTime)
                .expiredTime(expiredTime)
                .build();
    }


    private String buildScope(User user){
        return user.getRole().getRoleId().toUpperCase();
    }
}
