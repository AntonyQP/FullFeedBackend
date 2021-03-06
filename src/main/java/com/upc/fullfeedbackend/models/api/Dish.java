package com.upc.fullfeedbackend.models.api;

import lombok.Data;

import java.util.List;

@Data
public class Dish {

    private String nombre;
    private double proteinas;
    private double grasas;
    private double carbohidratos;
    private List<String> ingredientes;
    private double calorias_totales;
    private String horario;
    private List<String> region;
    private double porcion_gramos;
    private String url_image;

}
