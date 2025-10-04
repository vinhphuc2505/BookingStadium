package com.BookingStadium.StadiumService.controller;


import com.BookingStadium.StadiumService.dto.request.type.CreateTypeRequest;
import com.BookingStadium.StadiumService.dto.request.type.UpdateTypeRequest;
import com.BookingStadium.StadiumService.dto.response.ApiResponse;
import com.BookingStadium.StadiumService.dto.response.TypeResponse;
import com.BookingStadium.StadiumService.service.TypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @PostMapping
    public ApiResponse<TypeResponse> createType(@RequestBody @Valid CreateTypeRequest request){
        return ApiResponse.<TypeResponse>builder()
                .code(1000)
                .result(typeService.createType(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<TypeResponse>> getType(){
        return ApiResponse.<List<TypeResponse>>builder()
                .code(1000)
                .result(typeService.getType())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<TypeResponse> updateType(@PathVariable("id") int id,
                                                @RequestBody @Valid UpdateTypeRequest request){
        return ApiResponse.<TypeResponse>builder()
                .code(1000)
                .result(typeService.updateType(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteType(@PathVariable("id") int id){
        typeService.deleteType(id);
        return ApiResponse.<String>builder()
                .code(1000)
                .result("Type has been deleted")
                .build();
    }


}




