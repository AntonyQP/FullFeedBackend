package com.upc.fullfeedbackend.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upc.fullfeedbackend.models.Region;
import com.upc.fullfeedbackend.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO<T> {

    private T profile;

}
