package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Meal;
import com.upc.fullfeedbackend.models.NutritionalPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MealRespository extends JpaRepository<Meal, Long> {



    @Query("select m from Meal m where m.day = ?1")
    public List<Meal> findByDay(Date day);


    @Query("select m from Meal m where m.day >= ?1")
    public List<Meal> findByDayIsGreaterThanEqual(Date day);


    @Query("select m from Meal m where m.day >= ?1 and m.day <= ?2 and m.nutritionalPlan = ?3")
    public List<Meal> findByDayIsGreaterThanEqualAndDayIsLessThanEqualAndNutritionalPlan(Date startDate, Date endDay, NutritionalPlan nutritionalPlan);

}
