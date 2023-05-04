package com.zaga.apis.patient;

import com.zaga.enity.patient.PatientDetails;
import com.zaga.repository.PatientRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/zaga/hospital")
public class PateintEnrollmentResource {

    @Inject
    PatientRepository repo;

    @POST
    @Path("/patientEnrollment")
    @Transactional
    public Response patientEnrollment(PatientDetails patientDetails) {

        repo.persist(patientDetails);
        return Response.ok(patientDetails).build();
    }

}
