package com.upc.fullfeedbackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nutritionalPlan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionalPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long nutritionalPlanId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "personalTreatmentsId",nullable = false)
    private PersonalTreatments personalTreatments;

    private double caloriesPlan;

    private double weightPatient;

    private Byte isActive;

}
