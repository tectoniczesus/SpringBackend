package com.yeti.hospital.controller;

import com.yeti.hospital.Mapper.PatientMapper;
import com.yeti.hospital.dto.BloodGroupDTO;
import com.yeti.hospital.dto.PatientResponseDTO;
import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.services.AppointmentServices;
import com.yeti.hospital.services.PatientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private  PatientServices patientServices;
   @Autowired
    private final AppointmentServices appointmentServices;
//    @PatchMapping("/{id}/blood-grp")
//    public ResponseEntity<PatientResponseDTO> updateBloodGrp(
//            @PathVariable Long id,
//            @RequestBody BloodGroupDTO request
//    ){
//        Patient updatePatient =  patientServices.updateBloodGrp(id, request.getBloodGrp());
//        return ResponseEntity.ok(PatientMapper.responseDTO(updatePatient));
//    }
    /**
     *
     * ? /doctor/appointments and /patient/allPatient are gonna  access using jwt token only
     */

    @GetMapping("/allPatient")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatient(
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
  ){
      return ResponseEntity.ok(patientServices.getAllPatient(pageNumber,pageSize));
  }
}
