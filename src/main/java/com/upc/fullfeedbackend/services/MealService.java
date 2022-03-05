package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Meal;
import com.upc.fullfeedbackend.models.NutritionalPlan;
import com.upc.fullfeedbackend.models.api.ApiRequest;
import com.upc.fullfeedbackend.models.api.Dish;
import com.upc.fullfeedbackend.repositories.MealRespository;
import com.upc.fullfeedbackend.repositories.NutritionalPlanRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MealService {

    @Autowired
    MealRespository mealRespository;

    @Autowired
    NutritionalPlanService nutritionalPlanService;

    public List<Meal> getMeals(){
        return null;
    }


    public List<Meal> generateTwoWeeksMealsForPatient(Long nutritionalPlanId, Integer calories, Integer weight){

        String url = "https://fullfeedflask-app.herokuapp.com/diet-week";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setCalories(calories);
        apiRequest.setWeight(weight);

        HttpEntity<ApiRequest> httpEntity = new HttpEntity<>(apiRequest, headers);

        Dish[][] dishes = restTemplate.postForObject(url, httpEntity, Dish[][].class);

        List<Meal> meals = new ArrayList<>();

        //NutritionalPlan nutritionalPlan = nutritionalPlanService.getNutritionalPlanByPatientId(patientId);
        NutritionalPlan nutritionalPlan = nutritionalPlanService.getNutritionalPlanById(nutritionalPlanId);

        int indexDay = 1;
        for (Dish[] dishList: dishes) {
            for (Dish dish: dishList) {
                Meal meal = new Meal();
                meal.setSchedule(dish.getTipo());
                meal.setName(dish.getNombre());
                meal.setProtein(dish.getProteinas());
                meal.setFat(dish.getGrasas());
                meal.setCarbohydrates(dish.getCarbohidratos());
                meal.setNutritionalPlan(nutritionalPlan);

                String result = String.join("-", dish.getIngredientes());
                meal.setIngredients(result);

                meal.setTotalCalories(dish.getCalorias_totales());
                meal.setGramsPortion(dish.getPorcion_gramos());

                meal.setDay(getDate(indexDay));
                meals.add(meal);
            }
            indexDay++;
        }

        return mealRespository.saveAll(meals);
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

    public List<Meal> getMealsByDay(Date date){
        return mealRespository.findByDay(date);
    }


    public List<Meal> getMealsBetweenDatesAndNutritionalPlan(Date startDate, Date endDate, NutritionalPlan nutritionalPlanService){
        return mealRespository.findByDayIsGreaterThanEqualAndDayIsLessThanEqualAndNutritionalPlan(startDate, endDate, nutritionalPlanService);
    }

}
