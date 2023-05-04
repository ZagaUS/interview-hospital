package com.zaga.service;

import com.zaga.entity.MedicationOrder;
import com.zaga.repository.MedicationOrderRepository;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MedicationOrderservice {

    @Inject
    MedicationOrderRepository morepo;

    public void createMedicationOrder(MedicationOrder medicationOrder) {
        morepo.persist(medicationOrder);
    }

}
