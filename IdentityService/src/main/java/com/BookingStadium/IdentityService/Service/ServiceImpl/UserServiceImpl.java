package com.BookingStadium.IdentityService.Service.ServiceImpl;

import com.BookingStadium.IdentityService.Repository.RoleRepository;
import com.BookingStadium.IdentityService.Repository.UserRepository;
import com.BookingStadium.IdentityService.Service.UserService;
import com.BookingStadium.IdentityService.dto.request.CreateUserRequest;
import com.BookingStadium.IdentityService.dto.request.UpdateUserRequest;
import com.BookingStadium.IdentityService.dto.response.UserResponse;
import com.BookingStadium.IdentityService.entity.Role;
import com.BookingStadium.IdentityService.entity.User;
import com.BookingStadium.IdentityService.exception.AppException;
import com.BookingStadium.IdentityService.exception.ErrorCode;
import com.BookingStadium.IdentityService.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        if(userRepository.existsByEmailOrUsername(request.getEmail(), request.getUsername())){
            throw new AppException(ErrorCode.USER_OR_EMAIL_EXISTED);
        }

        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findById("CUSTOMER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));

        user.setRole(role);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse findUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    public UserResponse getMyInfo() {
        return null;
    }

    @Override
    public List<UserResponse> getUser() {
        return userMapper.toUserResponseList(userRepository.findAll());
    }

    @Override
    public UserResponse updateUser(String id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }


    @Override
    public void deleteUser(String id) {
        userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userRepository.deleteById(id);
    }

}
