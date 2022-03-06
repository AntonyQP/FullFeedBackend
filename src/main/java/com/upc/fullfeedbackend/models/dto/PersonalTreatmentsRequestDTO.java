package com.upc.fullfeedbackend.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PersonalTreatmentsRequestDTO {

    private Long patientId;
    private Long doctorId;
}
