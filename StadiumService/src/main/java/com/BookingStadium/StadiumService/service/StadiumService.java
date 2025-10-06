package com.BookingStadium.StadiumService.service;


import com.BookingStadium.StadiumService.dto.request.stadium.CreateStadiumRequest;
import com.BookingStadium.StadiumService.dto.request.stadium.UpdateStadiumRequest;
import com.BookingStadium.StadiumService.dto.response.StadiumResponse;

import java.util.List;
import java.util.UUID;

public interface StadiumService {
    StadiumResponse create(CreateStadiumRequest request);

    List<StadiumResponse> getStadium();

    StadiumResponse updateStadium(UUID id, UpdateStadiumRequest request);

    void deleteStadium(UUID id);
}
