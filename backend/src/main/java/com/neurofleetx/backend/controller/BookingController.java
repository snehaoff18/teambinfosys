package com.neurofleetx.backend.controller;

import com.neurofleetx.backend.model.Booking;
import com.neurofleetx.backend.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*") // allow React frontend
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // 🚗 Create a new booking
    @PostMapping("/create")
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    // 👤 Get bookings for a specific customer
    @GetMapping("/customer/{name}")
    public List<Booking> getCustomerBookings(
            @PathVariable String name) {

        return bookingService.getBookingsByCustomer(name);
    }

    // 📊 Get bookings by status
    @GetMapping("/status/{status}")
    public List<Booking> getBookingsByStatus(
            @PathVariable String status) {

        return bookingService.getBookingsByStatus(status);
    }

    // 🧠 Get all bookings (Admin Dashboard — Module 6)
    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}