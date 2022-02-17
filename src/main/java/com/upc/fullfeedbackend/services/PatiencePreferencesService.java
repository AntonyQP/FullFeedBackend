package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.PatiencePreferences;
import com.upc.fullfeedbackend.repositories.PatiencePreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatiencePreferencesService {

    @Autowired
    PatiencePreferencesRepository preferencesRepository;


    public List<PatiencePreferences> savePatiencePreferences(List<PatiencePreferences> patiencePreferences){
        return preferencesRepository.saveAll(patiencePreferences);
    }

}
