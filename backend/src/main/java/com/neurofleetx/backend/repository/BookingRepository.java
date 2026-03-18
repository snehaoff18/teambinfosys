package com.neurofleetx.backend.repository;

import com.neurofleetx.backend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // 🔹 For dashboard revenue
    @Query("SELECT SUM(b.estimatedFare) FROM Booking b")
    Double getTotalRevenue();

    // 🔹 Find bookings by customer name
    List<Booking> findByCustomerName(String customerName);

    // 🔹 Find bookings by status
    List<Booking> findByStatus(String status);
}