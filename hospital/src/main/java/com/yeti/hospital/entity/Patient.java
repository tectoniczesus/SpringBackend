package com.yeti.hospital.entity;

import com.yeti.hospital.entity.types.BloodGrpType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
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

   @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
   @JoinColumn(name = "patient_insurance_id")//this is owing side
   private Insurance insurance;

   @OneToMany(mappedBy = "patient",cascade = {CascadeType.REMOVE},orphanRemoval = true)
   @ToString.Exclude
   private List<Appointment> appointmentList = new ArrayList<>();
}
