package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.PersonalTreatments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalTreatmentsRepository extends JpaRepository<PersonalTreatments, Long> {


    @Query("select p from PersonalTreatments p where p.patient.patientId = ?1 and p.active = ?2")

    public PersonalTreatments findByPatient_PatientIdAndActive(Long patientId, Byte active);

    @Query("select p.patient from PersonalTreatments p where p.doctor.doctorId = ?1 and p.active = 1")
    public List<Patient> findPatientsByDoctor(Long doctorId);

}
