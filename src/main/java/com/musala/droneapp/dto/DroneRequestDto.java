package com.musala.droneapp.dto;

import com.musala.droneapp.utils.DroneStateEnum;
import com.musala.droneapp.utils.ModelTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DroneRequestDto {

    private String serialNumber;
    private ModelTypeEnum model;
    private BigDecimal weightLimit;
    private Integer batteryLevel;
    private DroneStateEnum state;

}
