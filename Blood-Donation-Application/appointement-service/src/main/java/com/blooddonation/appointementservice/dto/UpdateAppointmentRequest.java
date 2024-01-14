package com.blooddonation.appointementservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
public class UpdateAppointmentRequest {
    Long id;
    LocalDateTime slot;
}
