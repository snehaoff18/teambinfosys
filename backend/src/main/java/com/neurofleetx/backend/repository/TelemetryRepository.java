package com.neurofleetx.backend.repository;

import com.neurofleetx.backend.model.Telemetry;
import com.neurofleetx.backend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TelemetryRepository extends JpaRepository<Telemetry, Long> {

    List<Telemetry> findByVehicle(Vehicle vehicle);

    Optional<Telemetry> findTopByVehicleOrderByTimestampDesc(Vehicle vehicle);
}