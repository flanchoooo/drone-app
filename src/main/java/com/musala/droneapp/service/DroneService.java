package com.musala.droneapp.service;

import com.musala.droneapp.dto.DroneRequestDto;
import com.musala.droneapp.model.Drone;

public interface DroneService {

    Drone createDrone(DroneRequestDto droneRequestDto);

}
