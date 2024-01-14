package com.blooddonation.appointementservice.services;

import com.blooddonation.appointementservice.entities.Donor;
import com.blooddonation.appointementservice.repositories.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorsManagementServiceImpl implements DonorsManagementService{
    @Autowired
    private DonorRepository donorRepository;
    public String getDonorBloodType(Long donor_id){
        Donor donor =  donorRepository.findById(donor_id).get();
        return donor.getBloodType().name();
    }
}
