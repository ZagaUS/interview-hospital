package com.zaga.kafka.consumer;

import com.zaga.entity.MedicationOrder;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MedicationOrderDeserializer extends ObjectMapperDeserializer<MedicationOrder> {

    public MedicationOrderDeserializer() {
        super(MedicationOrder.class);

    }

}
