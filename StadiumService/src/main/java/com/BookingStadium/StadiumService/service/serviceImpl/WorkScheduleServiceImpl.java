package com.BookingStadium.StadiumService.service.serviceImpl;


import com.BookingStadium.StadiumService.dto.request.WorkSchelude.CreateWorkScheduleRequest;
import com.BookingStadium.StadiumService.dto.request.WorkSchelude.UpdateWorkScheduleRequest;
import com.BookingStadium.StadiumService.dto.response.WorkScheduleResponse;
import com.BookingStadium.StadiumService.entity.StadiumLocation;
import com.BookingStadium.StadiumService.entity.WorkSchedule;
import com.BookingStadium.StadiumService.exception.AppException;
import com.BookingStadium.StadiumService.exception.ErrorCode;
import com.BookingStadium.StadiumService.mapper.WorkScheduleMapper;
import com.BookingStadium.StadiumService.repository.StadiumLocationRepository;
import com.BookingStadium.StadiumService.repository.WorkScheduleRepository;
import com.BookingStadium.StadiumService.service.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @PreAuthorize("hasRole('OWNER')")
    @Transactional
    public WorkScheduleResponse createWorkSchedule(CreateWorkScheduleRequest request) {
        StadiumLocation location = stadiumLocationRepository.findById
                (request.getLocationId()).orElseThrow(() -> new AppException(ErrorCode.LOCATION_NOT_EXISTED));

        WorkSchedule workSchedule = workScheduleMapper.toWorkSchedule(request);
        workSchedule.setLocation(location);

        return workScheduleMapper.toWorkScheduleResponse(workScheduleRepository.save(workSchedule));
    }

    @Override
    public WorkScheduleResponse findWorkScheduleByLocation(UUID id) {
        StadiumLocation location = stadiumLocationRepository
                .findById(id).orElseThrow(() -> new AppException(ErrorCode.LOCATION_NOT_EXISTED));

        WorkSchedule workSchedule = workScheduleRepository.
                findByLocation(location).orElseThrow(() -> new AppException(ErrorCode.WORK_SCHEDULE_NOT_EXISTED));

        return workScheduleMapper.toWorkScheduleResponse(workSchedule);
    }

    @Override
    @PreAuthorize("hasRole('OWNER')")
    @Transactional
    public WorkScheduleResponse updateWorkSchedule(UUID id, UpdateWorkScheduleRequest request) {
        WorkSchedule workSchedule = workScheduleRepository
                .findById(id).orElseThrow(() -> new AppException(ErrorCode.WORK_SCHEDULE_NOT_EXISTED));
        workScheduleMapper.updateWorkSchedule(workSchedule, request);
        return workScheduleMapper.toWorkScheduleResponse(workScheduleRepository.save(workSchedule));
    }

    @Override
    @PreAuthorize("hasRole('OWNER')")
    @Transactional
    public void deleteWorkSchedule(UUID id) {
        workScheduleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.WORK_SCHEDULE_NOT_EXISTED));
        workScheduleRepository.deleteById(id);
    }
}












