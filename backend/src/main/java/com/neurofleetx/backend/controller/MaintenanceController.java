package com.neurofleetx.backend.controller;

import com.neurofleetx.backend.model.VehicleHealthData;
import com.neurofleetx.backend.model.MaintenanceReport;
import com.neurofleetx.backend.repository.VehicleHealthRepository;
import com.neurofleetx.backend.repository.MaintenanceReportRepository;
import com.neurofleetx.backend.service.MaintenanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin(origins = "http://localhost:3000") // React frontend
public class MaintenanceController {

    @Autowired
    private MaintenanceService service;

    @Autowired
    private VehicleHealthRepository healthRepo;

    @Autowired
    private MaintenanceReportRepository reportRepo;

    // 🔥 1️⃣ Analyze health data
    @PostMapping("/analyze")
    public MaintenanceReport analyze(@RequestBody VehicleHealthData data) {

        // Save input data
        healthRepo.save(data);

        // Analyze & generate report
        return service.analyze(data);
    }

    // 📊 2️⃣ Get all reports (Dashboard)
    @GetMapping("/reports")
    public List<MaintenanceReport> getAllReports() {
        return reportRepo.findAll();
    }

    // 🚗 3️⃣ Get reports by vehicle ID
    @GetMapping("/reports/{vehicleId}")
    public List<MaintenanceReport> getByVehicle(
            @PathVariable String vehicleId) {

        return reportRepo.findByVehicleId(vehicleId);
    }
}