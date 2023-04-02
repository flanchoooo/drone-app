package com.musala.droneapp.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class MedicationRequestDto {
    private String name;
    private BigDecimal weight;
    private String code;
    private MultipartFile image;
}
