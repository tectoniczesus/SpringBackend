package com.yeti.hospital.repository;

import com.yeti.hospital.entity.Patient;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepo extends JpaRepository<Patient,Long> {
    //Patient findByName(String name);
    List<Patient> findByBirthDateAndEmail(LocalDate birthDate,String email);
    List<Patient> findByNameEndingWith(String name);
    @Query("SELECT P FROM Patient P WHERE P.birthDate BETWEEN :startDate AND :endDate")
    List<Patient> findPatientByBirthDateRange(@Param("startDate") LocalDate startDate,
                                              @Param("endDate") LocalDate endDate);
}
