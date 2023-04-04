package com.musala.droneapp.service.impl;

import com.musala.droneapp.dto.DroneRequestDto;
import com.musala.droneapp.exceptions.InvalidTypeException;
import com.musala.droneapp.exceptions.NotFoundException;
import com.musala.droneapp.model.Drone;
import com.musala.droneapp.repository.DroneRepository;
import com.musala.droneapp.service.DroneService;
import com.musala.droneapp.utils.DroneStateEnum;
import com.musala.droneapp.utils.ModelTypeEnum;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public Drone createDrone(DroneRequestDto droneRequestDto) {

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
        return  droneRepository.findById(id).orElseThrow(()-> new NotFoundException("drone"));
    }

    @Override
    public Drone updateDrone(DroneRequestDto droneRequestDto, Integer Id) {
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
}
