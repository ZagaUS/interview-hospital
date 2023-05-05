package com.zaga.repository;

import com.zaga.enity.patient.PatientDetails;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class PatientRepository implements PanacheRepository<PatientDetails> {

}
