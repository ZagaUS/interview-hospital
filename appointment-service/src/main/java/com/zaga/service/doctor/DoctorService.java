package com.zaga.service.doctor;

import com.zaga.enity.appointment.Appointment;
import com.zaga.enity.doctor.DoctorShedule;
import com.zaga.enity.doctor.Schedule;

public interface DoctorService {

    DoctorShedule getDoctorById(Long id);

    void updateAppointmentById(Long id, Appointment appointment);

    void updateSchedulebyid(Long id, Schedule schedule);

    void createDoctor(DoctorShedule doctor);
}
