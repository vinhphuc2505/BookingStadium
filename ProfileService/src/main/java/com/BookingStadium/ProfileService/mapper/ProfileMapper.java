package com.BookingStadium.ProfileService.mapper;


import com.BookingStadium.ProfileService.dto.request.CreateProfileRequest;
import com.BookingStadium.ProfileService.dto.request.UpdateProfileRequest;
import com.BookingStadium.ProfileService.dto.response.ProfileResponse;
import com.BookingStadium.ProfileService.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toProfile(CreateProfileRequest request);

    ProfileResponse toProfileResponse(Profile profile);

    List<ProfileResponse> toProfileResponse(List<Profile> profiles);

    void updateProfile(@MappingTarget Profile profile, UpdateProfileRequest request);
}
