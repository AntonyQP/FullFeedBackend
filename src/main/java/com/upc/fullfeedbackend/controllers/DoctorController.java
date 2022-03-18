package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Doctor;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.dto.PatientsOfDoctorDTO;
import com.upc.fullfeedbackend.models.dto.ResponseDTO;
import com.upc.fullfeedbackend.services.DoctorService;
import com.upc.fullfeedbackend.services.MealService;
import com.upc.fullfeedbackend.services.PatientService;
import com.upc.fullfeedbackend.services.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    MealService mealService;

    @GetMapping()
    public ResponseEntity<ResponseDTO<List<Doctor>>> getAllDoctors(){
        ResponseDTO<List<Doctor>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(null);
        try {
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(doctorService.getAllDoctors());
        }catch (Exception e){

        }

        responseDTO.setErrorCode(1);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/patients")
    public ResponseEntity<ResponseDTO<List<PatientsOfDoctorDTO>>> getPatientsByDoctor(@RequestParam Long doctorId){
        ResponseDTO<List<PatientsOfDoctorDTO>> responseDTO = new ResponseDTO<>();
        List<PatientsOfDoctorDTO> newPatients = new ArrayList<>();
        try {
            List<Patient> patients = doctorService.getPatientsByDoctor(doctorId);

            if (patients == null){
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(1);
                responseDTO.setErrorMessage("No se encuentran pacientes registrados con el doctor");
                responseDTO.setData(null);

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }

            for (Patient patient : patients){
                Integer firstDayWeek = mealService.getFirstDayOfWeekMeal(patient.getPatientId());
                newPatients.add(PatientsOfDoctorDTO.createFromPatient(patient, firstDayWeek));
            }

            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(newPatients);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }catch (Exception e){
            e.getMessage();
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping("/generateAccessCode")
    public ResponseEntity<ResponseDTO<String>> generateAccessCode(@RequestParam Long doctorId){
        ResponseDTO<String> responseDTO = new ResponseDTO<>();

        try {
            Doctor doctor = doctorService.getDoctorById(doctorId);
            String accessCode = UtilService.RandomString();
            doctor.setAccessCode(accessCode);
            doctorService.saveDoctor(doctor);

            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(accessCode);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }catch (Exception e){
            e.getMessage();
        }

        responseDTO.setHttpCode(HttpStatus.OK.value());
        responseDTO.setErrorCode(1);
        responseDTO.setErrorMessage("Ocurrio un problema al generar el código");
        responseDTO.setData(null);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
