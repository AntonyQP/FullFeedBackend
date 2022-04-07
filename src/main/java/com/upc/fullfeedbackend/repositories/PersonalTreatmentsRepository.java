package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Doctor;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.PersonalTreatments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PersonalTreatmentsRepository extends JpaRepository<PersonalTreatments, Long> {


    @Query("select p from PersonalTreatments p where p.patient.patientId = ?1 and p.active = ?2")
    PersonalTreatments findByPatient_PatientIdAndActive(Long patientId, Byte active);

    @Query("select p.patient from PersonalTreatments p where p.doctor.doctorId = ?1 and p.active = 1")
    List<Patient> findPatientsByDoctor(Long doctorId);

    @Query("select p.doctor from PersonalTreatments p where p.patient.patientId = ?1 and p.active = 1")
    Doctor getDoctorByPatient(Long patientId);

    @Query("select count(p) from PersonalTreatments p where p.active = 1 and p.doctor.doctorId = ?1")
    Integer countActivePatientsByDoctor(Long doctorId);


}
