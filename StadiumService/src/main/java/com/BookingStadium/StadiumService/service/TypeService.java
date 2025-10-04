package com.BookingStadium.StadiumService.service;

import com.BookingStadium.StadiumService.dto.request.type.CreateTypeRequest;
import com.BookingStadium.StadiumService.dto.request.type.UpdateTypeRequest;
import com.BookingStadium.StadiumService.dto.response.TypeResponse;

import java.util.List;

public interface TypeService {
    TypeResponse createType(CreateTypeRequest request);

    List<TypeResponse> getType();

    TypeResponse updateType(int id, UpdateTypeRequest request);

    void deleteType(int id);
}
