package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.PatienceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatienceLogRepository extends JpaRepository<PatienceLog, Long> {

}
