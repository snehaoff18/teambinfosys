package com.neurofleetx.backend.controller;

import com.neurofleetx.backend.dto.DemandPoint;
import com.neurofleetx.backend.dto.KpiResponse;
import com.neurofleetx.backend.dto.TrafficResponse;
import com.neurofleetx.backend.service.UrbanInsightsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insights")
@CrossOrigin(origins = "http://localhost:3000")
public class UrbanInsightsController {

    private final UrbanInsightsService service;

    // ✅ Constructor Injection (BEST PRACTICE)
    public UrbanInsightsController(UrbanInsightsService service) {
        this.service = service;
    }

    /* ================= KPIs ================= */

    @GetMapping("/kpis")
    public ResponseEntity<KpiResponse> getKpis() {
        return ResponseEntity.ok(service.getKpis());
    }

    /* ================= Demand Analytics ================= */

    @GetMapping("/demand")
    public ResponseEntity<List<DemandPoint>> getDemand() {
        return ResponseEntity.ok(service.getDemandByHour());
    }

    /* ================= Pickup Hotspots ================= */

    @GetMapping("/hotspots")
    public ResponseEntity<List<String>> getHotspots() {
        return ResponseEntity.ok(service.getTopHotspots());
    }

    /* ================= Traffic Intelligence ================= */

    @GetMapping("/traffic")
    public ResponseEntity<TrafficResponse> getTraffic() {
        return ResponseEntity.ok(service.getTrafficData());
    }
}