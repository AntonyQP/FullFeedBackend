package com.upc.fullfeedbackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferencesDTO {

    @Column(length = 100)
    private String nombre;

    @Column(length = 25)
    private String tipo;
}
