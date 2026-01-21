package com.yeti.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 100)
    private String specialization;
    @Column(nullable = false,unique = true,length = 100)
    private String email;
    @ManyToMany(mappedBy = "doctor")
    private Set<Department> departments = new HashSet<>();
}
