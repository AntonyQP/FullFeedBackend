package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Doctor;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.repositories.DoctorRepository;
import com.upc.fullfeedbackend.repositories.PersonalTreatmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PersonalTreatmentsRepository personalTreatmentsRepository;

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long doctorId){
        return doctorRepository.findById(doctorId).get();
    }

    public Doctor getDoctorByUserId(Long userId){
        return doctorRepository.findByUser_UserId(userId);
    }

    public List<Patient> getPatientsByDoctor(Long doctorId){
        return personalTreatmentsRepository.findPatientsByDoctor(doctorId);
    }

}
