package com.musala.droneapp.service;

import com.musala.droneapp.dto.DispatchDetailsDto;
import com.musala.droneapp.dto.DispatchRequestDto;
import com.musala.droneapp.model.Dispatch;
import com.musala.droneapp.model.Drone;

import java.util.List;

public interface DispatchService {

    Dispatch dispatch(DispatchRequestDto dispatchRequestDto);
    DispatchDetailsDto droneCargo(String serialNumber);
    List<Drone> availableDrones();

    List<Drone> batteryLevel();
}
