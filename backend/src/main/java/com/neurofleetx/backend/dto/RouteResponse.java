package com.neurofleetx.backend.dto;

import java.util.List;

public class RouteResponse {

    private String vehicleNumber;
    private double totalDistance;
    private double estimatedTime;
    private List<String> optimizedRoute;

    public RouteResponse(String vehicleNumber,
                         double totalDistance,
                         double estimatedTime,
                         List<String> optimizedRoute) {
        this.vehicleNumber = vehicleNumber;
        this.totalDistance = totalDistance;
        this.estimatedTime = estimatedTime;
        this.optimizedRoute = optimizedRoute;
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public double getTotalDistance() { return totalDistance; }
    public double getEstimatedTime() { return estimatedTime; }
    public List<String> getOptimizedRoute() { return optimizedRoute; }
}
