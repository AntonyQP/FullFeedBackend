package com.upc.fullfeedbackend.models.dto;

import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.Region;
import com.upc.fullfeedbackend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientsOfDoctorDTO {

    private Long patientId;
    private float height;
    private float weight;
    private float imc;
    private float arm;
    private float abdominal;
    private float tmb;
    private int age;
    private Region region;
    private User user;
    private Integer firstDayOfWeek;

    public static PatientsOfDoctorDTO createFromPatient(Patient patient, Integer firstDayOfWeek){
        PatientsOfDoctorDTO newPatient = new PatientsOfDoctorDTO();

        newPatient.setPatientId(patient.getPatientId());
        newPatient.setHeight(patient.getHeight());
        newPatient.setWeight(patient.getWeight());
        newPatient.setImc(patient.getImc());
        newPatient.setArm(patient.getArm());
        newPatient.setAbdominal(patient.getAbdominal());
        newPatient.setTmb(patient.getTmb());
        newPatient.setAge(patient.getAge());
        newPatient.setRegion(patient.getRegion());
        newPatient.setUser(patient.getUser());
        newPatient.setFirstDayOfWeek(firstDayOfWeek);
        return newPatient;
    }

}
