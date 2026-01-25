package com.yeti.hospital.repository;

import com.yeti.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

}
