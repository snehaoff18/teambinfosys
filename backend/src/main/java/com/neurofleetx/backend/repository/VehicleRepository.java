package com.neurofleetx.backend.repository;

import com.neurofleetx.backend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // 🔹 Count vehicles by status (Dashboard)
    long countByStatus(String status);

    // 🔹 Get vehicles by status (Route Optimization)
    List<Vehicle> findByStatus(String status);

}