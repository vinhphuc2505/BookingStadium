package com.BookingStadium.IdentityService.client;


import com.BookingStadium.IdentityService.dto.external.profile.CreateProfileRequest;
import com.BookingStadium.IdentityService.dto.external.profile.ProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service", url = "${app.service.profile}")
public interface ProfileClient {
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    ProfileResponse createProfile(@RequestBody CreateProfileRequest request);

}
