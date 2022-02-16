package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Patience;
import com.upc.fullfeedbackend.models.PatienceLog;
import com.upc.fullfeedbackend.repositories.PatienceLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatienceLogService {

    @Autowired
    PatienceLogRepository patienceLogRepository;


    public PatienceLog savePatienceLog(PatienceLog patienceLog){
        return patienceLogRepository.save(patienceLog);
    }

    public List<PatienceLog> getAllPatienceLogs(Long patienceId){
        return patienceLogRepository.findAll();
    }
}
