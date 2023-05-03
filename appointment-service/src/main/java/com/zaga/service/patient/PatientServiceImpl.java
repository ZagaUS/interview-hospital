package com.zaga.service.patient;

import java.util.List;

import com.zaga.enity.patient.DiagnosisRecords;
import com.zaga.enity.patient.LabResults;
import com.zaga.enity.patient.MedicalRecord;
import com.zaga.repository.MedicalRecordRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PatientServiceImpl implements PatientService {

    @Inject
    MedicalRecordRepository repo;

    @Override
    public void createMedicalRecords(MedicalRecord medicalRecord) {

        repo.persist(medicalRecord);

    }

    @Override
    public MedicalRecord getMedicalRecordbyProjectId(Long id) {

        return repo.findbyProjectId(id);
    }

    @Override
    public MedicalRecord getMedicalRecordbyMedicalRecordId(Long id) {
        return repo.findById(id);
    }

    @Override
    public void updateLabResultsInMedicalRecord(Long id, LabResults labResults) {
        // get medical records by id
        MedicalRecord medicalRecord = getMedicalRecordbyMedicalRecordId(id);
        // get labresult list
        List<LabResults> labResultList = medicalRecord.getLab_results();
        // add new lab results
        labResultList.add(labResults);
        // persist the MedicalRecord
        medicalRecord.persist();
    }

    @Override
    public void updateDiagnoseRecordInMedicalRecord(Long id, DiagnosisRecords diagnosisRecords) {
        // get medical records by id
        MedicalRecord medicalRecord = getMedicalRecordbyMedicalRecordId(id);
        // get Diagnose list
        List<DiagnosisRecords> diagnoseList = medicalRecord.getDiagnosis_records();
        // add new diagnose
        diagnoseList.add(diagnosisRecords);
        // persist the MedicalRecord
        medicalRecord.persist();
    }
}