package com.blooddonation.appointementservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Donor {
    @Id
    private Long id;
    private String nom;
    private String prenom;
    private int age;
    private String email;
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;
}
