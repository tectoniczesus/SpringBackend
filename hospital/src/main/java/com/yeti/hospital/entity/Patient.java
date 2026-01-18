package com.yeti.hospital.entity;

import com.yeti.hospital.entity.types.BloodGrpType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@ToString
@Data
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private LocalDate birthDate;

   private String email;

   private String gender;
   private String bloodGrp;

   @OneToOne
   @JoinColumn(name = "patient_insurance_id")//this is owing side
   private Insurance insurance;
   @OneToMany(mappedBy = "patient")
   private List<Appointment> appointmentList;
}
