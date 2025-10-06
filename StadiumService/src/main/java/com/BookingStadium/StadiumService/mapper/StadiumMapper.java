package com.BookingStadium.StadiumService.mapper;


import com.BookingStadium.StadiumService.dto.request.stadium.CreateStadiumRequest;
import com.BookingStadium.StadiumService.dto.request.stadium.UpdateStadiumRequest;
import com.BookingStadium.StadiumService.dto.response.StadiumResponse;
import com.BookingStadium.StadiumService.entity.Stadium;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StadiumLocationMapper.class, TypeMapper.class})
public interface StadiumMapper {
    Stadium toStadium(CreateStadiumRequest request);

    StadiumResponse toStadiumResponse(Stadium stadium);

    List<StadiumResponse> toStadiumResponse(List<Stadium> stadiumList);

    @Mapping(target = "location", ignore = true)
    void updateStadium(@MappingTarget Stadium stadium, UpdateStadiumRequest request);
}
