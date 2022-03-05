package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.NutritionalPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionalPlanRepository extends JpaRepository<NutritionalPlan, Long> {


    @Query("select n from NutritionalPlan n where n.personalTreatments.patient.patientId = ?1 and n.isActive = ?2")
    public NutritionalPlan findByPersonalTreatments_Patient_PatientIdAndAndIsActive(Long patientId, Byte isActive);

}
