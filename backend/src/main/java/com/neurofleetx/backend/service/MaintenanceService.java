package com.neurofleetx.backend.service;

import com.neurofleetx.backend.model.VehicleHealthData;
import com.neurofleetx.backend.model.MaintenanceReport;
import com.neurofleetx.backend.repository.MaintenanceReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceReportRepository reportRepo;

    public MaintenanceReport analyze(VehicleHealthData data) {

        // 🧠 Initial health score
        int score = 100;

        // 🔥 Health scoring logic (penalties)

        if (data.getEngineTemp() > 95) {
            score -= 25;
        }

        if (data.getBatteryHealth() < 40) {
            score -= 20;
        }

        if (data.getTirePressure() < 28) {
            score -= 15;
        }

        if (data.getMileageSinceService() > 8000) {
            score -= 25;
        }

        if (data.isServiceOverdue()) {
            score -= 20;
        }

        // ⭐ Prevent negative score
        score = Math.max(0, score);

        // 🧠 Risk classification
        String risk;
        String recommendation;

        if (score >= 80) {
            risk = "Healthy";
            recommendation = "No action required.";
        }
        else if (score >= 50) {
            risk = "Warning";
            recommendation = "Schedule maintenance soon.";
        }
        else {
            risk = "Critical";
            recommendation = "Immediate inspection required!";
        }

        // 📊 Create report object
        MaintenanceReport report = new MaintenanceReport();
        report.setVehicleId(data.getVehicleId());
        report.setHealthScore(score);
        report.setRiskLevel(risk);
        report.setRecommendation(recommendation);

        // 💾 Save report to database
        return reportRepo.save(report);
    }
}