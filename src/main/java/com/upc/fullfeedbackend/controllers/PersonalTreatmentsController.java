package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Doctor;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.PersonalTreatments;
import com.upc.fullfeedbackend.models.dto.PersonalTreatmentsRequestDTO;
import com.upc.fullfeedbackend.models.dto.ResponseDTO;
import com.upc.fullfeedbackend.services.DoctorService;
import com.upc.fullfeedbackend.services.PatientService;
import com.upc.fullfeedbackend.services.PersonalTreatmentsService;
import com.upc.fullfeedbackend.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/personalTreatments")
public class PersonalTreatmentsController {

    @Autowired
    PersonalTreatmentsService personalTreatmentsService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;


    @GetMapping("")
    public ResponseEntity<ResponseDTO<PersonalTreatments>> getActivePersonalTreatmentByPatient(@RequestParam Long patientId){
        ResponseDTO<PersonalTreatments> responseDTO = new ResponseDTO<>();
        PersonalTreatments personalTreatments = new PersonalTreatments();
        try {
              personalTreatments = personalTreatmentsService.getByPatientIdAndActive(patientId);

              if (personalTreatments == null){
                  responseDTO.setHttpCode(HttpStatus.OK.value());
                  responseDTO.setErrorCode(1);
                  responseDTO.setErrorMessage("El paciente no tiene un tratamiento personal activo");
                  responseDTO.setData(null);

                  return new ResponseEntity<>(responseDTO, HttpStatus.OK);

              }

              responseDTO.setHttpCode(HttpStatus.OK.value());
              responseDTO.setErrorCode(0);
              responseDTO.setErrorMessage("");
              responseDTO.setData(personalTreatments);

              return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }catch (Exception e){

        }

        responseDTO.setHttpCode(HttpStatus.OK.value());
        responseDTO.setErrorCode(2);
        responseDTO.setErrorMessage("Ocurrio un problema obteniendo los datos del servidor");
        responseDTO.setData(null);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<ResponseDTO<PersonalTreatments>> savePersonalTreatment(@RequestBody PersonalTreatmentsRequestDTO personalTreatmentsRequestDTO){

        ResponseDTO<PersonalTreatments> responseDTO = new ResponseDTO<>();

        Doctor doctor = new Doctor();
        Patient patient = new Patient();

        try {
            PersonalTreatments personalTreatments = new PersonalTreatments();

            doctor = doctorService.getDoctorById(personalTreatmentsRequestDTO.getDoctorId());
            if (doctor == null){
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(1);
                responseDTO.setErrorMessage("El doctor no existe");
                responseDTO.setData(null);

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }

            patient = patientService.getPatientById(personalTreatmentsRequestDTO.getPatientId());
            if (patient == null){
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(1);
                responseDTO.setErrorMessage("El paciente no existe");
                responseDTO.setData(null);

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }

            personalTreatments.setDoctor(doctor);
            personalTreatments.setPatient(patient);
            personalTreatments.setEndDate(null);
            personalTreatments.setActive((byte) 1);
            personalTreatments.setStartDate(UtilService.getNowDate());

            personalTreatments = personalTreatmentsService.savePersonalTreatments(personalTreatments);

            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(personalTreatments);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (Exception e){
            e.getMessage();
        }

        responseDTO.setHttpCode(HttpStatus.OK.value());
        responseDTO.setErrorCode(0);
        responseDTO.setErrorMessage("");
        responseDTO.setData(null);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
