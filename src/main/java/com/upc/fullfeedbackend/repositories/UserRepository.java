package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByDni(String dni);

    public User findByEmail(String email);

}
