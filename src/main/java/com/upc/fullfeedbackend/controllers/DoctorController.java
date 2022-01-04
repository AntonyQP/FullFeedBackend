package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Doctor;
import com.upc.fullfeedbackend.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping()
    public List<Doctor> getAllDoctors(){

        return doctorService.getAllDoctors();
    }


}
