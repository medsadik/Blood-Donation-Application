package com.blooddonation.appointementservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Center {
    @Id
    private Long id;

    private String nom;
    private String localisation;
    private Time h_ouverture;
    private Time h_fermeture;
    private int capacity;
}
