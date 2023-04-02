package com.musala.droneapp.dto;

import com.musala.droneapp.utils.DroneStateEnum;
import com.musala.droneapp.utils.ModelTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseDto {

    private Integer code;
    private String message;
    private Object data;
}
