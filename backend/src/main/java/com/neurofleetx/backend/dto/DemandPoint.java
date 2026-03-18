package com.neurofleetx.backend.dto;

public class DemandPoint {

    private String time;
    private int bookings;

    public DemandPoint(String time, int bookings) {
        this.time = time;
        this.bookings = bookings;
    }

    public String getTime() {
        return time;
    }

    public int getBookings() {
        return bookings;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBookings(int bookings) {
        this.bookings = bookings;
    }
}