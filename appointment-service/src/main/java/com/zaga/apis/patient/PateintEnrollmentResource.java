package com.zaga.apis.patient;

import com.zaga.enity.patient.PatientDetails;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/zaga/hospital")
public class PateintEnrollmentResource {

    @POST
    @Path("/patientEnrollment")
    public Response patientEnrollment(PatientDetails patientDetails) {

        return Response.ok(patientDetails).build();
    }

}
