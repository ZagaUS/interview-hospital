package com.zaga.kafka.producer;

import java.util.List;

import com.zaga.enity.patient.Address;
import com.zaga.enity.patient.Medications;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PharmacyEvent {
    private String name;
    private Address address;
    private String phone;

    private List<Medications> medications;
}
