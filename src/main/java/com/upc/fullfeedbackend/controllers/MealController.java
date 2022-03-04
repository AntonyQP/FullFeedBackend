package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Meal;
import com.upc.fullfeedbackend.models.api.ApiRequest;
import com.upc.fullfeedbackend.models.api.Dish;
import com.upc.fullfeedbackend.services.MealService;
import com.upc.fullfeedbackend.services.NutritionalPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/save")
    private List<Meal> saveMeals(){
        String url = "https://fullfeedflask-app.herokuapp.com/diet-week";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setCalories(2000);
        apiRequest.setWeight(100);

        HttpEntity<ApiRequest> httpEntity = new HttpEntity<>(apiRequest, headers);

        Dish[][] dishes = restTemplate.postForObject(url, httpEntity, Dish[][].class);

        List<Meal> meals = new ArrayList<>();


        int indexDay = 1;
        for (Dish[] dishList: dishes) {
            for (Dish dish: dishList) {
                Meal meal = new Meal();
                meal.setSchedule(dish.getTipo());
                meal.setName(dish.getNombre());
                meal.setProtein(dish.getProteinas());
                meal.setFat(dish.getGrasas());
                meal.setCarbohydrates(dish.getCarbohidratos());
                meal.setNutritionalPlan(nutritionalPlanService.getNutritionalPlanById(4L));

                String result = String.join("-", dish.getIngredientes());
                meal.setIngredients(result);

                meal.setTotalCalories(dish.getCalorias_totales());
                meal.setGramsPortion(dish.getPorcion_gramos());

                meal.setDay(getDate(indexDay));
                meals.add(meal);


            }
            indexDay++;
        }
        return mealService.saveMeals(meals);
    }

    @GetMapping("/day")
    private List<Meal> getMealsByDay(){
        return mealService.getMealsByDay(getDate(1));
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
