package com.zaga.api;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.zaga.entity.MedicationDTO;
import com.zaga.entity.MedicationOrder;
import com.zaga.entity.Medications;
import com.zaga.kafka.producer.NotificationMessageEvent;
import com.zaga.repository.MedicationOrderRepository;
import com.zaga.repository.MedicationRepository;
import com.zaga.service.MedicationOrderservice;

import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.ejb.PostActivate;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/zaga/pharmacy")
public class PharmacyResource {

    // @Inject
    // MedicationOrderRepository morepo;

    // @Inject
    // @Channel("notification-out")
    // Emitter<>
    @Inject
    @Channel("notification-out")
    Emitter<NotificationMessageEvent> emitter;

    @Inject
    MedicationOrderservice service;

    @Inject
    MedicationRepository mrepo;

    @POST
    @Path("/storeOrder")
    @Transactional
    @ActivateRequestContext
    public void storeMedicationOrder(MedicationOrder medicationOrder) {
        service.createMedicationOrder(medicationOrder);
        System.out.println(medicationOrder);
    }

    @GET
    @Path("/stockcheck")
    @Transactional
    @ActivateRequestContext
    public Boolean stockCheck(Medications medications) {
        return mrepo.medicationStockcheck(medications);
    }

    @Incoming("pharmacy-in")
    @Blocking
    public void eventConsumerFromAppointmentService(MedicationOrder medicationOrder) {
        System.out.println(medicationOrder);
        List<MedicationDTO> medications = medicationOrder.getMedications();
        List<Boolean> stockCheckResults = medications.stream()
                .map(medication -> {
                    String medName = medication.getMedication_name();
                    String medDosage = medication.getDosage();
                    Medications medicationEntity = Medications.builder().medication_name(medName).dosage(medDosage)
                            .build();
                    return mrepo.medicationStockcheck(medicationEntity);
                })
                .collect(Collectors.toList());
        System.out.println(stockCheckResults);
        boolean allTrue = stockCheckResults.stream().allMatch(result -> result);

        if (allTrue) {
            // persist
            storeMedicationOrder(medicationOrder);
            // send notification
            NotificationMessageEvent event = NotificationMessageEvent.builder().phoneNo(medicationOrder.getPhone())
                    .source("pharmacy").status("Placed").build();
            System.out.println("------Event------" + event);
            emitter.send(event);

        } else {
            // send notification

            NotificationMessageEvent notAvailableEvent = NotificationMessageEvent.builder()
                    .phoneNo(medicationOrder.getPhone()).source("pharmacy").status("NotAvailable").build();
            emitter.send(notAvailableEvent);
        }

    }

}
