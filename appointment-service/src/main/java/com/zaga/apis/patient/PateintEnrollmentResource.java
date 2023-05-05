package com.zaga.apis.patient;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.zaga.enity.patient.PatientDetails;
import com.zaga.repository.PatientRepository;

import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/zaga/hospital")
@Tag(name = "Patient Enrollment", description = "Api for enrolling patient  ")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PateintEnrollmentResource {

    @Inject
    PatientRepository repo;

    @POST
    @Path("/patientEnrollment")
    // @Transactional
    // @ActivateRequestContext
    @APIResponse(responseCode = "200", description = "Created New Pateintdetail")
    public Response patientEnrollment(PatientDetails patientDetails) {

        repo.persist(patientDetails);
        // PatientDetails.persist(patientDetails);
        return Response.ok(patientDetails).build();
    }

}
