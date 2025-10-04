package com.BookingStadium.StadiumService.service.serviceImpl;

import com.BookingStadium.StadiumService.dto.request.location.CreateLocationRequest;
import com.BookingStadium.StadiumService.dto.request.location.UpdateLocationRequest;
import com.BookingStadium.StadiumService.dto.response.StadiumLocationResponse;
import com.BookingStadium.StadiumService.entity.StadiumLocation;
import com.BookingStadium.StadiumService.mapper.StadiumLocationMapper;
import com.BookingStadium.StadiumService.repository.StadiumLocationRepository;
import com.BookingStadium.StadiumService.service.StadiumLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StadiumLocationServiceImpl implements StadiumLocationService {
    @Autowired
    private StadiumLocationMapper stadiumLocationMapper;
    @Autowired
    private StadiumLocationRepository stadiumLocationRepository;


    @Override
    public StadiumLocationResponse createLocation(CreateLocationRequest request) {
        StadiumLocation stadiumLocation = stadiumLocationMapper.toLocation(request);

        return stadiumLocationMapper.toLocationResponse(stadiumLocationRepository.save(stadiumLocation));
    }

    @Override
    public List<StadiumLocationResponse> getLocation() {
        return stadiumLocationMapper.toLocationListResponse(stadiumLocationRepository.findAll());
    }

    @Override
    public StadiumLocationResponse updateLocation(UUID id, UpdateLocationRequest request) {
        StadiumLocation stadiumLocation = stadiumLocationRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Location not existed"));
        stadiumLocationMapper.updateLocation(stadiumLocation, request);
        return stadiumLocationMapper.toLocationResponse(stadiumLocationRepository.save(stadiumLocation));
    }

    @Override
    public void deleteLocation(UUID id) {
        stadiumLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not existed"));
        stadiumLocationRepository.deleteById(id);
    }
}
