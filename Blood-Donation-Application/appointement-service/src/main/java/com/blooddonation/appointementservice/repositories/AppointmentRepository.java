package com.blooddonation.appointementservice.repositories;

import com.blooddonation.appointementservice.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.donor.id = :donorId ORDER BY a.slot DESC")
    Optional<Appointment> findLastAppointmentByDonorId(@Param("donorId") Long donorId);
}
