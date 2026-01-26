package com.yeti.hospital;

import com.yeti.hospital.entity.Appointment;
import com.yeti.hospital.entity.Insurance;
import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.services.AppointmentServices;
import com.yeti.hospital.services.InsauranceServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.AssertionsKt.assertNotNull;

@SpringBootTest
public class InsuranceTest {
   @Autowired
   private InsauranceServices insauranceServices;
   @Autowired
   private AppointmentServices appointmentServices;
//   @Test
//    public void testInsurance(){
//        Insurance insurance = Insurance.builder()
//                .policyNumber("ICIC-7E45")
//                .provider("ICIC")
//                .validUntil(LocalDate.of(2028,8,01))
//                .build();
//
//        Patient patient = insauranceServices.assigneInsuranceToPatient(insurance,2l);
//
//        System.out.println(patient);
//    }

    @Test
    public void deleteInsurance(){
      //this is used for deleting the insurance
        //adding the patient id manually
        // adding the deleteMethod in create insurance will give error or will create a new insurance
       Patient patient = insauranceServices.disassociateInsurance(2l);
        System.out.println(patient);
    }
//    @Transactional
//   @Test
//    public void testCreateAppointment(){
//       Appointment appointment = Appointment.builder()
//               .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 0))
//               .reason("medical consultation")
//               .build();
//     //FIXME the assigning the new doctor is adding a new row instead of updating exiting appointment
//       // the newAppointment will create a new appointment and then the updating will call that's why
//       // it is creating a new row instead of updating the old one
//       //so it is better to add the id manually
//        Appointment newAppointment = appointmentServices.createNewAppointment(appointment,4l,2l);
//       System.out.println("the id of appointment " + newAppointment.getId());
//       System.out.println("creating appointment "+newAppointment);
//         Appointment reAppointment = appointmentServices.reAssigneAppointment(newAppointment.getId(), 6l);
//       System.out.println("after updating appointment "+reAppointment );
//
//       // assertEquals(5L, reAppointment.getDoctor().getId());
//
//   }

//   @Test
//    @Transactional
//    public void testCreateAppointmentAndreAssigneAppointment(){
//       Appointment appointment = Appointment.builder()
//               .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 0))
//               .rea
//   }

}
