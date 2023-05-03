package com.zaga.repository;

import com.zaga.enity.patient.DiagnosisRecords;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DiagnosisRepository implements PanacheRepositoryBase<DiagnosisRecords, Long> {

}
