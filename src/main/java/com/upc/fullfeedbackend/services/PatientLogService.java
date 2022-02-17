package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.PatientLog;
import com.upc.fullfeedbackend.repositories.PatientLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientLogService {

    @Autowired
    PatientLogRepository patientLogRepository;


    public PatientLog savePatientLog(PatientLog patientLog){
        return patientLogRepository.save(patientLog);
    }

    public List<PatientLog> getAllPatientsLogs(Long patientId){
        return patientLogRepository.findAll();
    }
}
