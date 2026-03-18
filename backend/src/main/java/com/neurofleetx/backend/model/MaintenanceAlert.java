package com.neurofleetx.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MaintenanceAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleId;
    private String issue;
    private String severity;   // LOW, MEDIUM, HIGH, CRITICAL
    private boolean resolved;

    private String description;
    private String reportedBy;
    private String assignedTechnician;

    private LocalDateTime reportedAt = LocalDateTime.now();
    private LocalDateTime resolvedAt;

    // -------- Getters & Setters --------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getIssue() { return issue; }
    public void setIssue(String issue) { this.issue = issue; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getReportedBy() { return reportedBy; }
    public void setReportedBy(String reportedBy) { this.reportedBy = reportedBy; }

    public String getAssignedTechnician() { return assignedTechnician; }
    public void setAssignedTechnician(String assignedTechnician) {
        this.assignedTechnician = assignedTechnician;
    }

    public LocalDateTime getReportedAt() { return reportedAt; }
    public void setReportedAt(LocalDateTime reportedAt) { this.reportedAt = reportedAt; }

    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }
}