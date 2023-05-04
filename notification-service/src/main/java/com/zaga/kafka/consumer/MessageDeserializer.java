package com.zaga.kafka.consumer;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MessageDeserializer extends ObjectMapperDeserializer<MessageEvent> {

    public MessageDeserializer() {
        super(MessageEvent.class);

    }

}
