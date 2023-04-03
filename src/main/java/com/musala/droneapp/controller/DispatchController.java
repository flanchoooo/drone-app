package com.musala.droneapp.controller;

import com.musala.droneapp.dto.DispatchRequestDto;
import com.musala.droneapp.dto.DroneRequestDto;
import com.musala.droneapp.model.Dispatch;
import com.musala.droneapp.model.Drone;
import com.musala.droneapp.service.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("dispatches")
@Validated
public class DispatchController {

    private final DispatchService dispatchService;

    @PostMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Dispatch> create(@RequestBody DispatchRequestDto dispatchRequestDto){
        return new ResponseEntity<>( dispatchService.dispatch(dispatchRequestDto), HttpStatus.CREATED);
    }
}
