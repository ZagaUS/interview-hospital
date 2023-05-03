package com.zaga.repository;

import com.zaga.enity.appointment.Appointment;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppontmentRepository implements PanacheRepositoryBase<Appointment, Long> {

}
