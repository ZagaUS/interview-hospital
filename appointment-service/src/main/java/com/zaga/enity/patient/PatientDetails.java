package com.zaga.enity.patient;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PatientDetails extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String patient_id;
    public String first_name;
    public String last_name;
    public String email;
    public String phone;
    public Address AddressObject;
    public String gender;
    public String date_of_birth;
    public EmergencyContact emergencyContact;
}
