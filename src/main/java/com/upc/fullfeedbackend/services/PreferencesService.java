package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Preferences;
import com.upc.fullfeedbackend.repositories.PreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferencesService {

    @Autowired
    PreferencesRepository preferencesRepository;


    public List<Preferences> getAllPreferences(){
        return preferencesRepository.findAll();
    }


    public Preferences findByPreferences(String name){return preferencesRepository.findByName(name);}


}
