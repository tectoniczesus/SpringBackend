package com.yeti.hospital.services;

import com.yeti.hospital.entity.Insurance;
import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.repository.InsuranceRepository;
import com.yeti.hospital.repository.PatientRepo;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsauranceServices {

    private InsuranceRepository insuranceRepository;
    private PatientRepo patientRepo;

    public void assigneInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepo.findById(patientId).orElseThrow(()-> new EntityNotFoundException(("Patient not found with this id " + patientId) ));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);

    }
}
