package com.neurofleetx.backend.service;

import com.neurofleetx.backend.model.MaintenanceAlert;
import com.neurofleetx.backend.repository.MaintenanceAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceAlertService {

    @Autowired
    private MaintenanceAlertRepository repository;

    // Create new alert
    public MaintenanceAlert createAlert(MaintenanceAlert alert) {
        return repository.save(alert);
    }

    // Get all alerts
    public List<MaintenanceAlert> getAllAlerts() {
        return repository.findAll();
    }

    // Get unresolved alerts
    public List<MaintenanceAlert> getActiveAlerts() {
        return repository.findByResolvedFalse();
    }

    // Get alerts by vehicle
    public List<MaintenanceAlert> getAlertsByVehicle(String vehicleId) {
        return repository.findByVehicleId(vehicleId);
    }

    // Resolve alert
    public MaintenanceAlert resolveAlert(Long id) {
        Optional<MaintenanceAlert> optional = repository.findById(id);

        if (optional.isPresent()) {
            MaintenanceAlert alert = optional.get();
            alert.setResolved(true);
            alert.setResolvedAt(LocalDateTime.now());
            return repository.save(alert);
        }

        return null;
    }

    // Delete alert
    public void deleteAlert(Long id) {
        repository.deleteById(id);
    }
}