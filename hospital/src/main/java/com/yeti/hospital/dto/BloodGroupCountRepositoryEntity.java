package com.yeti.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BloodGroupCountRepositoryEntity {
    private String bloodGrp;
    private Long count;
}
