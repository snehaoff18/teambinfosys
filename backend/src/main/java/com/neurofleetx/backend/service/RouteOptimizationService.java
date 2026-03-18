package com.neurofleetx.backend.service;

import com.neurofleetx.backend.dto.RouteRequest;
import com.neurofleetx.backend.dto.RouteResponse;
import com.neurofleetx.backend.model.Vehicle;
import com.neurofleetx.backend.repository.RoutePlanRepository;
import com.neurofleetx.backend.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import com.neurofleetx.backend.model.RoutePlan;
import java.time.LocalDateTime;


import java.util.ArrayList;
import java.util.List;

@Service
public class RouteOptimizationService {

    private final VehicleRepository vehicleRepository;
    private final RoutePlanRepository routePlanRepository;


    public RouteOptimizationService(
        VehicleRepository vehicleRepository,
        RoutePlanRepository routePlanRepository) {

    this.vehicleRepository = vehicleRepository;
    this.routePlanRepository = routePlanRepository;
}


   public RouteResponse optimize(RouteRequest request) {

    // STEP 1 — Select suitable vehicle
    Vehicle vehicle = selectVehicle(request.getTotalWeight());

    // STEP 2 — Optimize route
    List<String> optimizedRoute =
            optimizeStops(request.getPickupLocation(),
                          request.getDropLocations());

    // STEP 3 — Calculate distance & time
    double distance = calculateDistance(optimizedRoute);
    double time = distance / 40.0; // assume avg speed

    // ⭐ STEP 4 — SAVE ROUTE PLAN TO DATABASE
    RoutePlan plan = new RoutePlan();

    plan.setVehicleNumber(vehicle.getVehicleNumber());
    plan.setPickupLocation(request.getPickupLocation());
    plan.setStops(String.join(", ", optimizedRoute));
    plan.setTotalDistance(distance);
    plan.setEstimatedTime(time);
    plan.setCreatedAt(LocalDateTime.now());

    routePlanRepository.save(plan);

    // STEP 5 — Return response
    return new RouteResponse(
            vehicle.getVehicleNumber(),
            distance,
            time,
            optimizedRoute
    );
}


    // -------- Vehicle Selection --------
    private Vehicle selectVehicle(double weight) {

    List<Vehicle> available =
            vehicleRepository.findByStatus("Available");

    Vehicle bestVehicle = null;

    for (Vehicle v : available) {
        if (v.getCapacity() >= weight) {

            if (bestVehicle == null ||
                v.getCapacity() < bestVehicle.getCapacity()) {

                bestVehicle = v;
            }
        }
    }

    if (bestVehicle == null) {
        throw new RuntimeException("No suitable vehicle available");
    }

    return bestVehicle;
}



    // -------- Route Optimization --------
    private List<String> optimizeStops(String pickup,
                                   List<String> drops) {

    List<String> route = new ArrayList<>();

    route.add(pickup);

    // Simulated optimization
    drops.sort(String::compareTo);

    route.addAll(drops);

    return route;
}

    // -------- Distance Calculation --------
    private double calculateDistance(List<String> route) {

    double baseDistance = 10.0;  // pickup travel
    double perStop = 4.5;        // per delivery

    return baseDistance + (route.size() - 1) * perStop;
}

}
