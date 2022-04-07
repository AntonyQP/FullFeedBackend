package com.upc.fullfeedbackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterPatientRequestDTO {

    //User Data
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phone;
    private String sex;
    private String dni;
    private String rol = "p";

    //Patient Data
    private float height;
    private float weight;
    private float imc;
    private float arm;
    private float abdominal;
    private float tmb;
    private Long regionId;

}
