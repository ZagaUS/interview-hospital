package com.zaga.service;

import com.zaga.api.SmsMessage;
import com.zaga.kafka.consumer.MessageEvent;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessageBuilderService {

    public SmsMessage pharmacyPlaced(MessageEvent event) {

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Hello, ")
                .append("Customer")
                .append("! ")
                .append("Your Medication Order   ")
                .append("is Placed");

        String message = messageBuilder.toString();

        return SmsMessage.builder().message(message).to(event.phoneNo).build();
    }

    public SmsMessage pharmacyNotAvailable(MessageEvent event) {

        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Hello, ")
                .append("Customer")
                .append("! ")
                .append("Sorry  Your Some of the Medications  ")
                .append("in your prescribtion is not available");

        String message = messageBuilder.toString();

        return SmsMessage.builder().message(message).to(event.phoneNo).build();
    }

    public SmsMessage appointmentBooked(MessageEvent event) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Hello, ")
                .append("Customer")
                .append("! ")
                .append("Your Appointment ")
                .append("is Booked");

        String message = messageBuilder.toString();

        return SmsMessage.builder().message(message).to(event.phoneNo).build();
    }

}
