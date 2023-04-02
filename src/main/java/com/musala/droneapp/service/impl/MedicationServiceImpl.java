package com.musala.droneapp.service.impl;

import com.musala.droneapp.dto.DroneRequestDto;
import com.musala.droneapp.dto.MedicationRequestDto;
import com.musala.droneapp.exceptions.InvalidTypeException;
import com.musala.droneapp.exceptions.NotFoundException;
import com.musala.droneapp.model.Medication;
import com.musala.droneapp.repository.MedicationRepository;
import com.musala.droneapp.service.MedicationService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.regex.Pattern;

@Data
@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;
    @Override
    public Medication createMedication(MedicationRequestDto medicationRequestDto,MultipartFile image) {
        String regex = "^[a-zA-Z0-9-_]+$";
        boolean matches = Pattern.matches(regex, medicationRequestDto.getName());
        if(!matches){
            throw new InvalidTypeException("Only letters, numbers, ‘-‘, ‘_’ are allowed");
        }

        Medication medication = new Medication();
        medication.setName(medicationRequestDto.getName());
        medication.setCode(medication.getCode());
        medication.setWeight(medicationRequestDto.getWeight());
       // medication.setImage(image.getBytes());
        medicationRepository.save(medication);
        return  medication;
    }

    @Override
    public Medication findById(Integer Id) {
        return  medicationRepository.findById(Id).orElseThrow(()-> new NotFoundException("medication"));
    }

    @Override
    public Medication updateMedication(MedicationRequestDto medicationRequestDto, Integer Id) {
        var medication = medicationRepository.findById(Id).orElseThrow(()-> new NotFoundException("medication"));
        medication.setName(medicationRequestDto.getName());
        medication.setCode(medication.getCode());
        medication.setWeight(medicationRequestDto.getWeight());
        medicationRepository.save(medication);
        return  medication;
    }

    @Override
    public List<Medication> findAll() {
        return medicationRepository.findAll();
    }
}
