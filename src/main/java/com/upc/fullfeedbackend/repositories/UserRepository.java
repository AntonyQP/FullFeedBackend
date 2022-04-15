package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.dni = ?1")
    User findByDni(String dni);

    User findByEmail(String email);


    @Query("select p.user from Patient p where p.patientId = ?1")
    User findUserByPatientId(Long patientId);

}
