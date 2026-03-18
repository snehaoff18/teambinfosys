package com.neurofleetx.backend.service;

import com.neurofleetx.backend.dto.DemandPoint;
import com.neurofleetx.backend.dto.KpiResponse;
import com.neurofleetx.backend.dto.TrafficResponse;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UrbanInsightsService {

    public KpiResponse getKpis() {

        KpiResponse kpi = new KpiResponse();
        kpi.setActiveTrips(42);
        kpi.setAvgDuration("18 min");
        kpi.setUtilization("68%");
        kpi.setDemandIndex("High");

        return kpi;
    }

    public List<DemandPoint> getDemandByHour() {

        return Arrays.asList(
                new DemandPoint("6 AM", 20),
                new DemandPoint("7 AM", 40),
                new DemandPoint("8 AM", 65),
                new DemandPoint("9 AM", 80),
                new DemandPoint("10 AM", 55),
                new DemandPoint("11 AM", 30),
                new DemandPoint("12 PM", 15)
        );
    }

    public List<String> getTopHotspots() {

        return Arrays.asList(
                "Tech Park Zone",
                "Airport Road",
                "Central Station",
                "Downtown Mall"
        );
    }

    public TrafficResponse getTrafficData() {

        TrafficResponse traffic = new TrafficResponse();
        traffic.setCongestion("Moderate");
        traffic.setPeakHours("8–10 AM, 5–8 PM");

        return traffic;
    }
}