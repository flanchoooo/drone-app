package com.musala.droneapp.service;

import com.musala.droneapp.dto.DroneRequestDto;
import com.musala.droneapp.model.Drone;

import java.util.List;

public interface DroneService {

    Drone createDrone(DroneRequestDto droneRequestDto);
    Drone findbyId(Integer Id);
    Drone updateDrone(DroneRequestDto droneRequestDto,Integer Id);

    List<Drone> findAll();

}
