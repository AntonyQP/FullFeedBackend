package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Meal;
import com.upc.fullfeedbackend.models.NutritionalPlan;
import com.upc.fullfeedbackend.models.api.ApiRequest;
import com.upc.fullfeedbackend.models.api.Dish;
import com.upc.fullfeedbackend.services.MealService;
import com.upc.fullfeedbackend.services.NutritionalPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/meal")
public class MealController {


    @Autowired
    MealService mealService;

    @Autowired
    NutritionalPlanService nutritionalPlanService;

//    @PostMapping("/generate-two-weeks-meals")
//    private List<Meal> saveMeals(@RequestParam Long patientId,
//                                 @RequestParam Integer calories,
//                                 @RequestParam Integer weight){
//
//        return mealService.generateTwoWeeksMealsForPatient(patientId, calories, weight);
//    }


    @GetMapping("/day")
    private List<Meal> getMealsByDay(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return mealService.getMealsByDay(normalizeDate(date));
    }

    @GetMapping("/week-meals")
    private List<Meal> getMealsInTheWeek(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                         @RequestParam Long patientId){
        Date sd = normalizeDate(startDate);
        Date ed = normalizeDate(endDate);
        NutritionalPlan nutritionalPlan = nutritionalPlanService.getActiveNutritionalPlanByPatientId(patientId);

        return  mealService.getMealsBetweenDatesAndNutritionalPlan(sd, ed, nutritionalPlan);
    }


    private Date normalizeDate(Date dt){
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        dt = c.getTime();
        return dt;
    }

    private Date getDate(int diaMas){
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, diaMas);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        dt = c.getTime();
        return dt;
    }

}
