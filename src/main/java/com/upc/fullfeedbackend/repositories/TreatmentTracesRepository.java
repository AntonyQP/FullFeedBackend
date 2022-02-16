package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.TreatmentTraces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentTracesRepository extends JpaRepository<TreatmentTraces, Long> {
}
