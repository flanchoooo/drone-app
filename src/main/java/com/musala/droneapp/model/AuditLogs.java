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
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "audit_logs")
@Entity
public class AuditLogs implements Serializable {
    public static final long serialVersionUID = -4937240965976749105L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,updatable = false)
    private Integer Id;

    @NotNull(message = "Please provide serial number")
    @Size(max = 100,message = "Serial number should not be more than 100 characters")
    @Column(name = "serial_number", nullable = false,unique = true)
    private String serialNumber;


    @NotNull(message = "Please provide battery level")
    @Max(value = 100,message = "Battery level should not exceed 100")
    @Column(name = "battery_level", nullable = false)
    private Integer batteryLevel;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
