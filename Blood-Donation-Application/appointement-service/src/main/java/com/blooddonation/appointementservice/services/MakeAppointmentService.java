package com.blooddonation.appointementservice.services;

import com.blooddonation.appointementservice.entities.Appointment;

import java.sql.Time;
import java.time.LocalDateTime;

public interface MakeAppointmentService {
    Appointment makeAppointment(LocalDateTime slot,Long donor_id, Long center_id);
    boolean checkAppointment(Long donor_id);
    boolean checkSlotAvailable(Long center_id, LocalDateTime slot);
}
