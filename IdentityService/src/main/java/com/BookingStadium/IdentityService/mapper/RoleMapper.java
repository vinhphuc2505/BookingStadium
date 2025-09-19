package com.BookingStadium.IdentityService.mapper;


import com.BookingStadium.IdentityService.dto.response.RoleResponse;
import com.BookingStadium.IdentityService.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toRoleResponse(Role role);
}
