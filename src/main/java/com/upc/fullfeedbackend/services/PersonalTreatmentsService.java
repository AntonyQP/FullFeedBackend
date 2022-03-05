package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.PersonalTreatments;
import com.upc.fullfeedbackend.repositories.PersonalTreatmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalTreatmentsService {

    @Autowired
    PersonalTreatmentsRepository personalTreatmentsRepository;


    public PersonalTreatments getById(Long personalTreatmentId){
        return personalTreatmentsRepository.findById(personalTreatmentId).get();
    }


}
