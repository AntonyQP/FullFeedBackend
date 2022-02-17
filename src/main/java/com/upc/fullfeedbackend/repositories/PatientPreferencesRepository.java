package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.PatientPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PatientPreferencesRepository extends JpaRepository<PatientPreferences, Long> {

    public PatientPreferences findByPatient_PatientIdAndPreferences_Name(Long patientId, String preference);

    public List<PatientPreferences> findAllByPatient_PatientId(Long patientId);

    @Transactional
    @Modifying
    @Query("DELETE FROM PatientPreferences p WHERE p.patientPreferencesId = ?1")
    Integer deletePreferenceFromPatient(Long roleId);

}
