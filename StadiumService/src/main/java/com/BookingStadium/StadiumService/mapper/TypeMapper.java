package com.BookingStadium.StadiumService.mapper;


import com.BookingStadium.StadiumService.dto.request.type.CreateTypeRequest;
import com.BookingStadium.StadiumService.dto.request.type.UpdateTypeRequest;
import com.BookingStadium.StadiumService.dto.response.TypeResponse;
import com.BookingStadium.StadiumService.entity.TypeOfStadium;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeMapper {
    TypeOfStadium toType(CreateTypeRequest request);

    TypeResponse toTypeResponse(TypeOfStadium type);

    List<TypeResponse> toTypeListResponse(List<TypeOfStadium> typeOfStadiumList);

    @Mapping(target = "typeId", ignore = true)
    void updateType(@MappingTarget TypeOfStadium type, UpdateTypeRequest request);
}
