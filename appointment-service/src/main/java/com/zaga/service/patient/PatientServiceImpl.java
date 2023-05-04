package com.zaga.service.patient;

import java.util.List;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import com.zaga.enity.patient.DiagnosisRecords;
import com.zaga.enity.patient.LabResults;
import com.zaga.enity.patient.MedicalRecord;
import com.zaga.enity.patient.PatientDetails;
import com.zaga.kafka.producer.PharmacyEvent;
import com.zaga.repository.MedicalRecordRepository;
import com.zaga.repository.PatientRepository;

import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PatientServiceImpl implements PatientService {

    @Inject
    MedicalRecordRepository repo;

    @Inject
    PatientRepository prepo;

    @Inject
    @Channel("pharmacy-out")
    Emitter<PharmacyEvent> emitter;

    @Override
    public void createMedicalRecords(Long patientId, MedicalRecord medicalRecord) {
        PatientDetails pd = prepo.findById(patientId);

        MedicalRecord medicalRecorddata = pd.getMedicalRecord();
        if (medicalRecorddata == null) {
            pd.setMedicalRecord(medicalRecord);
            System.out.println(pd);
            pd.persist();
        }
        // repo.persist(medicalRecord);

    }

    @Override
    public MedicalRecord getMedicalRecordbyPatientId(Long id) {

        return repo.findbyProjectId(id);
    }

    @Override
    public MedicalRecord getMedicalRecordbyMedicalRecordId(Long id) {

        PatientDetails pd = prepo.findById(id);
        return pd.getMedicalRecord();
    }

    @Override
    @Transactional
    public void updateLabResultsInMedicalRecord(Long id, LabResults labResults) {

        PatientDetails pd = prepo.findById(id);

        // get medical records by id
        MedicalRecord medicalRecord = pd.getMedicalRecord();
        // get labresult list
        List<LabResults> labResultList = medicalRecord.getLab_results();

        System.out.println("-------labresultList---" + labResultList);
        // add new lab results
        labResultList.add(labResults);
        // persist the MedicalRecord

        medicalRecord.setLab_results(labResultList);
        MedicalRecord.persist(medicalRecord);
        pd.setMedicalRecord(medicalRecord);
        // persist the patient records
        PatientDetails.persist(pd);

        pd.persist();

    }

    @Override
    @Transactional
    public PharmacyEvent updateDiagnoseRecordInMedicalRecord(Long id, DiagnosisRecords diagnosisRecords) {
        // Patient details using patient id
        PatientDetails pd = prepo.findById(id);

        System.out.println("---query--" + pd);
        // get medical records by id
        MedicalRecord medicalRecord = pd.getMedicalRecord();
        // get Diagnose list
        List<DiagnosisRecords> diagnoseList = medicalRecord.getDiagnosis_records();
        // add new diagnose
        System.out.println(diagnoseList);
        diagnoseList.add(diagnosisRecords);
        System.out.println(diagnoseList);
        // persist the MedicalRecord
        medicalRecord.setDiagnosis_records(diagnoseList);
        pd.setMedicalRecord(medicalRecord);
        MedicalRecord.persist(diagnoseList);
        System.out.println(pd);
        // if medication is present generate event
        PatientDetails.persist(pd);
        // pharmacy event
        PharmacyEvent event = PharmacyEvent.builder().address(pd.getAddress()).name(pd.getName())
                .phone(pd.getPatientPhone()).medications(diagnosisRecords.getMedications()).build();
        emitter.send(event);
        return event;
    }
}