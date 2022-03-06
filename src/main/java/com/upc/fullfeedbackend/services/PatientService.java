package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.PatientPreferences;
import com.upc.fullfeedbackend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;


    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Patient updatePatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long patientId){
        return patientRepository.findByPatientId(patientId);
    }


    public Integer getTotalLostWeightByPatient(Long patientId){
        return patientRepository.getTotalWeightLoss(patientId);
    }

}
