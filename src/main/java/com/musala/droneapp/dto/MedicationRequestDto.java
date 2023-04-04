package com.musala.droneapp.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class MedicationRequestDto {
    private String name;
    private Double weight;
    private String code;
    private MultipartFile image;
}
