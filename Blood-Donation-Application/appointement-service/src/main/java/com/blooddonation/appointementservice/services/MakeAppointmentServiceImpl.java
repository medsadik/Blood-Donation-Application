package com.blooddonation.appointementservice.services;

import com.blooddonation.appointementservice.entities.Appointment;
import com.blooddonation.appointementservice.entities.Center;
import com.blooddonation.appointementservice.entities.Donor;
import com.blooddonation.appointementservice.repositories.AppointmentRepository;
import com.blooddonation.appointementservice.repositories.CenterRepository;
import com.blooddonation.appointementservice.repositories.DonorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class MakeAppointmentServiceImpl implements MakeAppointmentService{
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private DonorRepository donorRepository;
    public Appointment makeAppointment(LocalDateTime slot, Long donor_id, Long center_id){
        Appointment appointment = new Appointment();
        Donor donor = donorRepository.findById(donor_id).get();
        Center center = centerRepository.findById(center_id).get();
        appointment.setDonor(donor);
        appointment.setCenter(center);
        appointment.setSlot(slot);
        return appointmentRepository.save(appointment);
    }

    public boolean checkAppointment(Long donor_id){
        if(appointmentRepository.findLastAppointmentByDonorId(donor_id).isPresent()){
            Appointment appointment = appointmentRepository.findLastAppointmentByDonorId(donor_id).get();
            long daysDifference = ChronoUnit.DAYS.between(appointment.getSlot(), LocalDateTime.now());
            if(daysDifference > 56) return true;
            return false;
        }
        return true;
    }

    public boolean checkSlotAvailable(Long center_id, LocalDateTime slot){
        if(appointmentRepository.countAppointementsBySlot(center_id, slot) > 10L) return false;
        return true;
    }
}
