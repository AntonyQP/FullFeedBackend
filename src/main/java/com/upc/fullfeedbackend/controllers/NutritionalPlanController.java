package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Meal;
import com.upc.fullfeedbackend.models.NutritionalPlan;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.PersonalTreatments;
import com.upc.fullfeedbackend.models.dto.*;
import com.upc.fullfeedbackend.services.MealService;
import com.upc.fullfeedbackend.services.NutritionalPlanService;
import com.upc.fullfeedbackend.services.PatientLogService;
import com.upc.fullfeedbackend.services.PersonalTreatmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Autowired
    PatientLogService patientLogService;

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
    public ResponseEntity<ResponseDTO<NutritionalPlanResponseDTO>> createNewNutritionalPlan(@RequestParam Long patientId){

        NutritionalPlan nutritionalPlan = new NutritionalPlan();
        NutritionalPlanResponseDTO nutritionalPlanResponseDTO = new NutritionalPlanResponseDTO();
        ResponseDTO<NutritionalPlanResponseDTO> responseDTO = new ResponseDTO<>();

        try {

            PersonalTreatments personalTreatments = personalTreatmentsService.getByPatientIdAndActive(patientId);

            byte active = 1;

            nutritionalPlan.setIsActive(active);
            nutritionalPlan.setPersonalTreatments(personalTreatments);
            Patient patient = nutritionalPlan.getPersonalTreatments().getPatient();
            nutritionalPlan.setWeightPatient((int)patient.getWeight());

            double calorias = 0;
            if (patient.getUser().getSex().equals("m"))
                calorias = (655 + (9.6 * patient.getWeight())) + ((1.8 * patient.getHeight()) - (4.7 * patient.getAge())) * 1.2;
            else
                calorias = (66 + (13.7 * patient.getWeight())) + ((5 * patient.getHeight()) - (6.8 * patient.getAge())) * 1.2;

            nutritionalPlan.setCaloriesPlan(calorias);
            nutritionalPlan = nutritionalPlanService.createNutritionalPlan(nutritionalPlan);

            //[655 + (9,6 × peso en kg) ] + [ (1,8 × altura en cm) – (4,7 × edad)] × Factor actividad. - Mujer
            //[66 + (13,7 × peso en kg) ] + [ (5 × altura en cm) – (6,8 × edad)] × Factor actividad. - Hombre

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

    @GetMapping("/consumedBalance")
    public ResponseEntity<ResponseDTO<List<ConsumedBalanceResponseDTO>>> getConsumedBalancedBetweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, @RequestParam Long patientId){
        ResponseDTO<List<ConsumedBalanceResponseDTO>> responseDTO = new ResponseDTO<>();

        try {
            List<ConsumedBalanceResponseDTO> consumedBalanced = mealService.getConsumedBalanced(patientId, startDate,endDate);
            if(consumedBalanced == null){
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(0);
                responseDTO.setErrorMessage("");
                responseDTO.setData(null);

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(consumedBalanced);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }catch (Exception e){
            e.getMessage();
        }

     return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/weightEvolution")
    public ResponseEntity<ResponseDTO<List<WeightEvolutionResponseDTO>>> getweightEvolution(@RequestParam Long patientId){
        ResponseDTO<List<WeightEvolutionResponseDTO>> responseDTO = new ResponseDTO<>();

        try {
            List<WeightEvolutionResponseDTO> listWeight = patientLogService.getWeightHistorial(patientId);
            if (listWeight == null){
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(1);;
                responseDTO.setErrorMessage("No se encontro un historial para el paciente");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);

            }
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);;
            responseDTO.setErrorMessage("");
            responseDTO.setData(listWeight);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e){
            e.getMessage();
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);


    }

}
