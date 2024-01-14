package com.blooddonation.donationservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
@RequiredArgsConstructor
@Data
public class DonationID implements Serializable {
    private Long center_id;
    private Long donor_id;

}
