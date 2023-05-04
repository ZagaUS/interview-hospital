package com.zaga.repository;

import java.util.List;

import com.zaga.enity.doctor.DoctorShedule;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DoctorRepository implements PanacheRepositoryBase<DoctorShedule, Long> {

    public List<DoctorShedule> getDoctorSheduleListbySpeciality(String speciality) {
        List<DoctorShedule> list = list("specialty=?1", speciality);
        return list;
    }
}
