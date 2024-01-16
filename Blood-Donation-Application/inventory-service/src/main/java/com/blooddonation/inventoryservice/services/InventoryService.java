package com.blooddonation.inventoryservice.services;

import com.blooddonation.inventoryservice.entities.BloodBag;

import java.time.LocalDate;
import java.util.List;

public interface InventoryService {
    BloodBag save(String bloodType, LocalDate expiration);
    List<BloodBag> getCenterBags(Long center_id);
}
