package com.yeti.hospital.controller;

import com.yeti.hospital.Mapper.PatientMapper;
import com.yeti.hospital.dto.BloodGroupDTO;
import com.yeti.hospital.dto.PatientResponseDTO;
import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
    @Autowired
    private PatientServices patientServices;

//    @PatchMapping("/{id}/blood-grp")
//    public ResponseEntity<PatientResponseDTO> updateBloodGrp(
//            @PathVariable Long id,
//            @RequestBody BloodGroupDTO request
//    ){
//        Patient updatePatient =  patientServices.updateBloodGrp(id, request.getBloodGrp());
//        return ResponseEntity.ok(PatientMapper.responseDTO(updatePatient));
//    }
}
