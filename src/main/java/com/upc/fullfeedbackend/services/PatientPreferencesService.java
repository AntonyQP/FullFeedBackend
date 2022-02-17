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


    public PatientPreferences removePatientPrefences(Long patientId, String preference){
        PatientPreferences patientPreferences =  findByNameAndPatient(patientId, preference);
        if (patientPreferences != null){
            patientPreferencesRepository.deletePreferenceFromPatient(patientPreferences.getPatientPreferencesId());
            return patientPreferences;
        }
        return null;
    }

    public PatientPreferences findByNameAndPatient(Long patientId, String preference){
        return patientPreferencesRepository.findByPatient_PatientIdAndPreferences_Name(patientId, preference);
    }

    public List<PatientPreferences> findPreferencesByPatient(Long patientId){
        return  patientPreferencesRepository.findAllByPatient_PatientId(patientId);
    }

}
