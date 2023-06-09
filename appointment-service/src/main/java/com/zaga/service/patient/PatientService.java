package com.zaga.service.patient;

import java.util.List;

import com.zaga.enity.patient.DiagnosisRecords;
import com.zaga.enity.patient.LabResults;
import com.zaga.enity.patient.MedicalRecord;
import com.zaga.kafka.producer.PharmacyEvent;

public interface PatientService {

    void createMedicalRecords(Long patientId, MedicalRecord medicalRecord);

    MedicalRecord getMedicalRecordbyPatientId(Long id);

    MedicalRecord getMedicalRecordbyMedicalRecordId(Long id);

    void updateLabResultsInMedicalRecord(Long id, LabResults labResults);

    PharmacyEvent updateDiagnoseRecordInMedicalRecord(Long id, DiagnosisRecords diagnosisRecords);
}
