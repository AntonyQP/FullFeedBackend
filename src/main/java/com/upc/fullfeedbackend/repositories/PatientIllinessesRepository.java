package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.PatientIllnesses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientIllinessesRepository extends JpaRepository<PatientIllnesses, Long> {



}
