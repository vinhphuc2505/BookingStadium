package com.BookingStadium.StadiumService.service.serviceImpl;

import com.BookingStadium.StadiumService.dto.request.type.CreateTypeRequest;
import com.BookingStadium.StadiumService.dto.request.type.UpdateTypeRequest;
import com.BookingStadium.StadiumService.dto.response.TypeResponse;
import com.BookingStadium.StadiumService.entity.TypeOfStadium;
import com.BookingStadium.StadiumService.exception.AppException;
import com.BookingStadium.StadiumService.exception.ErrorCode;
import com.BookingStadium.StadiumService.mapper.TypeMapper;
import com.BookingStadium.StadiumService.repository.TypeRepository;
import com.BookingStadium.StadiumService.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private TypeMapper typeMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public TypeResponse createType(CreateTypeRequest request) {
        if (typeRepository.existsByTypeName(request.getTypeName())){
            throw new AppException(ErrorCode.TYPE_EXISTED);
        }

        TypeOfStadium type = typeMapper.toType(request);

        return typeMapper.toTypeResponse(typeRepository.save(type));
    }

    @Override
    public List<TypeResponse> getType() {
        return typeMapper.toTypeResponse(typeRepository.findAll());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public TypeResponse updateType(int id, UpdateTypeRequest request) {
        TypeOfStadium type = typeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TYPE_NOT_EXISTED));

        typeMapper.updateType(type, request);

        return typeMapper.toTypeResponse(typeRepository.save(type));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteType(int id) {
        typeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TYPE_NOT_EXISTED));
        typeRepository.deleteById(id);
    }
}
