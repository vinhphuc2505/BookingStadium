package com.BookingStadium.ProfileService.service.serviceImpl;


import com.BookingStadium.ProfileService.dto.request.CreateProfileRequest;
import com.BookingStadium.ProfileService.dto.request.UpdateProfileRequest;
import com.BookingStadium.ProfileService.dto.response.ProfileResponse;
import com.BookingStadium.ProfileService.entity.Profile;
import com.BookingStadium.ProfileService.mapper.ProfileMapper;
import com.BookingStadium.ProfileService.repository.ProfileRepository;
import com.BookingStadium.ProfileService.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileMapper profileMapper;


    @Override
    @Transactional
    public ProfileResponse createProfile(CreateProfileRequest request) {
        if(profileRepository.existsByUserId(request.getUserId())){
            throw new RuntimeException("Người dùng đã có profile");
        }

        Profile profile = profileMapper.toProfile(request);

        return profileMapper.toProfileResponse(profileRepository.save(profile));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProfileResponse> getProfile() {
        return profileMapper.toProfileListResponse(profileRepository.findAll());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ProfileResponse findProfile(String id) {
        return profileMapper.toProfileResponse(profileRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException()));
    }

    @Override
    public ProfileResponse getMyProfile() {
        var id = SecurityContextHolder.getContext().getAuthentication().getName();

        var profile = profileRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("Người dùng chưa có profile"));

        return profileMapper.toProfileResponse(profile);
    }

    @Override
    @Transactional
    public ProfileResponse updateProfile(UpdateProfileRequest request) {
        var id = SecurityContextHolder.getContext().getAuthentication().getName();

        Profile profile = profileRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException());
        profileMapper.updateProfile(profile, request);

        return profileMapper.toProfileResponse(profileRepository.save(profile));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteProfile(String id) {
        profileRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException());
        profileRepository.deleteByUserId(id);
    }


}
