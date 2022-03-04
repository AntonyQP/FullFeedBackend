package com.upc.fullfeedbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "meal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long mealId;


    @JsonIgnore
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "nutritionalPlanId",nullable = false)
    private NutritionalPlan nutritionalPlan;

    private Date day;

    private String schedule;

    private String name;

    private double protein;

    private double fat;

    private double carbohydrates;

    private String ingredients;

    private double totalCalories;

    private double gramsPortion;

}
