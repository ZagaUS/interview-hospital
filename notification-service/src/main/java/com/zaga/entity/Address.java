package com.zaga.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    public String houseNo;

    public String street;

    public String city;

    public String state;

    public String zipCode;
}
