package com.neurofleetx.backend.service;

import com.neurofleetx.backend.model.Telemetry;
import com.neurofleetx.backend.model.Vehicle;
import com.neurofleetx.backend.repository.TelemetryRepository;
import com.neurofleetx.backend.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TelemetryService {

    private final TelemetryRepository telemetryRepo;
    private final VehicleRepository vehicleRepo;

    public TelemetryService(TelemetryRepository telemetryRepo,
                            VehicleRepository vehicleRepo) {
        this.telemetryRepo = telemetryRepo;
        this.vehicleRepo = vehicleRepo;
    }

    public Telemetry saveTelemetry(Long vehicleId, Telemetry telemetry) {

        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        telemetry.setVehicle(vehicle);
        telemetry.setTimestamp(LocalDateTime.now());

        return telemetryRepo.save(telemetry);
    }

    public List<Telemetry> getHistory(Long vehicleId) {

        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        return telemetryRepo.findByVehicle(vehicle);
    }

    public Telemetry getLatest(Long vehicleId) {

        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        return telemetryRepo
                .findTopByVehicleOrderByTimestampDesc(vehicle)
                .orElse(null);
    }
}