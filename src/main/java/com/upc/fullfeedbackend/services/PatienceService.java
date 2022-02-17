package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Patience;
import com.upc.fullfeedbackend.repositories.PatienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatienceService {

    @Autowired
    PatienceRepository patienceRepository;

    public List<Patience> getAllPatiences(){
        return patienceRepository.findAll();
    }

    public Patience updatePatience(Patience patience){
        return patienceRepository.save(patience);
    }

    public Patience getPatienceById(Long patienceId){
        return patienceRepository.findByPatienceId(patienceId);
    }

}
