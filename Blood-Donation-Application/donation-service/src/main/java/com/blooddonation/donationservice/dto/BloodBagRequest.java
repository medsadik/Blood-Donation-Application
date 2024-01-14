package com.blooddonation.donationservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Data
public class BloodBagRequest {
    private String type;
    private LocalDate expiration;
}
