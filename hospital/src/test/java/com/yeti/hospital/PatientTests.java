package com.yeti.hospital;

import com.yeti.hospital.dto.BloodGroupCountRepositoryEntity;
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
      List<Patient> patientList = patientRepo.findByNameEndingWith("a");
      System.out.println("FIND BY NAME ENDING WITH");
      for (Patient patient:patientList){
          System.out.println(patient);
      }
  }

  @Test
    public void findByDate(){
      //FINDING BY QUERY METHOD
      List<Patient> patientList = patientRepo.findPatientByBirthDateRange(LocalDate.of(1998,1,1),LocalDate.of(2003,1,1));
      System.out.println("BIRTH DATE BETWEEN"   );
      for (Patient patient:patientList){
          System.out.println(patient);
      }

  }
  @Test
  public void countBloodGrp(){
      System.out.println("counting the blood grp");
      List<BloodGroupCountRepositoryEntity> bloodGrpList = patientRepo.countEachBloodType();
      for (BloodGroupCountRepositoryEntity bloodGroupCountRepositoryEntity: bloodGrpList){
          System.out.println(bloodGroupCountRepositoryEntity);
      }
  }
    @Test
    public void findByBloodGrp(){
        List<Object[]> bloodGrpList = patientRepo.countEachBloodGroupType();
            for (Object[] objects: bloodGrpList){
                System.out.println(objects[0] + " " + objects[1]);
            }
    }
//    @Test
//    public void findAllPatient(){
//        List<Patient> patientList = patientRepo.finaAllPatient();
//        for (Patient patient: patientList){
//            System.out.println(patient);
//        }
//    }
    @Test
    public void updatePatient(){
        int updatedRow = patientRepo.updateNameWithId("John",1l);
        System.out.println(updatedRow);
    }





}
