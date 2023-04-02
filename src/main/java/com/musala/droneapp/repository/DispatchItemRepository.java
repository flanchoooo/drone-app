package com.musala.droneapp.repository;

import com.musala.droneapp.model.Dispatch;
import com.musala.droneapp.model.DispatchItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispatchItemRepository extends JpaRepository<DispatchItem,Integer> {
}
