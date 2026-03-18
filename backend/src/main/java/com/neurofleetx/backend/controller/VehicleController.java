package com.neurofleetx.backend.controller;

import com.neurofleetx.backend.model.Vehicle;
import com.neurofleetx.backend.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*") // allow React frontend
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepo;

    // ✅ Get all vehicles
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepo.findAll();
        return ResponseEntity.ok(vehicles);
    }

    // ✅ Get vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleRepo.findById(id);

        return vehicle.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Add new vehicle
    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleRepo.save(vehicle);
        return ResponseEntity.ok(savedVehicle);
    }

    // ✅ Update existing vehicle
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable Long id,
            @RequestBody Vehicle updatedVehicle) {

        Optional<Vehicle> existingVehicle = vehicleRepo.findById(id);

        if (existingVehicle.isPresent()) {
            Vehicle vehicle = existingVehicle.get();

            vehicle.setVehicleNumber(updatedVehicle.getVehicleNumber());
            vehicle.setType(updatedVehicle.getType());
            vehicle.setCapacity(updatedVehicle.getCapacity());
            vehicle.setModel(updatedVehicle.getModel());
            vehicle.setManufacturer(updatedVehicle.getManufacturer());
            vehicle.setYear(updatedVehicle.getYear());
            vehicle.setStatus(updatedVehicle.getStatus());

            vehicleRepo.save(vehicle);
            return ResponseEntity.ok(vehicle);
        }

        return ResponseEntity.notFound().build();
    }

    // ✅ Delete vehicle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {

        if (vehicleRepo.existsById(id)) {
            vehicleRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}