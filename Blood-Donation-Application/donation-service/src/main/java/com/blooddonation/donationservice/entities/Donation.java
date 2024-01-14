package com.blooddonation.donationservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(DonationID.class)
public class Donation {
    @Id
    private Long center_id;
    @Id
    private Long donor_id;
    private LocalDate donationDate;
    private Long bag_refenrence;
}
