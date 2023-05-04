package com.zaga.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class MedicationDTO {
    public String medication_name;

    public String dosage;

    public String medication_frequency;

    public String medication_start_date;

    public String medication_end_date;
}
