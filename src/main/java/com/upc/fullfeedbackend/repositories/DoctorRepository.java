package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("select d from Doctor d where d.user.userId = ?1")
    public Doctor findByUser_UserId(Long userId);

    @Query("select d from Doctor d where d.activePatients <= 100 order by d.activePatients asc")
    public List<Doctor> findDoctorsOrderByActivePatients();

    @Query("select d from Doctor d where d.accessCode = ?1")
    public List<Doctor> findByAccessCode(String accessCode);

}
