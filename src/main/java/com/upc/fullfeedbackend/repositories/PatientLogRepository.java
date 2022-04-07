package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.PatientLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface PatientLogRepository extends JpaRepository<PatientLog, Long> {


    @Query(value = "SELECT * from PATIENT_LOG pl where pl.patient_id = ?1 ORDER BY pl.date DESC LIMIT 12",nativeQuery = true)
    List<PatientLog> getWeightHistorial(Long patientId);

}
