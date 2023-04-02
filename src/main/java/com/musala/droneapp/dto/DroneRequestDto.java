package com.musala.droneapp.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DroneRequestDto {

    private String serialNumber;
    private String model;
    private BigDecimal weightLimit;
    private Integer batteryLevel;
    private String state;

}
