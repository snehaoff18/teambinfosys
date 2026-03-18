package com.neurofleetx.backend.repository;

import com.neurofleetx.backend.model.MaintenanceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceReportRepository
        extends JpaRepository<MaintenanceReport, Long> {

    List<MaintenanceReport> findByVehicleId(String vehicleId);
}