package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.*;
import com.upc.fullfeedbackend.models.dto.PatientUpdateDTO;
import com.upc.fullfeedbackend.models.dto.PreferencesDTO;
import com.upc.fullfeedbackend.models.dto.PatientProgressDTO;
import com.upc.fullfeedbackend.models.dto.ResponseDTO;
import com.upc.fullfeedbackend.services.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.sql.Time;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    PatientLogService patientLogService;

    @Autowired
    PatientPreferencesService patientPreferencesService;

    @Autowired
    PreferencesService preferencesService;

    @Autowired
    MealService mealService;

    @Autowired
    DoctorService doctorService;


    @GetMapping("")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @PostMapping("/saveLog")
    public PatientLog savePatientLog(@RequestBody PatientLog patientLog){
        return patientLogService.savePatientLog(patientLog);
    }
    @PutMapping("/updatePatient")
    public ResponseEntity<ResponseDTO<Patient>> updatePatient(@RequestBody PatientUpdateDTO patientUpdateDTO){

        PatientLog patientLog = new PatientLog();

        Date date = UtilService.getNowDate();

        ResponseDTO<Patient> responseDTO = new ResponseDTO<>();

        try {
                Patient patient = patientService.getPatientById(patientUpdateDTO.getPatientId());

                patient.setArm(patientUpdateDTO.getArm());
                patient.setHeight(patientUpdateDTO.getHeight());
                patient.setImc(patientUpdateDTO.getImc());
                patient.setTmb(patientUpdateDTO.getTmb());
                patient.setWeight(patientUpdateDTO.getWeight());
                patient.setAbdominal(patientUpdateDTO.getAbdominal());

                patientLog.setPatient(patient);
                patientLog.setArm(patientUpdateDTO.getArm());
                patientLog.setDate(date);
                patientLog.setHeight(patientUpdateDTO.getHeight());
                patientLog.setImc(patientUpdateDTO.getImc());
                patientLog.setTmb(patientUpdateDTO.getTmb());
                patientLog.setWeight(patientUpdateDTO.getWeight());
                patientLog.setAbdominal(patientUpdateDTO.getAbdominal());

                patientLogService.savePatientLog(patientLog);

                responseDTO.setErrorCode(0);
                responseDTO.setErrorMessage("");
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setData(patientService.updatePatient(patient));

                return new ResponseEntity<>(responseDTO , HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(null , HttpStatus.NO_CONTENT);
    }

    @GetMapping("/historial")
    public ResponseEntity<ResponseDTO<List<PatientLog>>> getHistorial(@RequestParam Long patientId){

        ResponseDTO<List<PatientLog>> responseDTO = new ResponseDTO<>();
        try {
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setData(patientLogService.getAllPatientsLogs(patientId));
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/preferences")
    public ResponseEntity<ResponseDTO<List<PatientPreferences>>> getPrefernces(@RequestParam Long patientId){
        ResponseDTO<List<PatientPreferences>> responseDTO = new ResponseDTO<>();

        try {
            List<PatientPreferences> patientPreferences = patientPreferencesService.findByPatientId(patientId);
            if (patientPreferences.isEmpty()){
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(1);
                responseDTO.setErrorMessage("No existen preferencias para este paciente");
                responseDTO.setData(new ArrayList<>());
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);

            }
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(patientPreferences);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e){
            e.getMessage();
        }
        responseDTO.setHttpCode(HttpStatus.OK.value());
        responseDTO.setErrorCode(2);
        responseDTO.setErrorMessage("Ocurrio un error al recuperar la información");
        responseDTO.setData(null);

        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/preferences")
    public ResponseEntity<ResponseDTO<List<PatientPreferences>>> savePreferences(@RequestParam Long patientId, @RequestBody List<PreferencesDTO> preferencesDTOList){
        List<PatientPreferences> patientPreferencesList = new ArrayList<PatientPreferences>();

        Patient patient = patientService.getPatientById(patientId);

        ResponseDTO<List<PatientPreferences>> responseDTO = new ResponseDTO<>();

        try {
            for ( PreferencesDTO preference: preferencesDTOList) {
                PatientPreferences exist = patientPreferencesService.findByPatientAndPreference(patientId, preference.getPreferenceId());
                if (exist != null){
                    responseDTO.setData(null);
                    responseDTO.setHttpCode(HttpStatus.OK.value());
                    responseDTO.setErrorCode(1);
                    responseDTO.setErrorMessage("El usuario actual ya tiene a la preferencias" +  exist.getPreferences().getName() + "registrada");

                    return new ResponseEntity<>(responseDTO, HttpStatus.OK);
                }

                PatientPreferences patientPreferences = new PatientPreferences();
                patientPreferences.setPatient(patient);
                patientPreferences.setPreferences(preferencesService.findById(preference.getPreferenceId()));
                patientPreferences.setType(preference.getType());
                patientPreferencesList.add(patientPreferences);
            }
            responseDTO.setData(patientPreferencesService.savePatientPreferences(patientPreferencesList));
            responseDTO.setHttpCode(HttpStatus.CREATED.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");

            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

        }catch (Exception e){
            e.getMessage();
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/preferences")
    public ResponseEntity<ResponseDTO<PatientPreferences>> removePatientPreferences(@RequestParam Long patientId, @RequestParam Long preferenceId){

        ResponseDTO<PatientPreferences> responseDTO = new ResponseDTO<>();
        PatientPreferences patientPreferences = patientPreferencesService.removePatientPrefences(patientId,preferenceId);
        if (patientPreferences != null){
            try {
                responseDTO.setData(patientPreferences);
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(0);
                responseDTO.setErrorMessage("");

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);

            }catch (Exception e){
                e.getMessage();
            }
        }
        else {
            responseDTO.setData(null);
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(1);
            responseDTO.setErrorMessage("La preferencia no existe para este usuario");
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/successfulDays")
    public ResponseEntity<ResponseDTO<PatientProgressDTO>> getProgressbyPatient(@RequestParam Long patientId){
        ResponseDTO<PatientProgressDTO> responseDTO = new ResponseDTO<>();
        PatientProgressDTO patientProgressDTO = new PatientProgressDTO();
        try {

            Integer successfulDays =  mealService.getCountOfSuccessfulDaysByPatient(patientId);
            patientProgressDTO.setSuccessfulDays(successfulDays);
            Integer totalWeightLost = patientService.getTotalLostWeightByPatient(patientId);
            patientProgressDTO.setLostWeight(totalWeightLost);

            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorMessage("");
            responseDTO.setErrorCode(0);
            responseDTO.setData(patientProgressDTO);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }catch (Exception e){
            e.getMessage();
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/generateDiet")
    public ResponseEntity<ResponseDTO<List<Meal>>> generateDietAtRegister(@RequestParam Long patientId, @RequestParam Long doctorId){

        ResponseDTO<List<Meal>> responseDTO = new ResponseDTO<>();
        String errorMessage = "";
        try {

            responseDTO.setHttpCode(HttpStatus.CREATED.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            List<Meal> meal = patientService.generateDietByPatient(patientId, doctorId);
            if (meal == null){
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(1);
                responseDTO.setErrorMessage("No se pudo generar las comidas");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
            responseDTO.setData(meal);

            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

        }catch (Exception e){
            errorMessage = e.getMessage();
        }

        responseDTO.setHttpCode(HttpStatus.OK.value());
        responseDTO.setErrorCode(2);
        responseDTO.setErrorMessage("Ocurrio un error inesperado: " + errorMessage);
        responseDTO.setData(null);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/doctor")
    public ResponseEntity<ResponseDTO<Doctor>> getActualDoctorByPatient(@RequestParam Long patientId){
        ResponseDTO<Doctor> responseDTO = new ResponseDTO<>();
        String errorMessage = "";
        try {
            Doctor doctor = patientService.getDoctorByPatient(patientId);
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(doctor);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }catch (Exception e){
            errorMessage = e.getMessage();
        }

        responseDTO.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseDTO.setErrorCode(1);
        responseDTO.setErrorMessage(errorMessage);
        responseDTO.setData(null);

        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/validateAccessCode")
    public ResponseEntity<ResponseDTO<Boolean>> validateAccessCode(@RequestParam String accessCode){
        ResponseDTO<Boolean> responseDTO = new ResponseDTO<>();
        try {
            Boolean access = doctorService.verifyAccessCodeExist(accessCode);
            responseDTO.setErrorMessage("");
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setData(access);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e){

        }
        responseDTO.setErrorMessage("Ocurrio un al validar el código de accesso");
        responseDTO.setHttpCode(HttpStatus.OK.value());
        responseDTO.setErrorCode(1);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
