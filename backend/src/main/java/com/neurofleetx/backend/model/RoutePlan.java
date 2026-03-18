package com.neurofleetx.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RoutePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNumber;
    private String pickupLocation;

    @Column(length = 1000)
    private String stops;  // store as comma-separated

    private double totalDistance;
    private double estimatedTime;

    private LocalDateTime createdAt;

    // Getters & Setters

    public Long getId() { return id; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getStops() { return stops; }
    public void setStops(String stops) { this.stops = stops; }

    public double getTotalDistance() { return totalDistance; }
    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getEstimatedTime() { return estimatedTime; }
    public void setEstimatedTime(double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
