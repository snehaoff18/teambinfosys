package com.neurofleetx.backend.controller;

import com.neurofleetx.backend.dto.RouteRequest;
import com.neurofleetx.backend.dto.RouteResponse;
import com.neurofleetx.backend.model.RoutePlan;
import com.neurofleetx.backend.repository.RoutePlanRepository;
import com.neurofleetx.backend.service.RouteOptimizationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin
public class RouteController {

    private final RouteOptimizationService service;
    private final RoutePlanRepository routePlanRepository;

    public RouteController(RouteOptimizationService service,
                           RoutePlanRepository routePlanRepository) {
        this.service = service;
        this.routePlanRepository = routePlanRepository;
    }

    // 🚚 Optimize Route (Module 3 Core)
    @PostMapping("/optimize")
    public RouteResponse optimizeRoute(
            @RequestBody RouteRequest request) {

        return service.optimize(request);
    }

    // 📊 Get All Route Plans (Module 6 Support)
    @GetMapping("/history")
    public List<RoutePlan> getRouteHistory() {

        return routePlanRepository.findAll();
    }

    // 🔍 Get Route Plan by ID
    @GetMapping("/{id}")
    public RoutePlan getRouteById(@PathVariable Long id) {

        return routePlanRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Route plan not found"));
    }

    // ❌ Delete Route Plan (optional but useful)
    @DeleteMapping("/{id}")
    public String deleteRoute(@PathVariable Long id) {

        routePlanRepository.deleteById(id);
        return "Route plan deleted successfully";
    }
}
