package com.musala.droneapp.repository;

import com.musala.droneapp.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone,Integer> {

    Boolean existsBySerialNumber(String serialNumber);

    Drone findBySerialNumber(String serialNumber);

    @Query(value = "select * from drones where state='IDLE'", nativeQuery = true)
    List<Drone> findDrones();

    @Query(value = "select * from drones", nativeQuery = true)
    List<Drone> batteryLevel();
}
