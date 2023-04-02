package com.musala.droneapp.service.impl;

import com.musala.droneapp.dto.DroneRequestDto;
import com.musala.droneapp.model.Drone;
import com.musala.droneapp.repository.DroneRepository;
import com.musala.droneapp.service.DroneService;
import lombok.Data;
import org.springframework.stereotype.Service;



@Data
@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public Drone createDrone(DroneRequestDto droneRequestDto) {
        Drone drone = new Drone();
        drone.setSerialNumber(droneRequestDto.getSerialNumber());
        drone.setModel(droneRequestDto.getModel());
        drone.setState(droneRequestDto.getState());
        drone.setBatteryLevel(droneRequestDto.getBatteryLevel());
        drone.setWeightLimit(droneRequestDto.getWeightLimit());
        droneRepository.save(drone);
        return drone;
    }
}
