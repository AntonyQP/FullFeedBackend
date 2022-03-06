package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Patient;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    public Patient findByPatientId(Long patientId);

    //public Integer get


}
