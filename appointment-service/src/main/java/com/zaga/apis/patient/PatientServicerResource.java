package com.zaga.apis.patient;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.zaga.enity.patient.DiagnosisRecords;
import com.zaga.enity.patient.LabResults;
import com.zaga.enity.patient.MedicalRecord;
import com.zaga.service.patient.PatientService;

import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/zaga/hospital/patientservice")
@Tag(name = "Patient Management Services ", description = "Api for Managing patient details  ")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientServicerResource {

    @Inject
    PatientService service;

    @GET
    @Path("/getMedicalRecord/{id}")
    @APIResponse(responseCode = "200", description = "Get Medical Record by Patient Id")
    public Response getmedicalRecordById(Long id) {
        MedicalRecord record = service.getMedicalRecordbyMedicalRecordId(id);
        return Response.ok(record).build();
    }

    @POST
    @Path("/createMedicalRecord/{patientId}")
    @APIResponse(responseCode = "200", description = "Create New Medical Record by Patient Id")
    public Response createMedicalRecord(@PathParam("patientId") Long patientId, MedicalRecord record) {
        service.createMedicalRecords(patientId, record);
        return Response.ok(record).build();
    }

    @POST
    @Path("/updateLabresults/{id}")
    @APIResponse(responseCode = "200", description = "Update Lab Results Record that is adding new records to Medical records by Patient Id")
    public Response updateLabresults(@PathParam("id") Long id, LabResults labResults) {
        service.updateLabResultsInMedicalRecord(id, labResults);
        return Response.ok(labResults).build();
    }

    @POST
    @Path("/updateDianoseRecord/{id}")
    @APIResponse(responseCode = "200", description = "Update Lab Diagnosis Record that is adding new records to Medical records by Patient Id")
    public Response updateDiagnoseRecord(@PathParam("id") Long id, DiagnosisRecords diagnosisRecords) {
        service.updateDiagnoseRecordInMedicalRecord(id, diagnosisRecords);
        return Response.ok(diagnosisRecords).build();
    }
}
