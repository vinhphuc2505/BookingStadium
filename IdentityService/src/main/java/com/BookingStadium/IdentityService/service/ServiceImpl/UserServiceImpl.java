package com.BookingStadium.IdentityService.service.ServiceImpl;

import com.BookingStadium.IdentityService.client.ProfileClient;
import com.BookingStadium.IdentityService.mapper.external.ProfileMapper;
import com.BookingStadium.IdentityService.repository.RoleRepository;
import com.BookingStadium.IdentityService.repository.UserRepository;
import com.BookingStadium.IdentityService.service.UserService;
import com.BookingStadium.IdentityService.dto.request.CreateUserRequest;
import com.BookingStadium.IdentityService.dto.request.UpdateUserRequest;
import com.BookingStadium.IdentityService.dto.response.UserResponse;
import com.BookingStadium.IdentityService.entity.Role;
import com.BookingStadium.IdentityService.entity.User;
import com.BookingStadium.IdentityService.exception.AppException;
import com.BookingStadium.IdentityService.exception.ErrorCode;
import com.BookingStadium.IdentityService.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfileClient profileClient;
    @Autowired
    private ProfileMapper profileMapper;


    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if(userRepository.existsByEmailOrUsername(request.getEmail(), request.getUsername())){
            throw new AppException(ErrorCode.USER_OR_EMAIL_EXISTED);
        }

        if(!(request.getPassword().equals(request.getPasswordAgain()))){
            throw new AppException(ErrorCode.PASSWORDS_DO_NOT_MATCH);
        }

        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findById("CUSTOMER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));

        user.setRole(role);

        var profileRequest = profileMapper.toCreateProfileRequest(request);
        profileRequest.setUserId(user.getUserId());

        profileClient.createProfile(profileRequest);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse findUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    public UserResponse getMyInfo() {
        var id = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUser() {
        return userMapper.toUserResponseList(userRepository.findAll());
    }

    @Override
    @Transactional
    public UserResponse updateUser(UpdateUserRequest request) {
        if(!(request.getPassword().equals(request.getPasswordAgain()))){
            throw new AppException(ErrorCode.PASSWORDS_DO_NOT_MATCH);
        }

        var id = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }


    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String id) {
        userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userRepository.deleteById(id);
    }

}
