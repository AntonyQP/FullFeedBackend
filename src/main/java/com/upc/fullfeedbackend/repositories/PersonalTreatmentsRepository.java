package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.PersonalTreatments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalTreatmentsRepository extends JpaRepository<PersonalTreatments, Long> {


    @Query("select p from PersonalTreatments p where p.patient.patientId = ?1 and p.active = ?2")

    public PersonalTreatments findByPatient_PatientIdAndActive(Long patientId, Byte active);

}
