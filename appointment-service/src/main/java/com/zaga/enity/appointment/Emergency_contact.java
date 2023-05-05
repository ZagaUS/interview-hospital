package com.zaga.enity.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Emergency_contact {

    public String name;
    public String relationship;
    public String phone;
}
