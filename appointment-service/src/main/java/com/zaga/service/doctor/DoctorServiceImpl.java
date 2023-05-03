package com.zaga.service.doctor;

import java.util.List;

import com.zaga.enity.appointment.Appointment;
import com.zaga.enity.doctor.DoctorShedule;
import com.zaga.enity.doctor.Schedule;
import com.zaga.repository.DoctorRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DoctorServiceImpl implements DoctorService {

    @Inject
    DoctorRepository repo;

    @Override
    public DoctorShedule getDoctorById(Long id) {
        // Query and return DoctorShedule
        DoctorShedule schedule = repo.findById(id);
        return schedule;
    }

    @Override
    public void updateAppointmentById(Long id, Appointment appointment) {
        // Query and return Doctor schedules
        DoctorShedule data = repo.findById(id);
        // Get the list of the appointment
        List<Appointment> list = data.getAppointments();
        // add the appointment to list
        list.add(appointment);
        // update appointments list
        data.setAppointments(list);
        DoctorShedule.persist(data);
    }

    @Override
    public void updateSchedulebyid(Long id, Schedule schedule) {
        // Query and return Doctor schedules
        DoctorShedule data = repo.findById(id);
        // Get the list of the schedule
        List<Schedule> list = data.getSchedules();
        // add the timeslots to list
        // update schedule list by date
        list.add(schedule);
        data.setSchedules(list);
        System.out.println("-----data----" + data);
        DoctorShedule.persist(data);
    }

    @Override
    public void createDoctor(DoctorShedule doctor) {
        repo.persist(doctor);
    }

}
