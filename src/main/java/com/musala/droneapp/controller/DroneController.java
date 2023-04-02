package com.musala.droneapp.controller;

import com.musala.droneapp.dto.DroneRequestDto;
import com.musala.droneapp.model.Drone;
import com.musala.droneapp.service.DroneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("drone")
public class DroneController {

    private final DroneService droneService;

    @PostMapping(path = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Drone create(@RequestBody DroneRequestDto requestDto){
        return droneService.createDrone(requestDto);
    }
}
