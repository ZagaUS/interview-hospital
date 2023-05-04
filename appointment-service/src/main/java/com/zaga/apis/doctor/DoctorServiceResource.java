package com.zaga.apis.doctor;

import java.util.List;

import com.zaga.enity.doctor.DoctorShedule;
import com.zaga.enity.doctor.Schedule;
import com.zaga.repository.ScheduleRepository;
import com.zaga.service.doctor.DoctorService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/zaga/hospital/doctor")
public class DoctorServiceResource {

    @Inject
    ScheduleRepository srepo;

    @Inject
    DoctorService service;

    @POST
    @Path("/schedule")
    @Transactional
    public void createSchedule(Schedule schedule) {
        srepo.persist(schedule);
    }

    @POST
    @Path("/doctor")
    @Transactional
    public Response createDoctor(DoctorShedule doctor) {
        service.createDoctor(doctor);
        return Response.status(Response.Status.CREATED).entity(doctor).build();
    }

    @POST
    @Path("/update/{id}")
    @Transactional
    public void updateSchedulebyid(@PathParam("id") Long id, Schedule schedule) {
        service.updateSchedulebyid(id, schedule);
    }

    @GET
    @Path("/getById/{id}")
    @Transactional
    public Response getDoctorDetailsbyid(@PathParam("id") Long id) {
        DoctorShedule result = service.getDoctorById(id);
        System.out.println(result);
        return Response.ok(result).build();
    }

    @GET
    @Path("/filterBySpeciality")
    @Transactional
    public Response getDoctorDetailsbySpeciality(@QueryParam("speciality") String speciality) {
        List<DoctorShedule> result = service.listDoctorSpeciality(speciality);
        System.out.println(result);
        return Response.ok(result).build();
    }
}
