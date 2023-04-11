package com.musala.droneapp.repository;

import com.musala.droneapp.model.AuditLogs;
import com.musala.droneapp.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AuditLogsRepository extends JpaRepository<AuditLogs,Integer> {
    @Query(value = "SELECT * FROM audit_logs WHERE serial_number = :serialNumber AND created_at LIKE %:createdAt%", nativeQuery = true)
    Optional<AuditLogs> findBySerialNumberAndCreatedAt(String serialNumber, LocalDate createdAt);


}
