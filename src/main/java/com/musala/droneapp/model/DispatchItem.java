package com.musala.droneapp.model;

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
    public static final long serialVersionUID = -4937240965976749105L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,updatable = false)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "dispatch_id")
    private Dispatch dispatch;

    @Column(name = "medication_id")
    private Medication medication;

}
