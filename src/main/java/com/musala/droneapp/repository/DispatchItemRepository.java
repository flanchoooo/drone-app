package com.musala.droneapp.repository;

import com.musala.droneapp.model.Dispatch;
import com.musala.droneapp.model.DispatchItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DispatchItemRepository extends JpaRepository<DispatchItem,Integer> {

    @Query(value = "select * from dispatch_items where dispatch_id=:Id", nativeQuery = true)
    List<DispatchItem> findDispatchedItems(Integer Id);

}
