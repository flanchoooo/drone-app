package com.musala.droneapp.model;

import com.musala.droneapp.utils.DroneStateEnum;
import com.musala.droneapp.utils.ModelTypeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "drones")
@Entity
public class Drone  implements Serializable {
    public static final long serialVersionUID = -4937240965976749105L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,updatable = false)
    private Integer Id;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "model_type", nullable = false)
    private ModelTypeEnum model;

    @Column(name = "weight_limit", nullable = false)
    private BigDecimal weightLimit;

    @Column(name = "battery_level", nullable = false)
    private Integer batteryLevel;

    @Column(name = "state", nullable = false)
    private DroneStateEnum state;

}
