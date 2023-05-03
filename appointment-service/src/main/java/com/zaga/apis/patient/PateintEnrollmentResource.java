package com.zaga.apis.patient;

import com.zaga.enity.patient.PatientDetails;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/zaga/hospital")
public class PateintEnrollmentResource {

    @POST
    @Path("/patientEnrollment")
    @Transactional
    public Response patientEnrollment(PatientDetails patientDetails) {

        PatientDetails p = PatientDetails.builder().first_name("pam").build();
        PatientDetails.persist(p);
        return Response.ok(patientDetails).build();
    }

}
