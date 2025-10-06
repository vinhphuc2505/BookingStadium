package com.BookingStadium.StadiumService.service.serviceImpl;


import com.BookingStadium.StadiumService.dto.request.WorkSchelude.CreateWorkScheduleRequest;
import com.BookingStadium.StadiumService.dto.request.WorkSchelude.UpdateWorkScheduleRequest;
import com.BookingStadium.StadiumService.dto.response.WorkScheduleResponse;
import com.BookingStadium.StadiumService.entity.StadiumLocation;
import com.BookingStadium.StadiumService.entity.WorkSchedule;
import com.BookingStadium.StadiumService.mapper.WorkScheduleMapper;
import com.BookingStadium.StadiumService.repository.StadiumLocationRepository;
import com.BookingStadium.StadiumService.repository.WorkScheduleRepository;
import com.BookingStadium.StadiumService.service.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WorkScheduleServiceImpl implements WorkScheduleService {
    @Autowired
    private WorkScheduleRepository workScheduleRepository;
    @Autowired
    private WorkScheduleMapper workScheduleMapper;
    @Autowired
    private StadiumLocationRepository stadiumLocationRepository;

    @Override
    public WorkScheduleResponse createWorkSchedule(CreateWorkScheduleRequest request) {
        StadiumLocation location = stadiumLocationRepository.findById
                (request.getLocationId()).orElseThrow(() -> new RuntimeException("Location not existed"));

        WorkSchedule workSchedule = workScheduleMapper.toWorkSchedule(request);
        workSchedule.setLocation(location);

        return workScheduleMapper.toWorkScheduleResponse(workScheduleRepository.save(workSchedule));
    }

    @Override
    public WorkScheduleResponse findWorkScheduleByLocation(UUID id) {
        StadiumLocation location = stadiumLocationRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Location not existed"));

        WorkSchedule workSchedule = workScheduleRepository.
                findByLocation(location).orElseThrow(() -> new RuntimeException("Location not existed"));

        return workScheduleMapper.toWorkScheduleResponse(workSchedule);
    }

    @Override
    public WorkScheduleResponse updateWorkSchedule(UUID id, UpdateWorkScheduleRequest request) {
        WorkSchedule workSchedule = workScheduleRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Work schedule not existed"));
        workScheduleMapper.updateWorkSchedule(workSchedule, request);
        return workScheduleMapper.toWorkScheduleResponse(workScheduleRepository.save(workSchedule));
    }

    @Override
    public void deleteWorkSchedule(UUID id) {
        workScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Work schedule not existed"));
        workScheduleRepository.deleteById(id);
    }
}












