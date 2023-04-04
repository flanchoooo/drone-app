package com.musala.droneapp.service.impl;

import com.musala.droneapp.dto.MedicationRequestDto;
import com.musala.droneapp.exceptions.InvalidTypeException;
import com.musala.droneapp.exceptions.NotFoundException;
import com.musala.droneapp.model.Medication;
import com.musala.droneapp.repository.MedicationRepository;
import com.musala.droneapp.service.MedicationService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

@Data
@Service
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepository medicationRepository;

    @Override
    public Medication createMedication(MedicationRequestDto medicationRequestDto) throws IOException {
        String regex = "^[a-zA-Z0-9-_]+$";
        boolean matches = Pattern.matches(regex, medicationRequestDto.getName());
        if(!matches){
            throw new InvalidTypeException("Only letters, numbers, ‘-‘, ‘_’ are allowed");
        }
        var base64String = this.convertImageToBase64String(medicationRequestDto.getImage());
        Medication medication = new Medication();
        medication.setName(medicationRequestDto.getName());
        medication.setCode(medicationRequestDto.getCode());
        medication.setWeight(medicationRequestDto.getWeight());
        medication.setImage(base64String.getBytes());
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
        medication.setCode(medicationRequestDto.getCode());
        medication.setWeight(medicationRequestDto.getWeight());
        medicationRepository.save(medication);
        return  medication;
    }

    @Override
    public List<Medication> findAll() {
        return medicationRepository.findAll();
    }

    public String convertImageToBase64String(MultipartFile imageFile) throws IOException {
        // Get the input stream of the image file
        InputStream inputStream = imageFile.getInputStream();
        // Convert the image file to a byte array
        byte[] imageBytes = FileCopyUtils.copyToByteArray(inputStream);
        // Encode the byte array to a Base64 string
        String base64EncodedString = Base64.getEncoder().encodeToString(imageBytes);
        // Close the input stream
        inputStream.close();
        return base64EncodedString;
    }
}
