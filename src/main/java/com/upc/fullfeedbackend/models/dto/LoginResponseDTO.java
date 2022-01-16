package com.upc.fullfeedbackend.models.dto;

import com.upc.fullfeedbackend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    private int httpCode;
    private int errorCode;
    private String errorMessage;
    private User data;

}
