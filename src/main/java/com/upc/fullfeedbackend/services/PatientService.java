    package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.*;
import com.upc.fullfeedbackend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Meal generateDietByPatient(Long patientId){
        Patient patient = patientRepository.findByPatientId(patientId);


        PersonalTreatments personalTreatments = new PersonalTreatments();
        personalTreatments.setPatient(patient);
        personalTreatments.setStartDate(UtilService.getNowDate());
        personalTreatments.setActive((byte) 1);

        Doctor doctor = doctorService.getDoctorWhitMinorPatients();

        personalTreatments.setDoctor(doctor);

        personalTreatments = personalTreatmentsService.savePersonalTreatments(personalTreatments);

        double calories = UtilService.getCaloriesForPatient(patient);

        NutritionalPlan nutritionalPlan = new NutritionalPlan();
        nutritionalPlan.setWeightPatient(patient.getWeight());
        nutritionalPlan.setCaloriesPlan(redondearCalorias(calories));
        nutritionalPlan.setIsActive((byte) 1);
        nutritionalPlan.setPersonalTreatments(personalTreatments);

        nutritionalPlanService.saveNutritionalPlan(nutritionalPlan);

        List<Meal> meals = mealService.generateMonthMealsForPatient(patient.getPatientId(), redondearCalorias(calories) , (int) patient.getWeight());

        doctor.setActivePatients(doctor.getActivePatients()== null ? 1 : doctor.getActivePatients() + 1);
        doctorService.saveDoctor(doctor);
        if (meals != null)
        {
            if (meals.size() > 0)
                return meals.get(0);
        }
        return null;
    }

    public Integer redondearCalorias(double calories){
        int cal = (int) Math.round(calories / 10);
        return cal * 10;
    }


}
