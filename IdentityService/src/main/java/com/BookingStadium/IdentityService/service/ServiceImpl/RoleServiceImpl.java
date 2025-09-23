package com.BookingStadium.IdentityService.service.ServiceImpl;


import com.BookingStadium.IdentityService.repository.RoleRepository;
import com.BookingStadium.IdentityService.service.RoleService;
import com.BookingStadium.IdentityService.entity.Role;
import com.BookingStadium.IdentityService.exception.AppException;
import com.BookingStadium.IdentityService.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Role findRole(String id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Role> getRole() {
        return roleRepository.findAll();
    }
}
