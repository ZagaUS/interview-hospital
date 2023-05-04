package com.zaga.repository;

import com.zaga.enity.patient.LabResults;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LabresultsRepository implements PanacheRepository<LabResults> {

}
