package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.MealSchedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealSchedulesRespository extends JpaRepository<MealSchedules, Long> {

}
