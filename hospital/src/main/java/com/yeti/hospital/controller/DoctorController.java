package com.yeti.hospital.controller;

import com.yeti.hospital.dto.AppointmentResponseDTO;
import com.yeti.hospital.services.AppointmentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
private final AppointmentServices appointmentServices;
  //FIXME use user id to find the appointment for the doctor
  // then check if it is working or not
    @GetMapping("/appointments")
   public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointment(){
       return ResponseEntity.ok(appointmentServices.getDoctorsAppointment(2l));
   }
}
