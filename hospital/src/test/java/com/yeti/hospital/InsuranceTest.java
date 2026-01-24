package com.yeti.hospital;

import com.yeti.hospital.entity.Insurance;
import com.yeti.hospital.entity.Patient;
import com.yeti.hospital.services.InsauranceServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {
   @Autowired
   private InsauranceServices insauranceServices;
    @Test
    public void testInsurance(){
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC-7745")
                .provider("HDFC")
                .validUntil(LocalDate.of(2027,12,31))
                .build();

        Patient patient = insauranceServices.assigneInsuranceToPatient(insurance,1l);
        System.out.println(patient);
    }
}
