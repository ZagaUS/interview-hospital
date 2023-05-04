package com.zaga.repository;

import com.zaga.entity.Medications;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MedicationRepository implements PanacheRepository<Medications> {

}
