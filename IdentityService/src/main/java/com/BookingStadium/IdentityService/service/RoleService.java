package com.BookingStadium.IdentityService.service;


import com.BookingStadium.IdentityService.entity.Role;

import java.util.List;

public interface RoleService {
    Role findRole(String id);

    List<Role> getRole();
}
