package com.zaga.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicationOrder {
    private String name;

    private Address address;
    private String phone;

    private List<MedicationDTO> medications;
}
