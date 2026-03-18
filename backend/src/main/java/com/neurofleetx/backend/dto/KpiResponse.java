package com.neurofleetx.backend.dto;

public class KpiResponse {

    private int activeTrips;
    private String avgDuration;
    private String utilization;
    private String demandIndex;

    public int getActiveTrips() {
        return activeTrips;
    }

    public void setActiveTrips(int activeTrips) {
        this.activeTrips = activeTrips;
    }

    public String getAvgDuration() {
        return avgDuration;
    }

    public void setAvgDuration(String avgDuration) {
        this.avgDuration = avgDuration;
    }

    public String getUtilization() {
        return utilization;
    }

    public void setUtilization(String utilization) {
        this.utilization = utilization;
    }

    public String getDemandIndex() {
        return demandIndex;
    }

    public void setDemandIndex(String demandIndex) {
        this.demandIndex = demandIndex;
    }
}