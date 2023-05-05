package com.zaga.apis.appointment;

import com.zaga.enity.appointment.Appointment;
import com.zaga.service.appointment.Appointmentservice;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/zaga/hospital")
public class AppointmentResource {

    @Inject
    Appointmentservice service;

    @POST
    @Path("/bookAppointment")
    public Response book(Appointment appointment) {

        service.bookAppointment(appointment);

        return Response.ok(appointment).build();

    }

}
