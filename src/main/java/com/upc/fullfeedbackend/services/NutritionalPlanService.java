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
}
