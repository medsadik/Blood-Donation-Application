package com.blooddonation.appointementservice.controllers;

import com.blooddonation.appointementservice.entities.Donor;
import com.blooddonation.appointementservice.services.DonorsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/donors")
public class DonorController {
    @Autowired
    private DonorsManagementService donorsManagementService;
    @GetMapping("/{id}")
    ResponseEntity<String> getDonor(@PathVariable Long id){
        return new ResponseEntity<>(donorsManagementService.getDonorBloodType(id), HttpStatusCode.valueOf(200));
    }
}
