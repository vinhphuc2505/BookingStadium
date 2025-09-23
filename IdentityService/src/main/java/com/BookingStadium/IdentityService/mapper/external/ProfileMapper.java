package com.BookingStadium.IdentityService.mapper.external;


import com.BookingStadium.IdentityService.dto.external.profile.CreateProfileRequest;
import com.BookingStadium.IdentityService.dto.external.profile.ProfileResponse;
import com.BookingStadium.IdentityService.dto.request.CreateUserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    CreateProfileRequest toCreateProfileRequest(CreateUserRequest request);

    ProfileResponse toProfileResponse(CreateProfileRequest request);
}
