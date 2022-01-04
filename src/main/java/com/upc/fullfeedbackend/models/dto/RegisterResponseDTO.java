package com.upc.fullfeedbackend.models.dto;


import com.upc.fullfeedbackend.models.Patience;
import com.upc.fullfeedbackend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterResponseDTO {

    private String errorCode;

    private String errorMessage;

    private String httpCode;

    private User data;
}
