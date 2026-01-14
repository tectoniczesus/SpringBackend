package com.yeti.hospital.repository;

import com.yeti.hospital.entity.Patient;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepo extends JpaRepository<Patient,Long> {
    //Patient findByName(String name);
    List<Patient> findByBirthDateAndEmail(LocalDate birthDate,String email);
}
