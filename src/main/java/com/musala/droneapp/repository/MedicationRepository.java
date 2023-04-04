package com.musala.droneapp.repository;

import com.musala.droneapp.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication,Integer> {

    @Query(value = "select * from medications where id in(:idList)", nativeQuery = true)
    Double calculateWeight(List<Integer> idList);

}
