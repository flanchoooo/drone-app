package com.musala.droneapp.repository;

import com.musala.droneapp.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication,Integer> {
}
