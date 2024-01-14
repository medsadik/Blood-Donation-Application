package com.blooddonation.appointementservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AppointmentRequest {
    private Long donor_id;
    private Long center_id;
    private LocalDateTime slot;
}
