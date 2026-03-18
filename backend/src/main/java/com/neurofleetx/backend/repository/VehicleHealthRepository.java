package com.neurofleetx.backend.repository;

import com.neurofleetx.backend.model.VehicleHealthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleHealthRepository 
        extends JpaRepository<VehicleHealthData, Long> {
}