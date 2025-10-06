package com.BookingStadium.StadiumService.service.serviceImpl;

import com.BookingStadium.StadiumService.dto.request.type.CreateTypeRequest;
import com.BookingStadium.StadiumService.dto.request.type.UpdateTypeRequest;
import com.BookingStadium.StadiumService.dto.response.TypeResponse;
import com.BookingStadium.StadiumService.entity.TypeOfStadium;
import com.BookingStadium.StadiumService.mapper.TypeMapper;
import com.BookingStadium.StadiumService.repository.TypeRepository;
import com.BookingStadium.StadiumService.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public TypeResponse createType(CreateTypeRequest request) {
        TypeOfStadium type = typeMapper.toType(request);

        return typeMapper.toTypeResponse(typeRepository.save(type));
    }

    @Override
    public List<TypeResponse> getType() {
        return typeMapper.toTypeResponse(typeRepository.findAll());
    }

    @Override
    public TypeResponse updateType(int id, UpdateTypeRequest request) {
        TypeOfStadium type = typeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not existed"));
        typeMapper.updateType(type, request);

        return typeMapper.toTypeResponse(typeRepository.save(type));
    }

    @Override
    public void deleteType(int id) {
        typeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type not existed"));
        typeRepository.deleteById(id);
    }
}
