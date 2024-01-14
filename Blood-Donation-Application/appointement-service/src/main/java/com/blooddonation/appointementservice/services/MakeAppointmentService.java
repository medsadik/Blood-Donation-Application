package com.blooddonation.appointementservice.services;

import com.blooddonation.appointementservice.entities.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface MakeAppointmentService {
    Appointment makeAppointment(LocalDateTime slot,Long donor_id, Long center_id);
    boolean canMakeAppointment(Long donor_id);
    boolean checkSlotAvailable(Long center_id, LocalDateTime slot);
    Appointment updateAppointment(LocalDateTime slot, Long id);
    void deleteAppointment(Long id);
    List<Appointment> getDonorAppointments(Long donor_id);
    List<Appointment> getCenterAppointments(Long center_id);
}
