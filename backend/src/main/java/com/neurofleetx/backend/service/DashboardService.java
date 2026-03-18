package com.neurofleetx.backend.service;

import com.neurofleetx.backend.dto.DashboardStats;
import com.neurofleetx.backend.repository.BookingRepository;
import com.neurofleetx.backend.repository.MaintenanceAlertRepository;
import com.neurofleetx.backend.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private MaintenanceAlertRepository alertRepo;

    public DashboardStats getDashboardStats() {

        DashboardStats stats = new DashboardStats();

        // 🚗 Fleet (Updated Status Model)
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

        // 📅 Bookings
        stats.setTotalBookings(bookingRepo.count());

        Double revenue = bookingRepo.getTotalRevenue();
        stats.setTotalRevenue(revenue != null ? revenue : 0);

        // 🚨 Alerts
        stats.setActiveAlerts(
                alertRepo.countByResolvedFalse()
        );

        stats.setCriticalAlerts(
                alertRepo.countBySeverityAndResolvedFalse("CRITICAL")
        );

        return stats;
    }
}