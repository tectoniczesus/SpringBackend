package com.yeti.hospital;

import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.repository.PatientRepo;
import com.yeti.hospital.services.PatientServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientTests {
    @Autowired
  private PatientRepo patientRepo;
    @Autowired
    private PatientServices patientServices;
  @Test
  public void getPatient(){
      List<Patient> patientList = patientRepo.findAll();
      System.out.println(patientList);
  }
  @Test
  public void transactionMethod(){
      Patient patient = patientServices.getPatientById(1l);
      System.out.println(patient);
  }

}
