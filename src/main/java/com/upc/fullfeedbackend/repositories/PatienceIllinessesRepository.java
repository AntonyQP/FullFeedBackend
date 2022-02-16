package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.PatienceIllnesses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatienceIllinessesRepository extends JpaRepository<PatienceIllnesses, Long> {



}
