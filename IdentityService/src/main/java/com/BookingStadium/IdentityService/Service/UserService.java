package com.BookingStadium.IdentityService.Service;

import com.BookingStadium.IdentityService.dto.request.CreateUserRequest;
import com.BookingStadium.IdentityService.dto.request.UpdateUserRequest;
import com.BookingStadium.IdentityService.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse findUser(String id);

    UserResponse getMyInfo();

    List<UserResponse> getUser();

    UserResponse updateUser(String id, UpdateUserRequest request);

    void deleteUser(String id);
}
