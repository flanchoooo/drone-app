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
@Table(name = "medications")
@Entity
public class Medication implements Serializable {
    public static final long serialVersionUID = -3123117201801907027L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,updatable = false)
    private Integer Id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "code", nullable = false)
    private String code;

    @JsonIgnore
    @Column(name = "image", nullable = false)
    @Lob
    private byte[] image;
}
