package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Illnesses;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.PatientIllnesses;
import com.upc.fullfeedbackend.models.PatientPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PatientIllnessesRepository extends JpaRepository<PatientIllnesses, Long> {

    @Query("select p.illnesses from PatientIllnesses p where p.patient.patientId = ?1")
    public List<Illnesses> findIllinessByPatient(Long patientId);


    @Query("select p from PatientIllnesses p where p.patient.patientId = ?1 and p.illnesses.illnessesId in ?2")
    public List<PatientIllnesses> findPatientIllnessesByPatientAndIllness(Long patientId, List<Long> illnessId);

    @Query("select p from PatientIllnesses p where p.patient.patientId = ?1 and p.illnesses.illnessesId = ?2")
    public PatientIllnesses findPatientIllnessesByPatientAndIllness(Long patientId, Long illnessId);

    @Transactional
    @Modifying
    @Query("DELETE FROM PatientIllnesses p WHERE p in ?1")
    public void deletePreferenceFromPatient(List<PatientIllnesses> patientIllnessId);

}
