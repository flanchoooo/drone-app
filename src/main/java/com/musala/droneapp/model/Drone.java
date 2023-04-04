package com.musala.droneapp.model;

import com.musala.droneapp.utils.DroneStateEnum;
import com.musala.droneapp.utils.ModelTypeEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

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

    @NotNull(message = "Please provide serial number")
    @Size(max = 100,message = "Serial number should not be more than 100 characters")
    @Column(name = "serial_number", nullable = false,unique = true)
    private String serialNumber;

    @NotNull(message = "Please provide model")
    @Column(name = "model", nullable = false)
    @Enumerated(EnumType.STRING)
    private ModelTypeEnum model;

    @NotNull(message = "Please provide wieght")
    @Max(value = 500,message = "Weight must not exceed 500gr")
    @Column(name = "weight_limit", nullable = false)
    private Double weightLimit;

    @NotNull(message = "Please provide battery level")
    @Max(value = 100,message = "Battery level should not exceed 100")
    @Column(name = "battery_level", nullable = false)
    private Integer batteryLevel;

    @NotNull(message = "Please provide drone state")
    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private DroneStateEnum state;
}
