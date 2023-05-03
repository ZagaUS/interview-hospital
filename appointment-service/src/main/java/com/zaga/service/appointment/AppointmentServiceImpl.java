package com.zaga.service.appointment;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.zaga.enity.appointment.Appointment;
import com.zaga.enity.doctor.DoctorShedule;
import com.zaga.enity.doctor.Schedule;
import com.zaga.repository.AppontmentRepository;
import com.zaga.repository.DoctorRepository;
import com.zaga.service.doctor.DoctorService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class AppointmentServiceImpl implements Appointmentservice {

    @Inject
    DoctorService dservice;

    @Inject
    AppontmentRepository repo;

    @Override
    public void bookAppointment(Appointment appointment) {
        // checkavailability method
        if (checkAvailabiltyAndTimeslot(appointment)) {
            System.out.println("---------Checked the availability successful-------");
            repo.persist(appointment);
        } else {
            System.out.println("Error");
            // Throw exception
        }

    }

    @Override
    public void createAppointment(Appointment appointment) {

    }

    @Override
    public void cancelAppointment(Appointment appointment) {

    }

    @Override
    public Boolean checkAvailabiltyAndTimeslot(Appointment appointment) {
        // get schedule of the Doctor by doctor id
        DoctorShedule doctor = dservice.getDoctorById(appointment.getDoctor_id());
        List<Schedule> schedules = doctor.getSchedules();
        List<Schedule> filteredSchedules = schedules.stream()
                .filter(schedule -> schedule.getDate().equals(appointment.getDate()))
                .collect(Collectors.toList());

        System.out.println("filtered data" + filteredSchedules);
        Schedule schedule = filteredSchedules.get(0);
        // check the timeslot is within the schedule range
        LocalTime startTime = appointment.getStartTime();
        LocalTime endTime = appointment.getEndTime();
        // LocalTime scheduleStart = schedule.getStart_Time();
        // LocalTime schedule_end = schedule.getEnd_Time();
        List<Integer> array = schedule.getTime_slots();

        if ((startTime.isAfter(schedule.getStart_Time()) || endTime.equals(schedule.getEnd_Time()))
                && (endTime.isBefore(schedule.getEnd_Time()) || endTime.equals(schedule.getEnd_Time()))) {

            int apointmentCount = array.get(appointment.getTime_slot_id());
            if (apointmentCount + 1 > 4) {
                return false;
            } else {
                apointmentCount = apointmentCount + 1;
                array.set(appointment.getTime_slot_id(), apointmentCount);
                schedule.setTime_slots(array);
                // schedule.persist();
                schedules.add(schedule);
                doctor.setSchedules(schedules);
                List<Appointment> appointments = doctor.getAppointments();
                appointments.add(appointment);
                doctor.setAppointments(appointments);
                doctor.persist();
                // return true
                return true;
            }

        }
        // check the timslot limit using timslots array of doctor

        return null;
    }

}
