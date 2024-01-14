package com.blooddonation.donationservice.services;

import com.blooddonation.donationservice.entities.Donation;
import com.blooddonation.donationservice.entities.DonationID;
import com.blooddonation.donationservice.repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonationServiceImpl implements DonationService{
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private WebClient webClient;
    public Donation save(Long donor_id, Long center_id, Long bag_ref){
        Donation donation = new Donation();
        donation.setDonor_id(donor_id);
        donation.setCenter_id(center_id);
        donation.setBag_refenrence(bag_ref);
        donation.setDonation_date(LocalDate.now());
        return donationRepository.save(donation);
    }
    public Donation validateDonation(Long donor_id, Long center_id, Long bag_ref){
        DonationID donationID = new DonationID();
        donationID.setCenter_id(center_id);
        donationID.setDonor_id(donor_id);
        Donation donation = donationRepository.findById(donationID).get();
        donation.setBag_refenrence(bag_ref);
        return donationRepository.save(donation);
    }

    public List<Donation> getDonorHistory(Long donor_id){
        List<Donation> donations = donationRepository.findByDonor_id(donor_id);
        return donations;
    }
}
