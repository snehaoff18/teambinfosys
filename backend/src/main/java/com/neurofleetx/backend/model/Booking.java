package com.neurofleetx.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String pickupLocation;
    private String dropLocation;

    private LocalDateTime bookingTime;

    private String vehicleType;
    private int passengers;

    private double estimatedFare;

    private String status; 
    // CONFIRMED, COMPLETED, CANCELLED

    // 🔹 Default Constructor (Required by JPA)
    public Booking() {
    }

    // 🔹 Parameterized Constructor
    public Booking(Long id, String customerName, String pickupLocation,
                   String dropLocation, LocalDateTime bookingTime,
                   String vehicleType, int passengers,
                   double estimatedFare, String status) {

        this.id = id;
        this.customerName = customerName;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.bookingTime = bookingTime;
        this.vehicleType = vehicleType;
        this.passengers = passengers;
        this.estimatedFare = estimatedFare;
        this.status = status;
    }

    // 🔹 Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public double getEstimatedFare() {
        return estimatedFare;
    }

    public void setEstimatedFare(double estimatedFare) {
        this.estimatedFare = estimatedFare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}