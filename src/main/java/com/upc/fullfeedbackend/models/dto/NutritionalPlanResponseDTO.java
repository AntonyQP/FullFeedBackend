package com.upc.fullfeedbackend.models.dto;

import lombok.Data;

@Data
public class NutritionalPlanResponseDTO {

    private Long nutritionalPlanId;
    private Long personalTreatmentId;
    private Byte isActive;

    private double caloriesPlan;
    private double weightPatient;

}
