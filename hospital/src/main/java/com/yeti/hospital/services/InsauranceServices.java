package com.yeti.hospital.services;

import com.yeti.hospital.entity.Insurance;
import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.repository.InsuranceRepository;
import com.yeti.hospital.repository.PatientRepo;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsauranceServices {
     @Autowired
    private InsuranceRepository insuranceRepository;
  @Autowired
  private PatientRepo patientRepo;
    @Transactional
    public Patient  assigneInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepo.findById(patientId).orElseThrow(()-> new EntityNotFoundException(("Patient not found with this id " + patientId) ));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);
      return patient;
    }

    @Transactional
    public Patient disassociateInsurance(Long patientID){
     Patient patient = patientRepo.findById(patientID).orElseThrow(()-> new EntityNotFoundException(("patient not found with this id" + patientID)));

     patient.setInsurance(null);
     return patient;
    }
}
