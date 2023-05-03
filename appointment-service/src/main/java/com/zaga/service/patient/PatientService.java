package com.zaga.service.patient;

import com.zaga.enity.patient.DiagnosisRecords;
import com.zaga.enity.patient.LabResults;
import com.zaga.enity.patient.MedicalRecord;

public interface PatientService {

    void createMedicalRecords(MedicalRecord medicalRecord);

    MedicalRecord getMedicalRecordbyProjectId(Long id);

    MedicalRecord getMedicalRecordbyMedicalRecordId(Long id);

    void updateLabResultsInMedicalRecord(Long id, LabResults labResults);

    void updateDiagnoseRecordInMedicalRecord(Long id, DiagnosisRecords diagnosisRecords);
}
