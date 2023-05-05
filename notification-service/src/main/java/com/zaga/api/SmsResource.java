package com.zaga.api;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.zaga.entity.MedicationOrder;
import com.zaga.kafka.consumer.MessageEvent;
import com.zaga.service.MessageBuilderService;

import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/zaga/sms")
public class SmsResource {

    @Inject
    MessageBuilderService service;

    public static final String ACCOUNT_SID = "AC6e6f7aecc1594fcc4efe6008b4a71e9a";
    public static final String AUTH_TOKEN = "c8b0c98cd089391a45e4fe3473992f83";
    public static final String TWILIO_NUMBER = "+13203732251";

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendSms(SmsMessage smsMessage) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String toNumber = smsMessage.getTo();
        String message = smsMessage.getMessage();
        try {
            Message.creator(new PhoneNumber(toNumber), new PhoneNumber(TWILIO_NUMBER), message).create();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return Response.ok("Message sent successfully!").build();
    }

    @Incoming("notification-in")
    @Blocking
    public void sendsms(MessageEvent event) {
        System.out.println(event);
        // if message source is pharmacy
        if (event.source.equals("pharmacy")) {
            if (event.status.equals("Placed")) {
                System.out.println("------here-----");
                SmsMessage input = service.pharmacyPlaced(event);
                sendSms(input);
            }
            if (event.status == "NotAvailable") {
                SmsMessage input = service.pharmacyNotAvailable(event);
                sendSms(input);
            }
        }

        // if sourcce is appointment
        // if status booked
        if (event.source.equals("Appointment")) {
            if (event.status.equals("Booked")) {
                SmsMessage input = service.appointmentBooked(event);
                sendSms(input);
            }
        }
    }
}