package com.BookingStadium.IdentityService.config;


import com.BookingStadium.IdentityService.repository.RoleRepository;
import com.BookingStadium.IdentityService.repository.UserRepository;
import com.BookingStadium.IdentityService.entity.Role;
import com.BookingStadium.IdentityService.entity.User;
import com.BookingStadium.IdentityService.exception.AppException;
import com.BookingStadium.IdentityService.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class ApplicationInitConfig {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            if(!roleRepository.existsById("ADMIN")){
                Role role = Role.builder().roleId("ADMIN").build();
                roleRepository.save(role);
            }

            if(!roleRepository.existsById("OWNER")){
                Role role = Role.builder().roleId("OWNER").build();
                roleRepository.save(role);
            }

            if(!roleRepository.existsById("CUSTOMER")){
                Role role = Role.builder().roleId("CUSTOMER").build();
                roleRepository.save(role);
            }

            Role role = roleRepository.findById("ADMIN")
                    .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));

            if(!userRepository.existsByRole(role)){
                User user = User.builder()
                        .email("admin@example.com")
                        .username("admin")
                        .password(passwordEncoder.encode("Admin@123"))
                        .role(role)
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }
}
