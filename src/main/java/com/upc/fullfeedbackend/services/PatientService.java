    package com.upc.fullfeedbackend.services;

import com.google.common.collect.Lists;
import com.upc.fullfeedbackend.models.*;
import com.upc.fullfeedbackend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PersonalTreatmentsService personalTreatmentsService;

    @Autowired
    NutritionalPlanService nutritionalPlanService;

    @Autowired
    MealService mealService;


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
        Integer lossWeight =  patientRepository.getTotalWeightLoss(patientId);
        if (lossWeight != null) return lossWeight;
        return 0;
    }
    public Patient getPatientByUserId(Long userId){
        return patientRepository.findByUser_UserId(userId);
    }

    public List<Meal> generateFirstDietToPatient(Long patientId, Long doctorId){
        Patient patient = patientRepository.findByPatientId(patientId);


        PersonalTreatments personalTreatmentsAux = new PersonalTreatments();
        Doctor doctor = doctorService.getDoctorById(doctorId);

        PersonalTreatments personalTreatments = personalTreatmentsService.getByPatientIdAndActive(patientId);
        if (personalTreatments == null){
            personalTreatmentsAux.setPatient(patient);
            personalTreatmentsAux.setStartDate(UtilService.getNowDate());
            personalTreatmentsAux.setActive((byte) 1);
            personalTreatments = personalTreatmentsAux;
        }
        personalTreatments.setDoctor(doctor);
        personalTreatments = personalTreatmentsService.savePersonalTreatments(personalTreatments);

        doctor.setActivePatients(personalTreatmentsService.getActivePatientsByDoctor(doctorId));
        doctorService.saveDoctor(doctor);

        double calories = UtilService.getCaloriesForPatient(patient);

        NutritionalPlan nutritionalPlan = new NutritionalPlan();
        nutritionalPlan.setWeightPatient(patient.getWeight());
        nutritionalPlan.setCaloriesPlan(redondearCalorias(calories));
        nutritionalPlan.setIsActive((byte) 1);
        nutritionalPlan.setPersonalTreatments(personalTreatments);

        nutritionalPlanService.createNutritionalPlan(nutritionalPlan);

        List<Meal> meals = mealService.generateMonthMealsForPatient(patient.getPatientId(), redondearCalorias(calories) , (int) patient.getWeight());

        List<Meal> mealsFirstWeek = new ArrayList<>();
        if (meals != null)
        {
            if (meals.size() > 0) {
                for (int i = 0; i< 35; i++) {
                    mealsFirstWeek.add(meals.get(i));
                }
                return mealsFirstWeek;
            }
        }
        return null;
    }

    public Integer redondearCalorias(double calories){
        int cal = (int) Math.round(calories / 10);
        return cal * 10;
    }

    public Doctor getDoctorByPatient(Long patientId){
        return personalTreatmentsService.getDoctorByPatient(patientId);
    }


}
