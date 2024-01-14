package com.blooddonation.appointementservice.controllers;

import com.blooddonation.appointementservice.dto.AppointmentRequest;
import com.blooddonation.appointementservice.services.MakeAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins ="*")
public class AppointmentController {
    @Autowired
    private final MakeAppointmentService makeAppointmentService;

    @PostMapping("/makeAppointment")
    public String makeAppointment(@RequestBody AppointmentRequest appointmentRequest){
        if(makeAppointmentService.canMakeAppointment(appointmentRequest.getDonor_id())){
            if(makeAppointmentService.checkSlotAvailable(appointmentRequest.getCenter_id(), appointmentRequest.getSlot())){
                makeAppointmentService.makeAppointment(appointmentRequest.getSlot(),appointmentRequest.getDonor_id(),
                        appointmentRequest.getCenter_id());
                return "Appointment made succefully";
            }
            return "Slot full please choose other one";
        }
        return "You can't make an appointment; you are still in a recovery period.";
    }
}
