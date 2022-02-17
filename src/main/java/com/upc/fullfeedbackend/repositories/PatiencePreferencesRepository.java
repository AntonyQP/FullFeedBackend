package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.PatiencePreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatiencePreferencesRepository extends JpaRepository<PatiencePreferences, Long> {
}
