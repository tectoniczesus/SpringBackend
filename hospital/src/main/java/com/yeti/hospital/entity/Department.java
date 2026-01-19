package com.yeti.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.Doc;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true,length = 100)
    private String name;

    @OneToOne//this is for one to one only creates head_doctor_id in department table
   private Doctor headDoctor;
    @ManyToMany//this is for many to many where there is one table is created in which department_id
    //and docter_set_id both will be primary key
    //it will create an extra table known as department_doctor_set
    @JoinTable(name = "my_dept_doc",
     joinColumns = @JoinColumn(name="dept_id"),
    inverseJoinColumns = @JoinColumn(name= "doctor_id"))
    private Set<Doctor> doctor = new HashSet<>();
}
