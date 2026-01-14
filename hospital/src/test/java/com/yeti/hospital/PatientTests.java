package com.yeti.hospital;

import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.repository.PatientRepo;
import com.yeti.hospital.services.PatientServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {
    @Autowired
  private PatientRepo patientRepo;
    @Autowired
    private PatientServices patientServices;
//  @Test
//  public void getPatient(){
//      List<Patient> patientList = patientRepo.findAll();
//      System.out.println(patientList);
//  }
  @Test
  public void transactionMethod(){
//      Patient patient = patientRepo.findByName("Aarav Sharma");
//      System.out.println(patient);
      List<Patient> patientList = patientRepo.findByBirthDateAndEmail(LocalDate.of(2001,07,22),"priya.verma@gmail.com");
      for (Patient patient:patientList){
          System.out.println(patient);
      }
  }




}
