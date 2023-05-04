package com.zaga;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaga.enity.appointment.Appointment;
import com.zaga.enity.doctor.DoctorShedule;
import com.zaga.enity.patient.PatientDetails;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentTest {

    private static ObjectMapper mapper;
    private PatientDetails pateDetailsResponse;
    private DoctorShedule doctorSheduleResponse;

    // enroll pateint

    @Test
    @Order(1)
    public void enrollPatient() {

        String json = "{\"name\":\"string\",\"email\":\"string\",\"patientPhone\":\"string\",\"address\":{\"houseNo\":\"string\",\"street\":\"string\",\"city\":\"string\",\"state\":\"string\",\"zipCode\":\"string\"},\"gender\":\"string\",\"date_of_birth\":\"string\",\"emergencyContact\":{\"emergencyContactName\":\"string\",\"relationship\":\"string\",\"phone\":\"string\"},\"medicalRecord\":null}";

        PatientDetails response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .when().post("/zaga/hospital/patientEnrollment").then().statusCode(200).extract()
                .as(PatientDetails.class);

        pateDetailsResponse = response;

        System.out.println(pateDetailsResponse);

    }

    // create doctor
    @Test
    @Order(2)
    public void createDoctorSchedule() {

        String json = "{\"doctor_name\":\"Ram\",\"specialty\":\"Eye\",\"email\":\"ram@gmail.com\",\"phone\":\"979797989\",\"appointments\":null,\"schedules\":[{\"date\":\"2023-05-06\",\"start_Time\":\"10:00\",\"end_Time\":\"17:00\"}]}";

        DoctorShedule response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .when().post("/zaga/hospital/doctor/doctor").then().statusCode(201).extract().as(DoctorShedule.class);
        doctorSheduleResponse = response;

    }

    // List doctor by speciality
    @Test
    @Order(3)
    public void listDoctorBySpeciality() {

        TypeRef<List<DoctorShedule>> tref = new TypeRef<>() {
        };

        List<DoctorShedule> list = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).queryParam("speciality", doctorSheduleResponse.getSpecialty())
                .when().get("/zaga/hospital/doctor/filterBySpeciality").then().statusCode(200).extract().as(tref);
        System.out.println("list" + list);
    }
    // Get doctor by id

    @Test
    @Order(4)
    public void getDoctorbyId() {

        DoctorShedule response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).pathParam("id", doctorSheduleResponse.id)
                .when().get("/zaga/hospital/doctor/getById/{id}").then().statusCode(200).extract()
                .as(DoctorShedule.class);

        System.out.println(response);

    }

    // book Appointment this includes (availability Check)
    @Test
    @Order(5)
    public void bookAppointment() {

        Appointment appointment = Appointment.builder().startTime(LocalTime.of(14, 00)).endTime(LocalTime.of(15, 00))
                .date(LocalDate.of(2023, 05, 06)).doctor_id(doctorSheduleResponse.id).patient_id(pateDetailsResponse.id)
                .timeSlot("").time_slot_id(4).appointment_status("Reserved").appointment_type("follow-up")
                .appointment_reason("Consulting").build();
        Appointment response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(appointment)
                .when().post("/zaga/hospital/bookAppointment").then().statusCode(200).extract().as(Appointment.class);
        System.out.println(response);

    }

    // Not available check while booking
    // Timeslot Limit full

}
