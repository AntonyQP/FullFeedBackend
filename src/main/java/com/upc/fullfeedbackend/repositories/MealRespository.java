package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Meal;
import com.upc.fullfeedbackend.models.NutritionalPlan;
import com.upc.fullfeedbackend.models.dto.ConsumedBalanceMapSQL;
import com.upc.fullfeedbackend.models.dto.ConsumedBalanceResponseDTO;
import org.hibernate.mapping.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface MealRespository extends JpaRepository<Meal, Long> {



    @Query("select m from Meal m where m.day = ?1 and m.nutritionalPlan.personalTreatments.patient.patientId = ?2 and m.nutritionalPlan.isActive = 1")
    List<Meal> findByDayAndNutritionalPlan_PersonalTreatments_Patient_PatientIdAndNutritionalPlan_IsActive(LocalDate day, Long patientId);

    @Query("select m from Meal m where m.day >= ?1")
    List<Meal> findByDayIsGreaterThanEqual(Date day);

    @Query("select m from Meal m where m.day >= ?1 and m.day <= ?2 and m.nutritionalPlan = ?3")
    List<Meal> findByDayIsGreaterThanEqualAndDayIsLessThanEqualAndNutritionalPlan(LocalDate startDate, LocalDate endDay, NutritionalPlan nutritionalPlan);


    @Query("select count(m) from Meal m where m.status = ?1 and m.nutritionalPlan.personalTreatments.patient.patientId = ?2")
    Integer countByStatusAndNutritionalPlan_PersonalTreatments_Patient_PatientId(Byte status, Long patientId);

    @Query(value = "SELECT m.day as date, SUM(m.fat) as fat, SUM(m.carbohydrates) as carbohydrates, SUM(m.protein) as protein FROM meal m WHERE m.nutritional_plan_id = (SELECT np.nutritional_plan_id FROM personal_treatment pt JOIN nutritional_plan np on pt.personal_treatment_id = np.personal_treatments_id WHERE pt.patient_id = ?1 and np.is_active = 1 LIMIT 1) AND (m.day BETWEEN ?2 AND ?3) AND m.status = 1 GROUP BY m.day", nativeQuery = true)
    List<ConsumedBalanceMapSQL> getConsumeBalance(Long patientId, Date startDate, Date endDate);


    @Query(value = "SELECT m.day FROM meal m JOIN nutritional_plan np on m.nutritional_plan_id = np.nutritional_plan_id WHERE np.nutritional_plan_id = (SELECT np.nutritional_plan_id FROM personal_treatment pt JOIN nutritional_plan np on pt.personal_treatment_id = np.personal_treatments_id WHERE pt.patient_id = ?1 and np.is_active = 1 LIMIT 1) LIMIT 1", nativeQuery = true)
    Date getFirstDayWeekOfDiet(Long patientId);

}
