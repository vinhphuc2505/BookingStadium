package com.BookingStadium.ProfileService.controller;



import com.BookingStadium.ProfileService.dto.request.CreateProfileRequest;
import com.BookingStadium.ProfileService.dto.request.UpdateProfileRequest;
import com.BookingStadium.ProfileService.dto.response.ApiResponse;
import com.BookingStadium.ProfileService.dto.response.ProfileResponse;
import com.BookingStadium.ProfileService.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ApiResponse<ProfileResponse> createProfile(@RequestBody @Valid CreateProfileRequest request){
        return ApiResponse.<ProfileResponse>builder()
                .code(1000)
                .result(profileService.createProfile(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProfileResponse>> getProfile(){
        return ApiResponse.<List<ProfileResponse>>builder()
                .code(1000)
                .result(profileService.getProfile())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProfileResponse> findProfile(@PathVariable("id") String id){
        return ApiResponse.<ProfileResponse>builder()
                .code(1000)
                .result(profileService.findProfile(id))
                .build();
    }

    @GetMapping("/my-profile")
    public ApiResponse<ProfileResponse> getMyProfile(){
        return ApiResponse.<ProfileResponse>builder()
                .code(1000)
                .result(profileService.getMyProfile())
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<ProfileResponse> updateProfile(@RequestBody UpdateProfileRequest request){
        return ApiResponse.<ProfileResponse>builder()
                .code(1000)
                .result(profileService.updateProfile(request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteProfile(@PathVariable("id") String id){
        profileService.deleteProfile(id);
        return ApiResponse.<String>builder()
                .code(1000)
                .result("Profile has been deleted")
                .build();
    }

}
