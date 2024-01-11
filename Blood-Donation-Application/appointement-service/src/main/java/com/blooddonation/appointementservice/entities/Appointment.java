package com.blooddonation.appointementservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "center_id",referencedColumnName = "id")
    private Center center;
    @OneToOne
    @JoinColumn(name = "donor_id", referencedColumnName = "id")
    private Donor donor;

    private LocalDateTime slot;

}
