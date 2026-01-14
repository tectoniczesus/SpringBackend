package com.yeti.hospital.services;

import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.entity.types.BloodGrpType;
import com.yeti.hospital.repository.PatientRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServices {
    @Autowired
    private PatientRepo patientRepo;


    @Transactional
    public Patient getPatientById(Long id){
        Patient p1 = patientRepo.findById(id).orElseThrow();
        Patient p2 = patientRepo.findById(id).orElseThrow();

        return p1;
    }

//    public Patient updateBloodGrp(Long id, BloodGrpType bloodGrp){
//        Patient patient = patientRepo.findById(id).orElseThrow(()->new RuntimeException("patient not found"));
//        patient.setBloodGrp(bloodGrp);
//        return patientRepo.save(patient);
//
//    }
}
