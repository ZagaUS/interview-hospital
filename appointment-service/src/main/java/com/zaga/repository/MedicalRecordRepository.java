package com.zaga.repository;

import java.util.List;

import com.zaga.enity.patient.MedicalRecord;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MedicalRecordRepository implements PanacheRepositoryBase<MedicalRecord, Long> {

    public MedicalRecord findbyProjectId(Long patientid) {
        List<MedicalRecord> medicalRecord = list("patient.id", patientid);
        return medicalRecord.get(0);
    }

}
