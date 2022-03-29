package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Meal;
import com.upc.fullfeedbackend.models.NutritionalPlan;
import com.upc.fullfeedbackend.models.api.ApiAlternativesRequest;
import com.upc.fullfeedbackend.models.api.ApiRequest;
import com.upc.fullfeedbackend.models.api.Dish;
import com.upc.fullfeedbackend.models.dto.ReplaceMealRequestDTO;
import com.upc.fullfeedbackend.models.dto.ResponseDTO;
import com.upc.fullfeedbackend.services.MealService;
import com.upc.fullfeedbackend.services.NutritionalPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/day")
    private List<Meal> getMealsByDay(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam Long patientId) {
        return mealService.getMealsByDay(normalizeDate(date), patientId);
    }

    @GetMapping("/diet-meals")
    private List<Meal> getMealsBetweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                            @RequestParam Long patientId) {
        Date sd = normalizeDate(startDate);
        Date ed = normalizeDate(endDate);
        NutritionalPlan nutritionalPlan = nutritionalPlanService.getActiveNutritionalPlanByPatientId(patientId);

        return mealService.getMealsBetweenDatesAndNutritionalPlan(sd, ed, nutritionalPlan);
    }

    @PutMapping("/completeMeal")
    private ResponseEntity<ResponseDTO<Meal>> completeMeal(@RequestParam Long mealId) {
        ResponseDTO<Meal> responseDTO = new ResponseDTO<>();

        try {
            Meal meal = mealService.getMealByID(mealId);
            meal.setStatus((byte) 1);
            meal = mealService.saveMeal(meal);

            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(meal);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            e.getMessage();
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/failedMeal")
    private ResponseEntity<ResponseDTO<Meal>> failedMeal(@RequestParam Long mealId) {
        ResponseDTO<Meal> responseDTO = new ResponseDTO<>();

        try {
            Meal meal = mealService.getMealByID(mealId);
            meal.setStatus((byte) 2);
            meal = mealService.saveMeal(meal);

            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(meal);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            e.getMessage();
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/restoreMeal")
    private ResponseEntity<ResponseDTO<Meal>> restoreMeal(@RequestParam Long mealId) {
        ResponseDTO<Meal> responseDTO = new ResponseDTO<>();

        try {
            Meal meal = mealService.getMealByID(mealId);
            meal.setStatus((byte) 0);
            meal = mealService.saveMeal(meal);

            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(meal);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            e.getMessage();
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @PostMapping("/alternativeMeals")
    private ResponseEntity<ResponseDTO<List<Meal>>> getAlternativeMeals(@RequestBody ApiAlternativesRequest request) {
        ResponseDTO<List<Meal>> responseDTO = new ResponseDTO<>();

        try {
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(mealService.generateAlternativesMeal(request));

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            e.getMessage();
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/replaceMeal")
    private ResponseEntity<ResponseDTO<Meal>> replaceAlterantiveMeal(@RequestBody ReplaceMealRequestDTO requestMeal) {

        ResponseDTO<Meal> responseDTO = new ResponseDTO<>();

        try {

            Meal originalMeal = mealService.getMealByID(requestMeal.getMealId());

            originalMeal.setName(requestMeal.getName());
            originalMeal.setIngredients(requestMeal.getIngredients());
            originalMeal.setFat(requestMeal.getFat());
            originalMeal.setProtein(requestMeal.getProtein());
            originalMeal.setCarbohydrates(requestMeal.getCarbohydrates());
            originalMeal.setGramsPortion(requestMeal.getGramsPortion());
            originalMeal.setImageUrl(requestMeal.getImageUrl());
            originalMeal.setTotalCalories(requestMeal.getTotalCalories());

            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(mealService.saveMeal(originalMeal));

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            e.getMessage();
        }


        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    private Date normalizeDate(Date dt) {

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
}
