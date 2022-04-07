package com.upc.fullfeedbackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class WeightEvolutionResponseDTO {

    private LocalDate date;
    private double weight;

}
