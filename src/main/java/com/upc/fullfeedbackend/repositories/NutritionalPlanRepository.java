package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.NutritionalPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionalPlanRepository extends JpaRepository<NutritionalPlan, Long> {
}
