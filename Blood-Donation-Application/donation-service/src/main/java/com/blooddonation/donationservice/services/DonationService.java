package com.blooddonation.donationservice.services;

import com.blooddonation.donationservice.entities.Donation;
import com.blooddonation.donationservice.entities.DonationID;

import java.util.List;

public interface DonationService {
    public Donation save(Long donor_id, Long center_id, Long bag_ref);
    public Donation validateDonation(Long donor_id, Long center_id, Long bag_ref);
    public List<Donation> getDonorHistory(Long donor_id);
    public List<Donation> getTodaysDonations();
}
