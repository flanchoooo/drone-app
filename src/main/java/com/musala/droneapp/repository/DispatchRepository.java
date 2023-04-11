package com.musala.droneapp.repository;

import com.musala.droneapp.model.Dispatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DispatchRepository extends JpaRepository<Dispatch,Integer> {

    @Query(value = "select * from dispatches where drone_id=:Id and state='DELIVERING'", nativeQuery = true)
    Dispatch findDroneDispatch(Integer Id);
}
