package com.neurofleetx.backend.controller;

import com.neurofleetx.backend.model.Telemetry;
import com.neurofleetx.backend.service.TelemetryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telemetry")
@CrossOrigin("*")
public class TelemetryController {

    private final TelemetryService telemetryService;

    public TelemetryController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    // 📡 Add telemetry for a vehicle
    @PostMapping("/{vehicleId}")
    public Telemetry addTelemetry(
            @PathVariable Long vehicleId,
            @RequestBody Telemetry telemetry) {

        return telemetryService.saveTelemetry(vehicleId, telemetry);
    }

    // 📊 Get history
    @GetMapping("/{vehicleId}")
    public List<Telemetry> getHistory(@PathVariable Long vehicleId) {
        return telemetryService.getHistory(vehicleId);
    }

    // ⚡ Get latest data
    @GetMapping("/latest/{vehicleId}")
    public Telemetry getLatest(@PathVariable Long vehicleId) {
        return telemetryService.getLatest(vehicleId);
    }
}