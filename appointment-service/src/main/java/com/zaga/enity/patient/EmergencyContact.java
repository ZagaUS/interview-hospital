package com.zaga.enity.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmergencyContact {
    public String name;
    public String relationship;
    public String phone;
}
