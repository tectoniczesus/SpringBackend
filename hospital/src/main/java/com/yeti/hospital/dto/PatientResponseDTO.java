package com.yeti.hospital.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PatientResponseDTO {
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String email;

    private String gender;

    private String bloodGrp;
}
