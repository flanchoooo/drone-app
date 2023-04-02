package com.musala.droneapp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "medications")
@Entity
public class Medication implements Serializable {
    public static final long serialVersionUID = 7705937458543097365L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,updatable = false)
    private Integer Id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "image", nullable = false)
    @Lob
    private byte[] image;
}
