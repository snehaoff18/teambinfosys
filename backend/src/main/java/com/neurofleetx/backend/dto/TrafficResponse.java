package com.neurofleetx.backend.dto;

public class TrafficResponse {

    private String congestion;
    private String peakHours;

    public String getCongestion() {
        return congestion;
    }

    public void setCongestion(String congestion) {
        this.congestion = congestion;
    }

    public String getPeakHours() {
        return peakHours;
    }

    public void setPeakHours(String peakHours) {
        this.peakHours = peakHours;
    }
}