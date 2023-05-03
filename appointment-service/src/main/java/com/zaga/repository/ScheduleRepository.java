package com.zaga.repository;

import java.time.LocalDate;

import com.zaga.enity.doctor.Schedule;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScheduleRepository implements PanacheRepositoryBase<Schedule, LocalDate> {

}
