package com.musala.droneapp.service.impl;

import com.musala.droneapp.dto.DroneRequestDto;
import com.musala.droneapp.exceptions.InvalidTypeException;
import com.musala.droneapp.exceptions.NotFoundException;
import com.musala.droneapp.model.AuditLogs;
import com.musala.droneapp.model.Drone;
import com.musala.droneapp.repository.AuditLogsRepository;
import com.musala.droneapp.repository.DroneRepository;
import com.musala.droneapp.service.DroneService;
import com.musala.droneapp.utils.DroneStateEnum;
import com.musala.droneapp.utils.ModelTypeEnum;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Data
@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final AuditLogsRepository auditLogsRepository;

    @Override
    public Drone createDrone(DroneRequestDto droneRequestDto) {
        //Create Drone
        if(Arrays.asList(ModelTypeEnum.values()).stream().filter(model-> model.toString().equals(droneRequestDto.getModel())).collect(Collectors.toList()).isEmpty()){
            throw new InvalidTypeException("Allowed values for model are: LIGHTWEIGHT,MIDDLEWEIGHT,CRUISEWEIGHT,HEAVYWEIGHT");
        }

        if(Arrays.asList(DroneStateEnum.values()).stream().filter(model-> model.toString().equals(droneRequestDto.getState())).collect(Collectors.toList()).isEmpty()){
            throw new InvalidTypeException("Allowed values for state are: IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING");
        }

        Boolean check = droneRepository.existsBySerialNumber(droneRequestDto.getSerialNumber());
        if(Boolean.TRUE.equals(check)){
            throw new InvalidTypeException("Serial Number " + droneRequestDto.getSerialNumber() + " Already exists.");
        }

        final Drone drone = new Drone();
        return getDrone(droneRequestDto, drone);
    }

    @Override
    public Drone findbyId(Integer id) {
        //Find By ID
        return  droneRepository.findById(id).orElseThrow(()-> new NotFoundException("drone"));
    }

    @Override
    public Drone updateDrone(DroneRequestDto droneRequestDto, Integer Id) {
        //update drone
        var drone  = droneRepository.findById(Id).orElseThrow(()-> new NotFoundException("drone"));

        if(Arrays.asList(ModelTypeEnum.values()).stream().filter(model-> model.toString().equals(droneRequestDto.getModel())).collect(Collectors.toList()).isEmpty()){
            throw new InvalidTypeException("Allowed values for model are: LIGHTWEIGHT,MIDDLEWEIGHT,CRUISEWEIGHT,HEAVYWEIGHT");
        }

        if(Arrays.asList(DroneStateEnum.values()).stream().filter(model-> model.toString().equals(droneRequestDto.getState())).collect(Collectors.toList()).isEmpty()){
            throw new InvalidTypeException("Allowed values for state are: IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING");
        }


        return getDrone(droneRequestDto, drone);
    }

    @Override
    public List<Drone> findAll() {
        return droneRepository.findAll();
    }

    private Drone getDrone(DroneRequestDto droneRequestDto, Drone drone) {
        drone.setSerialNumber(droneRequestDto.getSerialNumber());
        drone.setModel(ModelTypeEnum.valueOf(droneRequestDto.getModel()));
        drone.setState(DroneStateEnum.valueOf(droneRequestDto.getState()));
        drone.setBatteryLevel(droneRequestDto.getBatteryLevel());
        drone.setWeightLimit(droneRequestDto.getWeightLimit());
        droneRepository.save(drone);
        return drone;
    }

    @Scheduled(cron = "0 0/1 * * * ?") // runs every 1 minutes
    public void checkBatteryLevel() {
        var droneList = droneRepository.findAll();

        for (Drone drone : droneList) {
            // Check if an audit log already exists for the current day
            Optional<AuditLogs> auditLogOptional = auditLogsRepository
                    .findBySerialNumberAndCreatedAt(drone.getSerialNumber(), LocalDate.now());

            if (auditLogOptional.isPresent()) {
                // If an audit log already exists, update the battery level and updated_at column
                AuditLogs auditLogs = auditLogOptional.get();
                auditLogs.setBatteryLevel(drone.getBatteryLevel());
                auditLogs.setUpdatedAt(LocalDateTime.now());
                auditLogsRepository.save(auditLogs);
            } else {
                // If an audit log does not exist, create a new one
                AuditLogs auditLogs = new AuditLogs();
                auditLogs.setBatteryLevel(drone.getBatteryLevel());
                auditLogs.setSerialNumber(drone.getSerialNumber());
                auditLogs.setCreatedAt(LocalDateTime.now());
                auditLogsRepository.save(auditLogs);
            }
        }
    }
}
