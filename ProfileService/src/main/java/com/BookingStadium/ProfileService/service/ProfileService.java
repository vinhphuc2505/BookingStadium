package com.BookingStadium.ProfileService.service;


import com.BookingStadium.ProfileService.dto.request.CreateProfileRequest;
import com.BookingStadium.ProfileService.dto.request.UpdateProfileRequest;
import com.BookingStadium.ProfileService.dto.response.ProfileResponse;

import java.util.List;

public interface ProfileService {
    ProfileResponse createProfile(CreateProfileRequest request);

    List<ProfileResponse> getProfile();

    ProfileResponse findProfile(String id);

    ProfileResponse updateProfile(String id, UpdateProfileRequest request);

    void deleteProfile(String id);

}
