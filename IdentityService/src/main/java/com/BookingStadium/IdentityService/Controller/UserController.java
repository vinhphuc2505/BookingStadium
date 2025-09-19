package com.BookingStadium.IdentityService.Controller;


import com.BookingStadium.IdentityService.Service.UserService;
import com.BookingStadium.IdentityService.dto.request.CreateUserRequest;
import com.BookingStadium.IdentityService.dto.request.UpdateUserRequest;
import com.BookingStadium.IdentityService.dto.response.ApiResponse;
import com.BookingStadium.IdentityService.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request){
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findUser(@PathVariable("id") String id){
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.findUser(id))
                .build();
    }

    @GetMapping()
    public ApiResponse<List<UserResponse>> getUser(){
        return ApiResponse.<List<UserResponse>>builder()
                .code(1000)
                .result(userService.getUser())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable("id") String id,
                                                @RequestBody @Valid UpdateUserRequest request){
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.updateUser(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
        return ApiResponse.<String>builder()
                .code(1000)
                .result("User has been deleted")
                .build();
    }

}














