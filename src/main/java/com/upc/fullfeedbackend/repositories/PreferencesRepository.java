package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferencesRepository extends JpaRepository<Preferences, Long> {

    Preferences findByName(String name);

}
