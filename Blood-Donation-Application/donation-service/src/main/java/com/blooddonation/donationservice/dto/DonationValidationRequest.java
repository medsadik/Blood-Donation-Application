package com.blooddonation.donationservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Data
public class DonationValidationRequest {
    private Long donor_id;
    private Long center_id;
    private LocalDate expiration;
}
