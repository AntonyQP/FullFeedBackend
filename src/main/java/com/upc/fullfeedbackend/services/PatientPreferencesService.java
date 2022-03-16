package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.PatientPreferences;
import com.upc.fullfeedbackend.models.Preferences;
import com.upc.fullfeedbackend.repositories.PatientPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatientPreferencesService {

    @Autowired
    PatientPreferencesRepository patientPreferencesRepository;


    public List<PatientPreferences> savePatientPreferences(List<PatientPreferences> patientPreferences){
        return patientPreferencesRepository.saveAll(patientPreferences);
    }

    public PatientPreferences removePatientPrefences(Long patientId, Long preferenceId){
        PatientPreferences patientPreferences =  findByPatientAndPreference(patientId, preferenceId);
        if (patientPreferences != null){
            patientPreferencesRepository.deletePreferenceFromPatient(patientPreferences.getPatientPreferencesId());
            return patientPreferences;
        }
        return null;
    }

    public PatientPreferences findByPatientAndPreference(Long patientId, Long preferenceId){
        return patientPreferencesRepository.findByPatientAndPreference(patientId, preferenceId);
    }

    public List<Preferences> findAllergiesByPatient(Long patientId){
        return patientPreferencesRepository.getAllergiesByPatient(patientId);
    }

    public List<Preferences> findFavoritesByPatient(Long patientId){
        return patientPreferencesRepository.getFavoritesByPatient(patientId);
    }

    public List<PatientPreferences> findByPatientId(Long patientId){
        return patientPreferencesRepository.findAllByPatient_PatientId(patientId);
    }

}
