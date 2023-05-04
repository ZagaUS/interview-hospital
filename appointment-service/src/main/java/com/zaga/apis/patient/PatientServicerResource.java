package com.zaga.apis.patient;

import com.zaga.enity.patient.DiagnosisRecords;
import com.zaga.enity.patient.LabResults;
import com.zaga.enity.patient.MedicalRecord;
import com.zaga.service.patient.PatientService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/zaga/hospital/patientservice")
public class PatientServicerResource {

    @Inject
    PatientService service;

    @GET
    @Path("/getMedicalRecord/{id}")
    public Response getmedicalRecordById(Long id) {
        MedicalRecord record = service.getMedicalRecordbyMedicalRecordId(id);
        return Response.ok(record).build();
    }

    @POST
    @Path("/createMedicalRecord/{patientId}")
    public Response createMedicalRecord(@PathParam("patientId") Long patientId, MedicalRecord record) {
        service.createMedicalRecords(patientId, record);
        return Response.ok(record).build();
    }

    @POST
    @Path("/updateLabresults/{id}")
    public Response updateLabresults(@PathParam("id") Long id, LabResults labResults) {
        service.updateLabResultsInMedicalRecord(id, labResults);
        return Response.ok(labResults).build();
    }

    @POST
    @Path("/updateDianoseRecord/{id}")
    public Response updateDiagnoseRecord(@PathParam("id") Long id, DiagnosisRecords diagnosisRecords) {
        service.updateDiagnoseRecordInMedicalRecord(id, diagnosisRecords);
        return Response.ok(diagnosisRecords).build();
    }
}
