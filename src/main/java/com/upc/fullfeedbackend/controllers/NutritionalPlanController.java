package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Meal;
import com.upc.fullfeedbackend.models.NutritionalPlan;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.PersonalTreatments;
import com.upc.fullfeedbackend.models.dto.NutritionalPlanRequestDTO;
import com.upc.fullfeedbackend.models.dto.NutritionalPlanResponseDTO;
import com.upc.fullfeedbackend.models.dto.ResponseDTO;
import com.upc.fullfeedbackend.services.MealService;
import com.upc.fullfeedbackend.services.NutritionalPlanService;
import com.upc.fullfeedbackend.services.PersonalTreatmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nutritionalPlan")
public class NutritionalPlanController {

    @Autowired
    NutritionalPlanService nutritionalPlanService;

    @Autowired
    PersonalTreatmentsService personalTreatmentsService;

    @Autowired
    MealService mealService;

    @GetMapping("")
    public ResponseEntity<ResponseDTO<NutritionalPlan>> getActiveNutritionalPlan(@RequestParam Long patientId){
        ResponseDTO<NutritionalPlan>  responseDTO = new ResponseDTO<>();
        NutritionalPlan nutritionalPlan = new NutritionalPlan();

        try {
            nutritionalPlan = nutritionalPlanService.getActiveNutritionalPlanByPatientId(patientId);

            if (nutritionalPlan == null){
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(1);
                responseDTO.setErrorMessage("El usuario no tiene un plan nutricional activo");
                responseDTO.setData(null);

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }

            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(nutritionalPlan);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }catch (Exception e){
            e.getMessage();
        }

        responseDTO.setHttpCode(HttpStatus.OK.value());
        responseDTO.setErrorCode(2);
        responseDTO.setErrorMessage("Ocurrio un error al intentar recuperar el plan nutricional");
        responseDTO.setData(nutritionalPlan);


        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @PostMapping("/new")
    public ResponseEntity<ResponseDTO<NutritionalPlanResponseDTO>> createNewNutritionalPlan(@RequestBody NutritionalPlanRequestDTO requestDTO){

        NutritionalPlan nutritionalPlan = new NutritionalPlan();
        NutritionalPlanResponseDTO nutritionalPlanResponseDTO = new NutritionalPlanResponseDTO();
        ResponseDTO<NutritionalPlanResponseDTO> responseDTO = new ResponseDTO<>();

        try {

            PersonalTreatments personalTreatments = personalTreatmentsService.getByPatientIdAndActive(requestDTO.getPatientId());
            byte active = 1;
            nutritionalPlan.setIsActive(active);
            nutritionalPlan.setPersonalTreatments(personalTreatments);
            nutritionalPlan.setCaloriesPlan(requestDTO.getCaloriesPlan());
            nutritionalPlan.setWeightPatient(requestDTO.getWeightPatient());

            nutritionalPlan = nutritionalPlanService.createNutritionalPlan(nutritionalPlan);

            Patient patient = nutritionalPlan.getPersonalTreatments().getPatient();

            double calorias = (66 + (13.7 * patient.getWeight())) + ((5 * patient.getHeight()) - (6.8 * patient.getAge())) * 1.2;

            //[66 + (13,7 × peso en kg) ] + [ (5 × altura en cm) – (6,8 × edad)] × Factor actividad.
            List<Meal> meals =  mealService.generateMonthMealsForPatient(patient.getPatientId(), redondearCalorias(calorias), (int)patient.getWeight());

            nutritionalPlanResponseDTO.setIsActive(nutritionalPlan.getIsActive());
            nutritionalPlanResponseDTO.setNutritionalPlanId(nutritionalPlan.getNutritionalPlanId());
            nutritionalPlanResponseDTO.setPersonalTreatmentId(nutritionalPlan.getPersonalTreatments().getPersonalTreatmentId());
            nutritionalPlanResponseDTO.setCaloriesPlan(nutritionalPlan.getCaloriesPlan());
            nutritionalPlanResponseDTO.setWeightPatient(nutritionalPlan.getWeightPatient());



            if (meals.equals(null)){
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(1);
                responseDTO.setErrorMessage("Ocurrio un error al generar la dieta");
                responseDTO.setData(null);

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }

            responseDTO.setHttpCode(HttpStatus.CREATED.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(nutritionalPlanResponseDTO);

            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

        }catch (Exception e){
            e.getMessage();
        }

        responseDTO.setHttpCode(HttpStatus.OK.value());
        responseDTO.setErrorCode(1);
        responseDTO.setErrorMessage("Ocurrio un error");
        responseDTO.setData(null);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    public Integer redondearCalorias(double calorias){
        int cal = (int) Math.round(calorias / 10);
        return cal * 10;
    }

}
