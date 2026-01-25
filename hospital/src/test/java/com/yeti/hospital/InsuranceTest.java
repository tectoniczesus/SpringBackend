package com.yeti.hospital;

import com.yeti.hospital.entity.Appointment;
import com.yeti.hospital.entity.Insurance;
import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.services.AppointmentServices;
import com.yeti.hospital.services.InsauranceServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
//                .policyNumber("HDFC-7745")
//                .provider("HDFC")
//                .validUntil(LocalDate.of(2027,12,31))
//                .build();
//
//        Patient patient = insauranceServices.assigneInsuranceToPatient(insurance,1l);
//        System.out.println(patient);
//    }
   @Test
    public void testCreateAppointment(){
       Appointment appointment = Appointment.builder()
               .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 0))
               .reason("medical consultation")
               .build();

        Appointment newAppointment = appointmentServices.createNewAppointment(appointment,4l,2l);
       System.out.println(newAppointment );
   }

}
