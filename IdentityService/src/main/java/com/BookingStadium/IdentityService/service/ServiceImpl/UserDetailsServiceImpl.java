package com.BookingStadium.IdentityService.service.ServiceImpl;

import com.BookingStadium.IdentityService.exception.AppException;
import com.BookingStadium.IdentityService.exception.ErrorCode;
import com.BookingStadium.IdentityService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmail(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_OR_EMAIL_NOT_EXISTED));
    }
}
