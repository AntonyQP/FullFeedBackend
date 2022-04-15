package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Doctor;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.PersonalTreatments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface PersonalTreatmentsRepository extends JpaRepository<PersonalTreatments, Long> {


    @Query("select p from PersonalTreatments p where p.patient.patientId = ?1 and p.active = ?2")
    PersonalTreatments findByPatient_PatientIdAndActive(Long patientId, Byte active);

    @Query("select p.patient from PersonalTreatments p where p.doctor.doctorId = ?1 and p.active = 1")
    List<Patient> findPatientsByDoctor(Long doctorId);

    @Query("select p.doctor from PersonalTreatments p where p.patient.patientId = ?1 and p.active = 1")
    Doctor getDoctorByPatient(Long patientId);

    @Query("select count(p) from PersonalTreatments p where p.active = 1 and p.doctor.doctorId = ?1")
    Integer countActivePatientsByDoctor(Long doctorId);


    @Query(value = "SELECT SUM(m.status) 'result', pt.patient_id from personal_treatment pt JOIN doctor d ON pt.doctor_id = d.doctor_id  JOIN nutritional_plan np ON pt.personal_treatment_id = np.personal_treatments_id JOIN meal m  ON np.nutritional_plan_id = m.nutritional_plan_id WHERE d.doctor_id = ?1 and pt.active = 1 and np.is_active = 1 and m.day = ?2 and m.schedule = ?3 GROUP BY pt.patient_id;", nativeQuery = true)
    List<Map<Object, Object>> findPatientsMarkMealsByDoctor(Long doctorId, LocalDate date);

    @Query(value = "SELECT m.meal_id,m.status,m.schedule, pt.patient_id from personal_treatment pt JOIN doctor d ON pt.doctor_id = d.doctor_id  JOIN nutritional_plan np ON pt.personal_treatment_id = np.personal_treatments_id JOIN meal m  ON np.nutritional_plan_id = m.nutritional_plan_id WHERE d.doctor_id = :doctorId and pt.active = 1 and np.is_active = 1 and m.day = :date and m.schedule = :schedule", nativeQuery = true)
    List<Map<Object, Object>> findPatientsMarkMealScheduleByDoctor(Long doctorId, LocalDate date, String schedule);

}
