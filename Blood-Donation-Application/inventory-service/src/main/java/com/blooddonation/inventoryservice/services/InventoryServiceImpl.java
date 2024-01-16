package com.blooddonation.inventoryservice.services;

import com.blooddonation.inventoryservice.entities.BloodBag;
import com.blooddonation.inventoryservice.entities.BloodType;
import com.blooddonation.inventoryservice.repositories.BloodBagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private BloodBagRepository bloodBagRepository;
    @Override
    public BloodBag save(String bloodType, LocalDate expiration) {
        BloodBag bloodBag = new BloodBag();
        bloodBag.setType(BloodType.valueOf(bloodType));
        bloodBag.setExpiration(expiration);
        return bloodBagRepository.save(bloodBag);
    }

    @Override
    public List<BloodBag> getCenterBags(Long center_id){
        return null;
    }
}
