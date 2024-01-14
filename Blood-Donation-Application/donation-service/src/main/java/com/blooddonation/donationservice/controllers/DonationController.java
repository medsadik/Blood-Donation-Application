package com.blooddonation.donationservice.controllers;

import com.blooddonation.donationservice.dto.DonationRequest;
import com.blooddonation.donationservice.entities.Donation;
import com.blooddonation.donationservice.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/donations")
@CrossOrigin(origins ="*")
public class DonationController {
    @Autowired
    private DonationService donationService;
    @PostMapping("/saveDonation")
    public String saveDonation(@RequestBody DonationRequest donationRequest){
        donationService.save(donationRequest.getDonor_id(),donationRequest.getCenter_id(),donationRequest.getBag_ref());
        return "Donation sevaed";
    }
    @PutMapping("/validatedDonation")
    public String validateDonation(@RequestBody DonationRequest donationRequest){
        donationService.validateDonation(donationRequest.getDonor_id(),donationRequest.getCenter_id(),donationRequest.getBag_ref());
        return "Donation validated";
    }
    @GetMapping("/userHistory/{donor_id}")
    public List<Donation> getUserHistory(@PathVariable Long donor_id){
        return donationService.getDonorHistory(donor_id);
    }

    @GetMapping("/getTodaysDonation")
    public List<Donation> getTodaysDonations(){
        return donationService.getTodaysDonations();
    }
    @GetMapping("/donorValid/{donorId}")
    public boolean isDonorValid(@PathVariable Long donorId){
        return !donationService.haveDonationInPast56(donorId);
    }
}
