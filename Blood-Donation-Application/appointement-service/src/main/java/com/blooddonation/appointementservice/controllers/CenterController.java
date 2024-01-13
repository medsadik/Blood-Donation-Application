package com.blooddonation.appointementservice.controllers;

import com.blooddonation.appointementservice.entities.Center;
import com.blooddonation.appointementservice.repositories.CenterRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
public class CenterController {
    private CenterRepository centerRepository;
    @GetMapping("/all")
    public List<Center> getAllCenters(){
        return centerRepository.findAll();
    }
}
