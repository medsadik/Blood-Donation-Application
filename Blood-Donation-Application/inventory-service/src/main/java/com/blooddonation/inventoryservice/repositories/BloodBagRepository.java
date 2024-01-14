package com.blooddonation.inventoryservice.repositories;

import com.blooddonation.inventoryservice.entities.BloodBag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodBagRepository extends JpaRepository<BloodBag, Long> {
}
