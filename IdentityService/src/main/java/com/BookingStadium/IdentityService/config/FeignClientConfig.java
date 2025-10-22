package com.BookingStadium.IdentityService.config;


import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
public class FeignClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication instanceof JwtAuthenticationToken jwtAuth) {

                // Lấy giá trị token đúng cách
                String token = jwtAuth.getToken().getTokenValue();

                // Gắn Authorization header vào request Feign
                requestTemplate.header("Authorization", "Bearer " + token);
            }
        };
    }
}
