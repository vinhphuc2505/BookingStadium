package com.BookingStadium.ApiGateway.config;


import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Slf4j
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${endpoint.public.register}")
    private String REGISTER_ENDPOINT;

    @NonFinal
    @Value("${endpoint.public.login}")
    private String LOGIN_ENDPOINT;

//    @NonFinal
//    @Value("${endpoint.public.login}")
//    private String LOGOUT_ENDPOINT;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        log.info(REGISTER_ENDPOINT);
        log.info((LOGIN_ENDPOINT));
        http.authorizeExchange(auth -> auth
                .pathMatchers(HttpMethod.POST, REGISTER_ENDPOINT).permitAll()
                .pathMatchers(HttpMethod.POST, LOGIN_ENDPOINT).permitAll()
                .anyExchange().authenticated()
        );

        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults())
        );

        return http.build();

    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HmacSHA512");
        return NimbusReactiveJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }
}
