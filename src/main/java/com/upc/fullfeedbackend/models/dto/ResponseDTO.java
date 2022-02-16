package com.upc.fullfeedbackend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {

    private int httpCode;
    private int errorCode;
    private String errorMessage;
    private T data;
}
