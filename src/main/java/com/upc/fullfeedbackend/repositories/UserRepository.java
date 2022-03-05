package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.dni = ?1")
    public User findByDni(String dni);

    public User findByEmail(String email);

}
