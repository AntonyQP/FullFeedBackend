package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Illnesses;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.PatientIllnesses;
import com.upc.fullfeedbackend.repositories.IllnessesRepository;
import com.upc.fullfeedbackend.repositories.PatientIllnessesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IllnessService {

    @Autowired
    IllnessesRepository illnessesRepository;

    @Autowired
    PatientIllnessesRepository patientIllnessesRepository;

    public List<Illnesses> getAllIllnesses(){
        return illnessesRepository.findAll();
    }

    public List<Illnesses> getIllnessesByPatient(Long patientId){
        return patientIllnessesRepository.findIllinessByPatient(patientId);
    }

    public List<PatientIllnesses> savePatientIllnesses(Patient patient, List<Long> illnessesIds){
        List<PatientIllnesses> patientIllnessesList = new ArrayList<>();

        for (Long illnessId: illnessesIds ){
            PatientIllnesses patientIllnesses = new PatientIllnesses();
            patientIllnesses.setIllnesses(illnessesRepository.findById(illnessId).orElse(null));
            patientIllnesses.setPatient(patient);
            patientIllnessesList.add(patientIllnesses);
        }

        return patientIllnessesRepository.saveAll(patientIllnessesList);
    }

    public List<PatientIllnesses> removePatientIllnesses(Patient patient, List<Long> illnessesIds){

        try {
            List<PatientIllnesses> list = patientIllnessesRepository.findPatientIllnessesByPatientAndIllness(patient.getPatientId(), illnessesIds);
            if (!list.isEmpty()){
                patientIllnessesRepository.deletePreferenceFromPatient(list);
                return new ArrayList<PatientIllnesses>();
            } else {
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
