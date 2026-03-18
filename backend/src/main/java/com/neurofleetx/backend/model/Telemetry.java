package com.neurofleetx.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "telemetry")
public class Telemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Link to Vehicle
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    private double latitude;
    private double longitude;

    private double speed;
    private double fuelLevel;
    private double engineTemp;
    private double batteryHealth;
    private double odometer;

    private LocalDateTime timestamp;

    // ===== Getters & Setters =====

    public Long getId() { return id; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    public double getFuelLevel() { return fuelLevel; }
    public void setFuelLevel(double fuelLevel) { this.fuelLevel = fuelLevel; }

    public double getEngineTemp() { return engineTemp; }
    public void setEngineTemp(double engineTemp) { this.engineTemp = engineTemp; }

    public double getBatteryHealth() { return batteryHealth; }
    public void setBatteryHealth(double batteryHealth) {
        this.batteryHealth = batteryHealth;
    }

    public double getOdometer() { return odometer; }
    public void setOdometer(double odometer) { this.odometer = odometer; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}