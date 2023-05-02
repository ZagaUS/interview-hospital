package com.zaga.enity.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medications {
    public String medication_id;
    public String medication_name;
    public String medication_dosage;
    public String medication_frequency;
    public String medication_start_date;
    public String medication_end_date;

}
