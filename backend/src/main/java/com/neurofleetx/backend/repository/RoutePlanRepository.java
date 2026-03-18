package com.neurofleetx.backend.repository;

import com.neurofleetx.backend.model.RoutePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutePlanRepository
        extends JpaRepository<RoutePlan, Long> {
}
