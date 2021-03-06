package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Doctor;
import com.upc.fullfeedbackend.models.NutritionalPlan;
import com.upc.fullfeedbackend.models.PersonalTreatments;
import com.upc.fullfeedbackend.repositories.PersonalTreatmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Util;

@Service
public class PersonalTreatmentsService {

    @Autowired
    PersonalTreatmentsRepository personalTreatmentsRepository;


    public PersonalTreatments getById(Long personalTreatmentId){
        return personalTreatmentsRepository.findById(personalTreatmentId).get();
    }

    public PersonalTreatments getByPatientIdAndActive(Long patientId){
        return personalTreatmentsRepository.findByPatient_PatientIdAndActive(patientId, (byte) 1);
    }

    public PersonalTreatments savePersonalTreatments(PersonalTreatments personalTreatments){
        return personalTreatmentsRepository.save(personalTreatments);
    }

    public PersonalTreatments updatePersonaTreatments(PersonalTreatments personalTreatments){
        return personalTreatmentsRepository.save(personalTreatments);
    }

    public Doctor getDoctorByPatient(Long patientId){
        return personalTreatmentsRepository.getDoctorByPatient(patientId);
    }

    public PersonalTreatments createNewPersonalTreatment(PersonalTreatments personalTreatments){
        Long patientId = personalTreatments.getPatient().getPatientId();
        PersonalTreatments lastPersonalTreatment = getByPatientIdAndActive(patientId);
        if (lastPersonalTreatment != null){
            byte desactived = 0;
            lastPersonalTreatment.setActive(desactived);
            lastPersonalTreatment.setEndDate(UtilService.getNowDate());
            updatePersonaTreatments(lastPersonalTreatment);
        }
        return personalTreatmentsRepository.save(personalTreatments);
    }

    public Integer getActivePatientsByDoctor(Long doctorId){
        return personalTreatmentsRepository.countActivePatientsByDoctor(doctorId);
    }

}
