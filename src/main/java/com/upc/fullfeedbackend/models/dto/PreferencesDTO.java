package com.upc.fullfeedbackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferencesDTO {

    private Long preferenceId;

    @Column(length = 25)
    private String type;
}
