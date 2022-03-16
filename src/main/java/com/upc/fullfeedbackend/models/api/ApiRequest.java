package com.upc.fullfeedbackend.models.api;

import lombok.Data;

import java.util.List;

@Data
public class ApiRequest {

    Integer calories;
    Integer weight;
    String region;
    List<String> allergies;
    List<String> favorites;
}
