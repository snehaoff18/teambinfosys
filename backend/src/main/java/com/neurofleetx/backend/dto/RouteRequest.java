package com.neurofleetx.backend.dto;

import java.util.List;

public class RouteRequest {

    private String pickupLocation;
    private List<String> dropLocations;
    private double totalWeight;

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public List<String> getDropLocations() { return dropLocations; }
    public void setDropLocations(List<String> dropLocations) {
        this.dropLocations = dropLocations;
    }

    public double getTotalWeight() { return totalWeight; }
    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }
}
