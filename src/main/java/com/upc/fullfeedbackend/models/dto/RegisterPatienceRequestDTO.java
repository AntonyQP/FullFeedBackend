package com.upc.fullfeedbackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterPatienceRequestDTO {
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
}
