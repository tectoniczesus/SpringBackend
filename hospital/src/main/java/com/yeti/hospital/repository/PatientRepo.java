package com.yeti.hospital.repository;

import com.yeti.hospital.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    @Query("select p.bloodGrp, Count(p) from Patient p group by p.bloodGrp")
    List<Object[]> countEachBloodGroupType();

    @Query(value = "select * from Patient",nativeQuery = true)
    List<Patient> finaAllPatient();
    @Transactional
    @Modifying
    @Query("update Patient p set p.name= :name where p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") Long id);
}
