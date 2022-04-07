package com.upc.fullfeedbackend.controllers;


import com.upc.fullfeedbackend.models.Illnesses;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.PatientIllnesses;
import com.upc.fullfeedbackend.models.dto.ResponseDTO;
import com.upc.fullfeedbackend.services.IllnessService;
import com.upc.fullfeedbackend.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/illness")
public class IllnessController {

    @Autowired
    IllnessService illnessService;


    @Autowired
    PatientService patientService;

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<Illnesses>>> getAllIllnesses(){

        ResponseDTO<List<Illnesses>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(null);

        try {
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorMessage("");
            responseDTO.setErrorCode(0);
            responseDTO.setData(illnessService.getAllIllnesses());

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e){
            responseDTO.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setErrorCode(1);

            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO<List<Illnesses>>> getIllnessByPatient(@RequestParam Long patientId){

        ResponseDTO<List<Illnesses>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(null);

        try {
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorMessage("");
            responseDTO.setErrorCode(0);
            responseDTO.setData(illnessService.getIllnessesByPatient(patientId));
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e){
            responseDTO.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setErrorCode(1);

            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("")
    public ResponseEntity<ResponseDTO<List<PatientIllnesses>>> saveIllnessesToPatient(@RequestParam Long patientId, @RequestBody List<Long> illnessesIds){

        ResponseDTO<List<PatientIllnesses>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(null);

        try {
            Patient patient = patientService.getPatientById(patientId);
            if (patient == null){
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorMessage("El paciente no existe.");
                responseDTO.setErrorCode(1);
                responseDTO.setData(null);

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorMessage("");
            responseDTO.setErrorCode(0);
            responseDTO.setData(illnessService.savePatientIllnesses(patient, illnessesIds));
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }catch (Exception e){
            responseDTO.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setErrorCode(2);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("")
    public ResponseEntity<ResponseDTO<String>> removeIllnessesToPatient(@RequestParam Long patientId, @RequestBody List<Long> illnessesIds){

        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        responseDTO.setData(null);

        try {
            Patient patient = patientService.getPatientById(patientId);
            if (patient == null){
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorMessage("El paciente no existe.");
                responseDTO.setErrorCode(1);
                responseDTO.setData(null);

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
            if(illnessService.removePatientIllnesses(patient, illnessesIds)==null)
                throw new Exception("El eliminado fallo");
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorMessage("");
            responseDTO.setErrorCode(0);
            responseDTO.setData("Enferemedades retiradas exitosamente.");
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }catch (Exception e){
            responseDTO.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setErrorMessage(e.getMessage());
            responseDTO.setErrorCode(2);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
