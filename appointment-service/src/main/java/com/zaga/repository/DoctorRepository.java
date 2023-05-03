package com.zaga.repository;

import com.zaga.enity.doctor.DoctorShedule;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DoctorRepository implements PanacheRepositoryBase<DoctorShedule, Long> {

}
