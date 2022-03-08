package com.upc.fullfeedbackend.repositories;

import com.upc.fullfeedbackend.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
