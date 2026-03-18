package com.neurofleetx.backend.controller;

import com.neurofleetx.backend.model.MaintenanceAlert;
import com.neurofleetx.backend.service.MaintenanceAlertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "http://localhost:3000")
public class MaintenanceAlertController {

    @Autowired
    private MaintenanceAlertService service;

    // 🔴 Create new alert
    @PostMapping
    public MaintenanceAlert createAlert(@RequestBody MaintenanceAlert alert) {
        return service.createAlert(alert);
    }

    // 📋 Get all alerts
    @GetMapping
    public List<MaintenanceAlert> getAllAlerts() {
        return service.getAllAlerts();
    }

    // 🚨 Get active (unresolved) alerts
    @GetMapping("/active")
    public List<MaintenanceAlert> getActiveAlerts() {
        return service.getActiveAlerts();
    }

    // 🚗 Get alerts by vehicle
    @GetMapping("/vehicle/{vehicleId}")
    public List<MaintenanceAlert> getAlertsByVehicle(
            @PathVariable String vehicleId) {
        return service.getAlertsByVehicle(vehicleId);
    }

    // ✅ Resolve alert
    @PutMapping("/resolve/{id}")
    public MaintenanceAlert resolveAlert(@PathVariable Long id) {
        return service.resolveAlert(id);
    }

    // ❌ Delete alert
    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable Long id) {
        service.deleteAlert(id);
    }
}