package com.upc.fullfeedbackend.models.dto;

import com.upc.fullfeedbackend.models.User;
import lombok.Data;

import javax.persistence.*;

@Data
public class PatientUpdateDTO {

    private Long patientId;
    private float height;
    private float weight;
    private float imc;
    private float arm;
    private float abdominal;
    private float tmb;
}
