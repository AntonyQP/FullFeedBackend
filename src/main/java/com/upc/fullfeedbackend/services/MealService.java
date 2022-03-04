package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Meal;
import com.upc.fullfeedbackend.repositories.MealRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MealService {

    @Autowired
    MealRespository mealRespository;


    public List<Meal> getMeals(){
        return null;
    }


    public List<Meal> saveMeals(List<Meal> mealList){
        return mealRespository.saveAll(mealList);
    }


    public List<Meal> getMealsByDay(Date date){
        return mealRespository.findByDay(date);
    }

}
