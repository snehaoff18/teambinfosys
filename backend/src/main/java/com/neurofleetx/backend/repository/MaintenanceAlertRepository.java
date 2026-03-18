package com.neurofleetx.backend.repository;

import com.neurofleetx.backend.model.MaintenanceAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceAlertRepository
        extends JpaRepository<MaintenanceAlert, Long> {

    // 🔹 Dashboard counts
    long countByResolvedFalse();

    long countBySeverityAndResolvedFalse(String severity);

    // 🔹 Service methods
    List<MaintenanceAlert> findByResolvedFalse();

    List<MaintenanceAlert> findByVehicleId(String vehicleId);
}