package com.blooddonation.inventoryservice.controllers;

import com.blooddonation.inventoryservice.dto.BloodBagRequest;
import com.blooddonation.inventoryservice.entities.BloodBag;
import com.blooddonation.inventoryservice.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @PostMapping("/createBag")
    public ResponseEntity<Long> createBag(@RequestBody BloodBagRequest bloodBagRequest){
        BloodBag bag = inventoryService.save(bloodBagRequest.getType(), bloodBagRequest.getExpiration());
        return new ResponseEntity<>(bag.getId(), HttpStatusCode.valueOf(200));

    }
}
