package com.musala.droneapp.dto;

import lombok.Data;

@Data
public class DispatchRequestDto {

    private String droneSerial;
    private String destination;
    private int[] medicationIds;
}
