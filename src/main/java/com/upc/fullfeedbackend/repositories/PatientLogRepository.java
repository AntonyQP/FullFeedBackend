package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.PatientLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientLogRepository extends JpaRepository<PatientLog, Long> {

}
