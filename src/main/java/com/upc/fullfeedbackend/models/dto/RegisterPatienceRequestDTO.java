package com.upc.fullfeedbackend.models.dto;

import com.upc.fullfeedbackend.models.Preferences;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterPatienceRequestDTO {

    //User Data
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phone;
    private String sex;
    private String dni;
    private Date registerDate;
    private String rol = "p";

    //Patience Data
    private float height;
    private float weight;
    private float imc;
    private float arm;
    private float abdominal;
    private float tmb;
}
