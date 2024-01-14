package com.blooddonation.appointementservice.controllers;

import com.blooddonation.appointementservice.dto.AppointmentRequest;
import com.blooddonation.appointementservice.dto.DeleteAppointmentRequest;
import com.blooddonation.appointementservice.dto.UpdateAppointmentRequest;
import com.blooddonation.appointementservice.entities.Appointment;
import com.blooddonation.appointementservice.services.MakeAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
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
    @PutMapping("/updateAppointment")
    public String updateAppointment(@RequestBody UpdateAppointmentRequest updateAppointmentRequest){
        makeAppointmentService.updateAppointment(updateAppointmentRequest.getSlot(), updateAppointmentRequest.getId());
        return "Appointment updated successfully";
    }

    @DeleteMapping("/deleteAppointment")
    public String deleteAppointment(@RequestBody DeleteAppointmentRequest deleteAppointmentRequest){
        makeAppointmentService.deleteAppointment(deleteAppointmentRequest.getId());
        return "Appointment deleted successfully";
    }

    @GetMapping("/getDonorAppointments/{donor_id}")
    public List<Appointment> getDonorAppointments(@PathVariable Long donor_id){
        return makeAppointmentService.getDonorAppointments(donor_id);
    }

    @GetMapping("/getCenterAppointments/{center_id}")
    public List<Appointment> getCenterAppointments(@PathVariable Long center_id){
        return makeAppointmentService.getDonorAppointments(center_id);
    }
}
