package com.neurofleetx.backend.controller;

import com.neurofleetx.backend.dto.DashboardStats;
import com.neurofleetx.backend.repository.BookingRepository;
import com.neurofleetx.backend.repository.MaintenanceAlertRepository;
import com.neurofleetx.backend.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin(origins = "*")   // Allow React frontend
public class AdminDashboardController {

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private MaintenanceAlertRepository alertRepo;

    // ⭐ MAIN DASHBOARD API
    @GetMapping("/stats")
    public DashboardStats getDashboardStats() {

        DashboardStats stats = new DashboardStats();

        // 🚗 Fleet Data
        stats.setTotalVehicles(vehicleRepo.count());

        stats.setAvailableVehicles(
                vehicleRepo.countByStatus("Available")
        );

        stats.setInUseVehicles(
                vehicleRepo.countByStatus("In Use")
        );

        stats.setMaintenanceVehicles(
                vehicleRepo.countByStatus("Maintenance")
        );

        // 📅 Booking Data
        stats.setTotalBookings(bookingRepo.count());

        Double revenue = bookingRepo.getTotalRevenue();
        stats.setTotalRevenue(revenue != null ? revenue : 0);

        // 🚨 Maintenance Alerts
        stats.setActiveAlerts(
                alertRepo.countByResolvedFalse()
        );

        stats.setCriticalAlerts(
                alertRepo.countBySeverityAndResolvedFalse("CRITICAL")
        );

        return stats;
    }
}