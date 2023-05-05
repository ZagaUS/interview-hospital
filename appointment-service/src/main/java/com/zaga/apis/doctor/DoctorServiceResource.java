package com.zaga.apis.doctor;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.zaga.enity.doctor.DoctorShedule;
import com.zaga.enity.doctor.Schedule;
import com.zaga.repository.ScheduleRepository;
import com.zaga.service.doctor.DoctorService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/zaga/hospital/doctor")
@Tag(name = "Patient Management Services ", description = "Api for Managing patient details  ")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorServiceResource {

    @Inject
    ScheduleRepository srepo;

    @Inject
    DoctorService service;

    @POST
    @Path("/schedule")
    @Transactional
    @APIResponse(responseCode = "204", description = "Create new Schedule to tables")
    public void createSchedule(Schedule schedule) {
        srepo.persist(schedule);
    }

    @POST
    @Path("/doctor")
    @Transactional
    @APIResponse(responseCode = "200", description = "Create new Doctorschedule to tables")
    public Response createDoctor(DoctorShedule doctor) {
        service.createDoctor(doctor);
        return Response.status(Response.Status.CREATED).entity(doctor).build();
    }

    @POST
    @Path("/update/{id}")
    @Transactional
    @APIResponse(responseCode = "204", description = "Update schedule Record that is adding new records to Doctorschedule records by Doctor Id")
    public void updateSchedulebyid(@PathParam("id") Long id, Schedule schedule) {
        service.updateSchedulebyid(id, schedule);
    }

    @GET
    @Path("/getById/{id}")
    @Transactional
    @APIResponse(responseCode = "200", description = "Get Lab Doctor Results By Doctor Id")
    public Response getDoctorDetailsbyid(@PathParam("id") Long id) {
        DoctorShedule result = service.getDoctorById(id);
        System.out.println(result);
        return Response.ok(result).build();
    }

    @GET
    @Path("/filterBySpeciality")
    @Transactional
    @APIResponse(responseCode = "200", description = "Get Lab Doctor Results By Doctor Specialization")
    public Response getDoctorDetailsbySpeciality(@QueryParam("speciality") String speciality) {
        List<DoctorShedule> result = service.listDoctorSpeciality(speciality);
        System.out.println(result);
        return Response.ok(result).build();
    }
}
