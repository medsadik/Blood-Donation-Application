package com.blooddonation.appointementservice.controllers;

import com.blooddonation.appointementservice.dto.AppointmentRequest;
import com.blooddonation.appointementservice.services.MakeAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    @Autowired
    private final MakeAppointmentService makeAppointmentService;

    @PostMapping("/make")
    public String makeAppointment(@RequestBody AppointmentRequest appointmentRequest){
        if(makeAppointmentService.checkAppointment(appointmentRequest.getDoner_id())){
            makeAppointmentService.makeAppointment(appointmentRequest.getSlot(),appointmentRequest.getDoner_id(),
                    appointmentRequest.getCenter_id());
            return "Appointment made succefully";
        }
        return "You can't make an appointment; you are still in a recovery period.";
    }
}
