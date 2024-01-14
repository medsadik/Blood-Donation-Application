package com.blooddonation.inventoryservice.services;

import com.blooddonation.inventoryservice.entities.BloodBag;

import java.time.LocalDate;

public interface InventoryService {
    BloodBag save(String bloodType, LocalDate expiration);
}
