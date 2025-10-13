package com.BookingStadium.StadiumService.service.serviceImpl;


import com.BookingStadium.StadiumService.dto.request.stadium.CreateStadiumRequest;
import com.BookingStadium.StadiumService.dto.request.stadium.UpdateStadiumRequest;
import com.BookingStadium.StadiumService.dto.response.StadiumResponse;
import com.BookingStadium.StadiumService.entity.Stadium;
import com.BookingStadium.StadiumService.entity.StadiumLocation;
import com.BookingStadium.StadiumService.entity.TypeOfStadium;
import com.BookingStadium.StadiumService.exception.AppException;
import com.BookingStadium.StadiumService.exception.ErrorCode;
import com.BookingStadium.StadiumService.mapper.StadiumMapper;
import com.BookingStadium.StadiumService.repository.StadiumLocationRepository;
import com.BookingStadium.StadiumService.repository.StadiumRepository;
import com.BookingStadium.StadiumService.repository.TypeRepository;
import com.BookingStadium.StadiumService.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StadiumServiceImpl implements StadiumService {
    @Autowired
    private StadiumMapper stadiumMapper;
    @Autowired
    private StadiumRepository stadiumRepository;
    @Autowired
    private StadiumLocationRepository stadiumLocationRepository;
    @Autowired
    private TypeRepository typeRepository;


    @Override
    @PreAuthorize("hasRole('OWNER')")
    @Transactional
    public StadiumResponse create(CreateStadiumRequest request) {
        StadiumLocation location = stadiumLocationRepository
                .findById(request.getLocationId()).orElseThrow(() -> new AppException(ErrorCode.LOCATION_NOT_EXISTED));
        TypeOfStadium type = typeRepository.findById(request.getTypeId())
                .orElseThrow(() -> new AppException(ErrorCode.TYPE_NOT_EXISTED));
        Stadium stadium = stadiumMapper.toStadium(request);
        stadium.setLocation(location);
        stadium.setType(type);

        return stadiumMapper.toStadiumResponse(stadiumRepository.save(stadium));
    }

    @Override
    public List<StadiumResponse> getStadium() {
        return stadiumMapper.toStadiumResponse(stadiumRepository.findAll());
    }

    @Override
    @PreAuthorize("hasRole('OWNER')")
    @Transactional
    public StadiumResponse updateStadium(UUID id, UpdateStadiumRequest request) {
        Stadium stadium = stadiumRepository
                .findById(id).orElseThrow(() -> new AppException(ErrorCode.STADIUM_NOT_EXISTED));
        stadiumMapper.updateStadium(stadium, request);
        return stadiumMapper.toStadiumResponse(stadiumRepository.save(stadium));
    }

    @Override
    @PreAuthorize("hasRole('OWNER')")
    @Transactional
    public void deleteStadium(UUID id) {
        stadiumRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.STADIUM_NOT_EXISTED));
        stadiumRepository.deleteById(id);
    }
}
