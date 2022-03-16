package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Category;
import com.upc.fullfeedbackend.models.PatientPreferences;
import com.upc.fullfeedbackend.models.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.SpinnerUI;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PatientPreferencesRepository extends JpaRepository<PatientPreferences, Long> {

    @Query("select p from PatientPreferences p where p.patient.patientId = ?1 and p.preferences.preferencesId = ?2")
    public PatientPreferences findByPatientAndPreference(Long patientId, Long preferenceId);

    @Query("select p from PatientPreferences p where p.patient.patientId = ?1")
    public List<PatientPreferences> findAllByPatient_PatientId(Long patientId);

    @Transactional
    @Modifying
    @Query("DELETE FROM PatientPreferences p WHERE p.patientPreferencesId = ?1")
    Integer deletePreferenceFromPatient(Long roleId);

    @Query("select p.preferences from PatientPreferences p where p.patient.patientId = ?1 and p.type = 'ALLERGY'")
    public List<Preferences> getAllergiesByPatient(Long patientId);

    @Query("select p.preferences from PatientPreferences p where p.patient.patientId = ?1 and p.type = 'FAVORITE'")
    public List<Preferences> getFavoritesByPatient(Long patientId);

}
