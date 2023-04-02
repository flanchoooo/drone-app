package com.musala.droneapp.service;

import com.musala.droneapp.dto.DroneRequestDto;
import com.musala.droneapp.dto.MedicationRequestDto;
import com.musala.droneapp.model.Drone;
import com.musala.droneapp.model.Medication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MedicationService {

    Medication createMedication(MedicationRequestDto medicationRequestDto, MultipartFile image);
    Medication findById(Integer Id);
    Medication updateMedication(MedicationRequestDto medicationRequestDto,Integer Id);

    List<Medication> findAll();

}
