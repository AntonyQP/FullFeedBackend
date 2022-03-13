package com.upc.fullfeedbackend.models.dto;

import com.upc.fullfeedbackend.models.PersonalTreatments;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class NutritionalPlanRequestDTO {
    //private Long personalTreatmentsId;
    private Long patientId;
}
