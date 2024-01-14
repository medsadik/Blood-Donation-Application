package com.blooddonation.appointementservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DonationRequest {
    private Long donor_id;
    private Long center_id;
    private Long bag_ref;
}
