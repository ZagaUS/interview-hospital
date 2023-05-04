package com.zaga.repository;

import com.zaga.entity.MedicationOrder;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MedicationOrderRepository implements PanacheRepository<MedicationOrder> {

}
