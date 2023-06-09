package com.musala.droneapp.controller;


import com.musala.droneapp.dto.MedicationRequestDto;
import com.musala.droneapp.model.Medication;
import com.musala.droneapp.service.MedicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("medications")
@Validated
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping(path = "/", consumes = { "multipart/form-data" })
    public ResponseEntity<Medication> create(@ModelAttribute MedicationRequestDto medicationRequestDto) throws IOException {
        return new ResponseEntity<>( medicationService.createMedication(medicationRequestDto), HttpStatus.CREATED);
    }

    @GetMapping(path = "/find-by-id/{id}")
    public ResponseEntity<Medication> findById(@PathVariable Integer id){
        return new ResponseEntity<>( medicationService.findById(id), HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Medication> updateDrone(@RequestBody MedicationRequestDto requestDto,@PathVariable Integer id){
        return new ResponseEntity<>( medicationService.updateMedication(requestDto,id), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/find-all")
    public ResponseEntity<List<Medication>> findAll(){
        return new ResponseEntity<>(medicationService.findAll(),HttpStatus.ACCEPTED);
    }

}
