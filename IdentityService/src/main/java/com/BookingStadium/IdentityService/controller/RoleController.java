package com.BookingStadium.IdentityService.controller;


import com.BookingStadium.IdentityService.service.RoleService;
import com.BookingStadium.IdentityService.dto.response.ApiResponse;
import com.BookingStadium.IdentityService.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ApiResponse<List<Role>> getRole(){
        return ApiResponse.<List<Role>>builder()
                .result(roleService.getRole())
                .code(1000)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Role> findRole(@PathVariable("id") String id){
        return ApiResponse.<Role>builder()
                .code(1000)
                .result(roleService.findRole(id.toUpperCase()))
                .build();
    }


}
