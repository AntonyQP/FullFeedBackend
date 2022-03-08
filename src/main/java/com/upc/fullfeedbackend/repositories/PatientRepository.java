package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Patient;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    public Patient findByPatientId(Long patientId);

    @Query(value = "SELECT  (pl.weight - p.weight) weight_lose FROM patient_log pl CROSS JOIN patient p WHERE pl.patient_id = ?1 and p.patient_id = ?1 ORDER BY pl.date ASC LIMIT 1;", nativeQuery = true)
    public Integer getTotalWeightLoss(Long patientId);

    public Patient findByUser_UserId(Long userId);


}
