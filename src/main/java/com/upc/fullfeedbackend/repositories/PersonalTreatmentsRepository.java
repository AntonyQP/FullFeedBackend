package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.PersonalTreatments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalTreatmentsRepository extends JpaRepository<PersonalTreatments, Long> {
}
