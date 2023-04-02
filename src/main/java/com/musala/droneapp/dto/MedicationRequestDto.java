package com.musala.droneapp.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicationRequestDto {
    private String name;
    private BigDecimal weight;
    private String code;
}
