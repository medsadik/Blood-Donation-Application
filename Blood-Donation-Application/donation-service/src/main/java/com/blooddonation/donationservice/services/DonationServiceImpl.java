package com.blooddonation.donationservice.services;

import com.blooddonation.donationservice.dto.BloodBagRequest;
import com.blooddonation.donationservice.entities.Donation;
import com.blooddonation.donationservice.entities.DonationID;
import com.blooddonation.donationservice.repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DonationServiceImpl implements DonationService{
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private RestTemplate restTemplate;
    public Donation save(Long donor_id, Long center_id, Long bag_ref){
        Donation donation = new Donation();
        donation.setDonor_id(donor_id);
        donation.setCenter_id(center_id);
        donation.setBag_refenrence(bag_ref);
        donation.setDonationDate(LocalDate.now());
        return donationRepository.save(donation);
    }
    public Donation validateDonation(Long donor_id, Long center_id, LocalDate expiration){
        DonationID donationID = new DonationID();
        donationID.setCenter_id(center_id);
        donationID.setDonor_id(donor_id);
        Donation donation = donationRepository.findById(donationID).get();
        Long bag_ref = createBag(donor_id, expiration);
        donation.setBag_refenrence(bag_ref);
        return donationRepository.save(donation);
    }

    public List<Donation> getDonorHistory(Long donor_id){
        List<Donation> donations = donationRepository.findByDonor_id(donor_id);
        return donations;
    }

    public List<Donation> getTodaysDonations(){
        return donationRepository.findByDonationDate(LocalDate.now());
    }

    public boolean haveDonationInPast56(Long donor_id){
        if(donationRepository.findLastDonationByDonorId(donor_id).isPresent()){
            Donation donation = donationRepository.findLastDonationByDonorId(donor_id).get();
            long daysDifference = ChronoUnit.DAYS.between(donation.getDonationDate(), LocalDate.now());
            if(daysDifference > 56) return false;
            return true;
        }
        return false;
    }
    public String getDonorBloodType(Long donor_id){
        String serviceAUrl = "http://127.0.0.1:8080/api/donors/"+donor_id;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(serviceAUrl,String.class);
        return responseEntity.getBody();
    }
    public Long createBag(Long donor_id, LocalDate expiration){
        String bloodType = getDonorBloodType(donor_id);
        String serviceAUrl = "http://127.0.0.1:8082/api/inventory/createBag";
        BloodBagRequest bloodBagRequest = new BloodBagRequest();
        bloodBagRequest.setType(bloodType);
        bloodBagRequest.setExpiration(expiration);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<BloodBagRequest> requestEntity = new HttpEntity<>(bloodBagRequest, headers);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(
                serviceAUrl,
                HttpMethod.POST,
                requestEntity,
                Long.class
        );

        Long response = responseEntity.getBody();
        System.out.println("Donation service received response from Inventory Service: " + response);
        return response;
    }
}
