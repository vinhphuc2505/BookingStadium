package com.BookingStadium.StadiumService.mapper;


import com.BookingStadium.StadiumService.dto.request.location.CreateLocationRequest;
import com.BookingStadium.StadiumService.dto.request.location.UpdateLocationRequest;
import com.BookingStadium.StadiumService.dto.response.StadiumLocationResponse;
import com.BookingStadium.StadiumService.entity.StadiumLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StadiumLocationMapper {
    StadiumLocation toLocation(CreateLocationRequest request);

    StadiumLocationResponse toLocationResponse(StadiumLocation stadiumLocation);

    List<StadiumLocationResponse> toLocationListResponse(List<StadiumLocation> locationList);

    @Mapping(target = "userId", ignore = true)
    void updateLocation(@MappingTarget StadiumLocation stadiumLocation, UpdateLocationRequest request);

}
