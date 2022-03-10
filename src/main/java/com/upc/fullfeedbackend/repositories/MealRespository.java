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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface MealRespository extends JpaRepository<Meal, Long> {



    @Query("select m from Meal m where m.day = ?1 and m.nutritionalPlan.personalTreatments.patient.patientId = ?2 and m.nutritionalPlan.isActive = ?3")
    public List<Meal> findByDayAndNutritionalPlan_PersonalTreatments_Patient_PatientIdAndNutritionalPlan_IsActive(Date day, Long patientId, Byte isActive);


    @Query("select m from Meal m where m.day >= ?1")
    public List<Meal> findByDayIsGreaterThanEqual(Date day);


    @Query("select m from Meal m where m.day >= ?1 and m.day <= ?2 and m.nutritionalPlan = ?3")
    public List<Meal> findByDayIsGreaterThanEqualAndDayIsLessThanEqualAndNutritionalPlan(Date startDate, Date endDay, NutritionalPlan nutritionalPlan);


    @Query("select count(m) from Meal m where m.status = ?1 and m.nutritionalPlan.personalTreatments.patient.patientId = ?2")
    public Integer countByStatusAndNutritionalPlan_PersonalTreatments_Patient_PatientId(Byte status, Long patientId);

    @Query(value = "SELECT m.day as date, SUM(m.fat) as fat, SUM(m.carbohydrates) as carbohydrates, SUM(m.protein) as protein FROM meal m WHERE m.nutritional_plan_id = (SELECT np.nutritional_plan_id FROM personal_treatment pt JOIN nutritional_plan np on pt.personal_treatment_id = np.personal_treatments_id WHERE pt.patient_id = ?1 and np.is_active = 1 LIMIT 1) AND (m.day BETWEEN ?2 AND ?3) AND m.status = 1 GROUP BY m.day", nativeQuery = true)
    public List<ConsumedBalanceMapSQL> getConsumeBalance(Long patientId, Date startDate, Date endDate);

}
