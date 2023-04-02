package com.musala.droneapp.repository;

import com.musala.droneapp.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone,Integer> {

    Boolean existsBySerialNumber(String serialNumber);
}
