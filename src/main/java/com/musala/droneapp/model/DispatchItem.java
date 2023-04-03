package com.musala.droneapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "dispatch_items")
@Entity
public class DispatchItem implements Serializable {
    public static final long serialVersionUID = 1056282854980676154L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,updatable = false)
    private Integer Id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dispatch_id")
    private Dispatch dispatch;


    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;
}
