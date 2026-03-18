package com.neurofleetx.backend.dto;

public class DashboardStats {

    // 🚗 Fleet
    private long totalVehicles;
    private long availableVehicles;
    private long inUseVehicles;
    private long maintenanceVehicles;

    // 📅 Bookings
    private long totalBookings;

    // 💰 Revenue
    private double totalRevenue;

    // 🚨 Alerts
    private long activeAlerts;
    private long criticalAlerts;

    // ===== GETTERS & SETTERS =====

    public long getTotalVehicles() {
        return totalVehicles;
    }

    public void setTotalVehicles(long totalVehicles) {
        this.totalVehicles = totalVehicles;
    }

    public long getAvailableVehicles() {
        return availableVehicles;
    }

    public void setAvailableVehicles(long availableVehicles) {
        this.availableVehicles = availableVehicles;
    }

    public long getInUseVehicles() {
        return inUseVehicles;
    }

    public void setInUseVehicles(long inUseVehicles) {
        this.inUseVehicles = inUseVehicles;
    }

    public long getMaintenanceVehicles() {
        return maintenanceVehicles;
    }

    public void setMaintenanceVehicles(long maintenanceVehicles) {
        this.maintenanceVehicles = maintenanceVehicles;
    }

    public long getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getActiveAlerts() {
        return activeAlerts;
    }

    public void setActiveAlerts(long activeAlerts) {
        this.activeAlerts = activeAlerts;
    }

    public long getCriticalAlerts() {
        return criticalAlerts;
    }

    public void setCriticalAlerts(long criticalAlerts) {
        this.criticalAlerts = criticalAlerts;
    }
}