package com.upc.fullfeedbackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mealSchedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealSchedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long mealScheduleId;


    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "mealId",nullable = false)
    private Meal meal;


    private int schedule;
}
