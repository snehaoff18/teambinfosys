package com.neurofleetx.backend.service;

import com.neurofleetx.backend.model.Booking;
import com.neurofleetx.backend.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // 🔹 Create a new booking
    public Booking createBooking(Booking booking) {

        // Set booking time automatically
        booking.setBookingTime(LocalDateTime.now());

        // 🔥 Smart Vehicle Recommendation Logic
        int passengers = booking.getPassengers();
        String destination = booking.getDropLocation();

        String vehicleType;

        if (destination != null &&
            destination.toLowerCase().contains("airport")) {
            vehicleType = "Premium Sedan";
        }
        else if (passengers <= 2) {
            vehicleType = "Bike";
        }
        else if (passengers <= 4) {
            vehicleType = "Sedan";
        }
        else {
            vehicleType = "SUV";
        }

        booking.setVehicleType(vehicleType);

        // 💰 Fare estimation
        double baseFare = 50;
        double fare = baseFare + (passengers * 20);

        booking.setEstimatedFare(fare);

        // Default status
        booking.setStatus("CONFIRMED");

        return bookingRepository.save(booking);
    }

    // 🔹 Get bookings for a specific customer
    public List<Booking> getBookingsByCustomer(String customerName) {
        return bookingRepository.findByCustomerName(customerName);
    }

    // 🔹 Get bookings by status
    public List<Booking> getBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }

    // 🔹 Get all bookings (Admin use)
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}