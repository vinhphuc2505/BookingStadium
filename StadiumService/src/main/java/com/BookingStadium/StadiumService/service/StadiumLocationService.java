package com.BookingStadium.StadiumService.service;

import com.BookingStadium.StadiumService.dto.request.location.CreateLocationRequest;
import com.BookingStadium.StadiumService.dto.request.location.UpdateLocationRequest;
import com.BookingStadium.StadiumService.dto.response.StadiumLocationResponse;

import java.util.List;
import java.util.UUID;

public interface StadiumLocationService {
    StadiumLocationResponse createLocation(CreateLocationRequest request);

    List<StadiumLocationResponse> getLocation();

    StadiumLocationResponse updateLocation(UUID id, UpdateLocationRequest request);

    void deleteLocation(UUID id);
}
