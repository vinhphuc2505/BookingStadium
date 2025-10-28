package com.BookingStadium.IdentityService.service;

import com.BookingStadium.IdentityService.dto.request.CreateUserRequest;
import com.BookingStadium.IdentityService.dto.request.UpdateRoleRequest;
import com.BookingStadium.IdentityService.dto.request.UpdateUserRequest;
import com.BookingStadium.IdentityService.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse findUser(String id);

    UserResponse getMyInfo();

    List<UserResponse> getUser();

    UserResponse updateUser(UpdateUserRequest request);

    UserResponse updateUserRole(String userId, UpdateRoleRequest request);

    void deleteUser(String id);
}
