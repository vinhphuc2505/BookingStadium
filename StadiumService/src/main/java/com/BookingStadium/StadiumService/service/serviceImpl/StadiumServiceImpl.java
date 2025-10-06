package com.BookingStadium.StadiumService.service.serviceImpl;


import com.BookingStadium.StadiumService.dto.request.stadium.CreateStadiumRequest;
import com.BookingStadium.StadiumService.dto.request.stadium.UpdateStadiumRequest;
import com.BookingStadium.StadiumService.dto.response.StadiumResponse;
import com.BookingStadium.StadiumService.entity.Stadium;
import com.BookingStadium.StadiumService.entity.StadiumLocation;
import com.BookingStadium.StadiumService.entity.TypeOfStadium;
import com.BookingStadium.StadiumService.mapper.StadiumMapper;
import com.BookingStadium.StadiumService.repository.StadiumLocationRepository;
import com.BookingStadium.StadiumService.repository.StadiumRepository;
import com.BookingStadium.StadiumService.repository.TypeRepository;
import com.BookingStadium.StadiumService.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public StadiumResponse create(CreateStadiumRequest request) {
        StadiumLocation location = stadiumLocationRepository
                .findById(request.getLocationId()).orElseThrow(() -> new RuntimeException("Location not existed"));
        TypeOfStadium type = typeRepository.findById(request.getTypeId())
                .orElseThrow(() -> new RuntimeException("Type of stadium not existed"));
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
    public StadiumResponse updateStadium(UUID id, UpdateStadiumRequest request) {
        Stadium stadium = stadiumRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Stadium not existed"));
        stadiumMapper.updateStadium(stadium, request);
        return stadiumMapper.toStadiumResponse(stadiumRepository.save(stadium));
    }

    @Override
    public void deleteStadium(UUID id) {
        stadiumRepository.findById(id).orElseThrow(() -> new RuntimeException("Stadium not existed"));
        stadiumRepository.deleteById(id);
    }
}
