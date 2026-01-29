package com.yeti.hospital.services;

import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.repository.PatientRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Transactional
    public Patient deletingPatient(Long patientId){
        Patient patient = patientRepo.findById(patientId).orElseThrow(()-> new EntityNotFoundException("patient not found" + patientId));

        patientRepo.delete(patient);
        System.out.println("patient deleted" + patient);
        return patient;
    }

//    public Patient updateBloodGrp(Long id, BloodGrpType bloodGrp){
//        Patient patient = patientRepo.findById(id).orElseThrow(()->new RuntimeException("patient not found"));
//        patient.setBloodGrp(bloodGrp);
//        return patientRepo.save(patient);
//
//    }
}
