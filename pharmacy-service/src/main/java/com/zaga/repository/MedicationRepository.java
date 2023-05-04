package com.zaga.repository;

import com.zaga.entity.Medications;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MedicationRepository implements PanacheRepository<Medications> {

    @Transactional
    @ActivateRequestContext
    public Boolean medicationStockcheck(Medications medication) {
        PanacheQuery<Medications> data = Medications.find("medication_name=?1 and dosage=?2 ",
                medication.medication_name, medication.dosage);
        if (data.firstResult().getStock() > 0) {
            return true;
        }
        return false;
    }
}
