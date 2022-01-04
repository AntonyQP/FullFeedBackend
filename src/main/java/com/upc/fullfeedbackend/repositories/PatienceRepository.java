package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Patience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatienceRepository extends JpaRepository<Patience, Long> {


}
