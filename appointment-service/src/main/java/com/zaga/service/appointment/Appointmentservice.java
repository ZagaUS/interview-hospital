package com.zaga.service.appointment;

import com.zaga.enity.appointment.Appointment;
import com.zaga.kafka.producer.MessageEvent;

public interface Appointmentservice {

    void bookAppointment(Appointment appointment);

    void createAppointment(Appointment appointment);

    void cancelAppointment(Appointment appointment);

    Boolean checkAvailabiltyAndTimeslot(Appointment appointment);

    void sendEvent(MessageEvent event);

}
