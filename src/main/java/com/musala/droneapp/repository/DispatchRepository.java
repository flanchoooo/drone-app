package com.musala.droneapp.repository;

import com.musala.droneapp.model.Dispatch;
import com.musala.droneapp.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispatchRepository extends JpaRepository<Dispatch,Integer> {
}
