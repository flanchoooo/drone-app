package com.musala.droneapp.controller;

import com.musala.droneapp.dto.DroneRequestDto;
import com.musala.droneapp.model.Drone;
import com.musala.droneapp.service.DroneService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("drones")
@Validated
public class DroneController {

    private final DroneService droneService;

    @PostMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Drone> create(@RequestBody DroneRequestDto requestDto){
        return new ResponseEntity<>( droneService.createDrone(requestDto), HttpStatus.CREATED);
    }

    @GetMapping(path = "/find-by-id/{id}")
    public ResponseEntity<Drone> findById(@PathVariable Integer id){
        return new ResponseEntity<>( droneService.findbyId(id), HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Drone> updateDrone(@RequestBody DroneRequestDto requestDto,@PathVariable Integer id){
        return new ResponseEntity<>( droneService.updateDrone(requestDto,id), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/find-all")
    public ResponseEntity<List<Drone>> findAll(){
        return new ResponseEntity<>(droneService.findAll(),HttpStatus.ACCEPTED);
    }

}
