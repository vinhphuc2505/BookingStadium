package com.BookingStadium.StadiumService.service.serviceImpl;

import com.BookingStadium.StadiumService.dto.request.location.CreateLocationRequest;
import com.BookingStadium.StadiumService.dto.request.location.UpdateLocationRequest;
import com.BookingStadium.StadiumService.dto.response.StadiumLocationResponse;
import com.BookingStadium.StadiumService.entity.StadiumLocation;
import com.BookingStadium.StadiumService.exception.AppException;
import com.BookingStadium.StadiumService.exception.ErrorCode;
import com.BookingStadium.StadiumService.mapper.StadiumLocationMapper;
import com.BookingStadium.StadiumService.repository.StadiumLocationRepository;
import com.BookingStadium.StadiumService.service.StadiumLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StadiumLocationServiceImpl implements StadiumLocationService {
    @Autowired
    private StadiumLocationMapper stadiumLocationMapper;
    @Autowired
    private StadiumLocationRepository stadiumLocationRepository;


    @Override
    @PreAuthorize("hasRole('OWNER')")
    @Transactional
    public StadiumLocationResponse createLocation(CreateLocationRequest request) {
        var id = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());

        StadiumLocation stadiumLocation = stadiumLocationMapper.toLocation(request);
        stadiumLocation.setUserId(id);

        return stadiumLocationMapper.toLocationResponse(stadiumLocationRepository.save(stadiumLocation));
    }

    @Override
    public List<StadiumLocationResponse> getLocation() {
        return stadiumLocationMapper.toLocationResponse(stadiumLocationRepository.findAll());
    }

    @Override
    @PreAuthorize("hasRole('OWNER')")
    @Transactional
    public StadiumLocationResponse updateLocation(UUID locationId, UpdateLocationRequest request) {
        var userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());

        StadiumLocation stadiumLocation = stadiumLocationRepository
                .findByLocationIdAndUserId(locationId, userId).orElseThrow(() -> new AppException(ErrorCode.LOCATION_NOT_EXISTED));

        stadiumLocationMapper.updateLocation(stadiumLocation, request);

        return stadiumLocationMapper.toLocationResponse(stadiumLocationRepository.save(stadiumLocation));
    }

    @Override
    @PreAuthorize("hasRole('OWNER')")
    @Transactional
    public void deleteLocation(UUID locationId) {
        var userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());

        stadiumLocationRepository.findByLocationIdAndUserId
                (locationId, userId).orElseThrow(() -> new AppException(ErrorCode.LOCATION_NOT_EXISTED));

        stadiumLocationRepository.deleteById(locationId);
    }
}
