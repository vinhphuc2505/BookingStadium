package com.BookingStadium.IdentityService.service.ServiceImpl;


import com.BookingStadium.IdentityService.dto.JwtInfo;
import com.BookingStadium.IdentityService.dto.request.IntrospectRequest;
import com.BookingStadium.IdentityService.dto.response.IntrospectResponse;
import com.BookingStadium.IdentityService.entity.RedisToken;
import com.BookingStadium.IdentityService.repository.RedisTokenRepository;
import com.BookingStadium.IdentityService.repository.UserRepository;
import com.BookingStadium.IdentityService.service.AuthenticationService;
import com.BookingStadium.IdentityService.dto.request.AuthenticationRequest;
import com.BookingStadium.IdentityService.dto.response.AuthenticationResponse;
import com.BookingStadium.IdentityService.entity.User;
import com.BookingStadium.IdentityService.service.JwtService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.Date;



@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RedisTokenRepository redisTokenRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @NonFinal
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) throws JOSEException {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(request.getLoginName(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        User user = (User) authentication.getPrincipal();

        return AuthenticationResponse.builder()
                .authenticate(true)
                .accessToken(jwtService.generateToken(user))
                .build();
    }

    @Override
    public void logout(String token) throws ParseException {
        JwtInfo jwtInfo = jwtService.parseToken(token);

        String jwtId = jwtInfo.getJwtId();
        Date expiredTime = jwtInfo.getExpiredTime();

        if (expiredTime.before(new Date())){
            throw new RuntimeException("Token đã hết hạn");
        }

        RedisToken redisToken = RedisToken.builder()
                .jwtId(jwtId)
                .expiredTime(expiredTime.getTime() - new Date().getTime())
                .build();

        redisTokenRepository.save(redisToken);

    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        // Xác thực token bằng SIGNER_KEY
        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());
        // Giải mã token
        SignedJWT signedJWT = SignedJWT.parse(token);
        // Lấy thời gian hết hạn của token
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        // Kiểm tra tính hợp lệ của token
        var verified = signedJWT.verify(jwsVerifier);

        boolean valid = verified && expiryTime.after(new Date());
        IntrospectResponse introspectResponse = new IntrospectResponse();
        introspectResponse.setValid(valid);

        return introspectResponse;
    }


}















