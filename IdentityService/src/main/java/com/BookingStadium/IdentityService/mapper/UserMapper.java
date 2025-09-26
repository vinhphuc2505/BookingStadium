package com.BookingStadium.IdentityService.mapper;


import com.BookingStadium.IdentityService.dto.external.profile.CreateProfileRequest;
import com.BookingStadium.IdentityService.dto.request.CreateUserRequest;
import com.BookingStadium.IdentityService.dto.request.UpdateUserRequest;
import com.BookingStadium.IdentityService.dto.response.UserResponse;
import com.BookingStadium.IdentityService.entity.User;
import com.BookingStadium.IdentityService.mapper.external.ProfileMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, ProfileMapper.class})
public interface UserMapper {
    User toUser(CreateUserRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(source = "user.userId", target = "userId")
    UserResponse toUserResponse(User user, CreateProfileRequest request);

    List<UserResponse> toUserResponseList(List<User> userList);

    @Mapping(target = "role", ignore = true) //Chỉ định field role sẽ bị bỏ qua trong quá trình mapping, không bị ghi đè
    void updateUser(@MappingTarget User user, UpdateUserRequest request);
}
