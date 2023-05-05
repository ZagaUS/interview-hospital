package com.zaga.enity.patient;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DiagnosisRecords extends PanacheEntityBase {

    // @ManyToOne
    // @JoinColumn(name = "patient_id")
    // private PatientDetails patient;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String date;
    public String type;
    public String notes;
    @Embedded
    public List<Medications> medications;

}
