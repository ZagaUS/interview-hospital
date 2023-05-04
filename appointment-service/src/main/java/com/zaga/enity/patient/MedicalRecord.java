package com.zaga.enity.patient;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MedicalRecord extends PanacheEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // public Long id;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "patient_id", referencedColumnName = "id")
    // public PatientDetails patient;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "labResult_id", referencedColumnName = "id")
    public List<LabResults> lab_results;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "diagnosisRecord_id", referencedColumnName = "id")
    public List<DiagnosisRecords> diagnosis_records;
}
