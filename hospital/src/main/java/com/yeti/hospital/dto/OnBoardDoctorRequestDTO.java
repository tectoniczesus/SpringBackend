package com.yeti.hospital.dto;

import lombok.Data;

@Data
public class OnBoardDoctorRequestDTO {
     private Long userId;
     private String specialization;
     private String name;
}
