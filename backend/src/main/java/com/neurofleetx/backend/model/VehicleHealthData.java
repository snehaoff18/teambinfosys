package com.neurofleetx.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VehicleHealthData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleId;

    private double engineTemp;
    private double batteryHealth;
    private double tirePressure;
    private double mileageSinceService;
    private boolean serviceOverdue;

    private LocalDateTime createdAt = LocalDateTime.now();

    // ----- Getters and Setters -----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public double getEngineTemp() { return engineTemp; }
    public void setEngineTemp(double engineTemp) { this.engineTemp = engineTemp; }

    public double getBatteryHealth() { return batteryHealth; }
    public void setBatteryHealth(double batteryHealth) { this.batteryHealth = batteryHealth; }

    public double getTirePressure() { return tirePressure; }
    public void setTirePressure(double tirePressure) { this.tirePressure = tirePressure; }

    public double getMileageSinceService() { return mileageSinceService; }
    public void setMileageSinceService(double mileageSinceService) { this.mileageSinceService = mileageSinceService; }

    public boolean isServiceOverdue() { return serviceOverdue; }
    public void setServiceOverdue(boolean serviceOverdue) { this.serviceOverdue = serviceOverdue; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}