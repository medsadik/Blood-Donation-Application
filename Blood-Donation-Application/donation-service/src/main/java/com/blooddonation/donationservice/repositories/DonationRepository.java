package com.blooddonation.donationservice.repositories;

import com.blooddonation.donationservice.entities.Donation;
import com.blooddonation.donationservice.entities.DonationID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DonationRepository extends JpaRepository<Donation, DonationID> {
    @Query("SELECT d FROM Donation d WHERE d.donor_id = :donorId and d.bag_refenrence IS NOT NULL")
    public List<Donation> findByDonor_id(@Param("donorId") Long donorId);
    public List<Donation> findByDonationDate(LocalDate donationDate);
    @Query("SELECT d FROM Donation d WHERE d.donor_id = :donorId ORDER BY d.donationDate DESC")
    Optional<Donation> findLastDonationByDonorId(@Param("donorId") Long donorId);
}
