package com.musala.droneapp.dto;

import com.musala.droneapp.model.DispatchItem;
import com.musala.droneapp.model.Drone;
import com.musala.droneapp.model.Medication;
import lombok.Data;

import java.util.List;

@Data
public class DispatchDetailsDto {

    private Drone drone;
    private List<Medication> medicationList;
}
