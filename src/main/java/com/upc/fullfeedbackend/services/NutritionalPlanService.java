package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.NutritionalPlan;
import com.upc.fullfeedbackend.repositories.NutritionalPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionalPlanService {


    @Autowired
    NutritionalPlanRepository nutritionalPlanRepository;


    public NutritionalPlan getNutritionalPlanById(Long nutritionalPlanId){
        return nutritionalPlanRepository.findById(nutritionalPlanId).get();
    }

    public NutritionalPlan getActiveNutritionalPlanByPatientId(Long patientId){
        byte active = 1;
        return nutritionalPlanRepository.findByPersonalTreatments_Patient_PatientIdAndAndIsActive(patientId, active);
    }

    public NutritionalPlan updateNutritionalPlan(NutritionalPlan nutritionalPlan){
        return nutritionalPlanRepository.save(nutritionalPlan);
    }

    public NutritionalPlan createNutritionalPlan(NutritionalPlan nutritionalPlan){
        Long patientId = nutritionalPlan.getPersonalTreatments().getPatient().getPatientId();
        NutritionalPlan lastNutritionalPlan = getActiveNutritionalPlanByPatientId(patientId);
        if (lastNutritionalPlan != null){
            byte desactived = 0;
            lastNutritionalPlan.setIsActive(desactived);
            updateNutritionalPlan(lastNutritionalPlan);
        }
        return nutritionalPlanRepository.save(nutritionalPlan);

    }

}
