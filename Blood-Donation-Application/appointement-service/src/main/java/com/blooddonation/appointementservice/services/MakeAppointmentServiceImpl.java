package com.blooddonation.appointementservice.services;

import com.blooddonation.appointementservice.dto.DonationRequest;
import com.blooddonation.appointementservice.entities.Appointment;
import com.blooddonation.appointementservice.entities.Center;
import com.blooddonation.appointementservice.entities.Donor;
import com.blooddonation.appointementservice.repositories.AppointmentRepository;
import com.blooddonation.appointementservice.repositories.CenterRepository;
import com.blooddonation.appointementservice.repositories.DonorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MakeAppointmentServiceImpl implements MakeAppointmentService{
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CenterRepository centerRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DonorRepository donorRepository;
    public Appointment makeAppointment(LocalDateTime slot, Long donor_id, Long center_id){
        Appointment appointment = new Appointment();
        Donor donor = donorRepository.findById(donor_id).get();
        Center center = centerRepository.findById(center_id).get();
        appointment.setDonor(donor);
        appointment.setCenter(center);
        appointment.setSlot(slot);
        saveDonationFromAppointment(donor_id, center_id);
        return appointmentRepository.save(appointment);
    }

    public boolean canMakeAppointment(Long donor_id){
        String serviceAUrl = "http://127.0.0.1:8081/api/donations/donorValid/"+donor_id;
        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity(serviceAUrl,Boolean.class);
        boolean response = responseEntity.getBody();
        return response;
    }

    public boolean checkSlotAvailable(Long center_id, LocalDateTime slot){
        if(appointmentRepository.countAppointementsBySlot(center_id, slot) > 10L) return false;
        return true;
    }

    public void saveDonationFromAppointment(Long donor_id, Long center_id) {
        String serviceAUrl = "http://127.0.0.1:8081/api/donations/saveDonation";

        // Create a sample DonationRequest
        DonationRequest donationRequest = new DonationRequest();
        donationRequest.setDonor_id(donor_id);
        donationRequest.setCenter_id(center_id);
        donationRequest.setBag_ref(null);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DonationRequest> requestEntity = new HttpEntity<>(donationRequest, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                serviceAUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        String response = responseEntity.getBody();
        System.out.println("Service B received response from Service A: " + response);
    }

    public Appointment updateAppointment(LocalDateTime slot, Long id){
        Appointment appointment = appointmentRepository.findById(id).get();
        appointment.setSlot(slot);
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id){
        Appointment appointment = appointmentRepository.findById(id).get();
        appointmentRepository.delete(appointment);
    }

    public List<Appointment> getDonorAppointments(Long donor_id){
        return appointmentRepository.findAppointmentByDonor(donor_id);
    }

    public List<Appointment> getCenterAppointments(Long center_id){
        return appointmentRepository.findAppointmentByCenter(center_id);
    }
}
