package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Illnesses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IllnessesRepository extends JpaRepository<Illnesses, Long> {



}
