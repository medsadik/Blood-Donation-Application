package com.blooddonation.donationservice.repositories;

import com.blooddonation.donationservice.entities.Donation;
import com.blooddonation.donationservice.entities.DonationID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, DonationID> {
    @Query("SELECT d FROM Donation d WHERE d.donor_id = :donorId and d.bag_refenrence IS NOT NULL")
    public List<Donation> findByDonor_id(@Param("donorId") Long donorId);
}
