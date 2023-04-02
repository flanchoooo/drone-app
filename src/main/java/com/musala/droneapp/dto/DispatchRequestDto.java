package com.musala.droneapp.dto;

import com.musala.droneapp.model.DispatchItem;
import com.musala.droneapp.model.Drone;
import com.musala.droneapp.utils.DroneStateEnum;
import com.musala.droneapp.utils.ModelTypeEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DispatchRequestDto {

    private Drone drone;
    private List<DispatchItem> dispatchItemList;
}
