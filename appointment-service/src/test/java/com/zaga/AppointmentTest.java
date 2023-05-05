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
import com.zaga.enity.patient.MedicalRecord;
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
    private MedicalRecord medicalRecordResponse;

    // enroll pateint

    @Test
    @Order(1)
    public void enrollPatient() {

        // String json =
        // "{\"name\":\"string\",\"email\":\"string\",\"patientPhone\":\"+91989447385\",\"address\":{\"houseNo\":\"string\",\"street\":\"string\",\"city\":\"string\",\"state\":\"string\",\"zipCode\":\"string\"},\"gender\":\"string\",\"date_of_birth\":\"string\",\"emergencyContact\":{\"emergencyContactName\":\"string\",\"relationship\":\"string\",\"phone\":\"string\"},\"medicalRecord\":null}";
        // String json =
        // "{\"name\":\"Raghul\",\"email\":\"string\",\"patientPhone\":\"+91989447385\",\"address\":{\"houseNo\":\"string\",\"street\":\"string\",\"city\":\"string\",\"state\":\"string\",\"zipCode\":\"string\"},\"gender\":\"string\",\"date_of_birth\":\"string\",\"emergencyContact\":{\"emergencyContactName\":\"string\",\"relationship\":\"string\",\"phone\":\"string\"},\"medicalRecord\":{\"lab_results\":null,\"diagnosis_records\":null}}";

        String json = "{\"name\":\"Raghul\",\"email\":\"jeyaraghul@gmail.com\",\"patientPhone\":\"+91989447288\",\"address\":{\"houseNo\":\"90/1\",\"street\":\"apparstreet\",\"city\":\"singapore\",\"state\":\"string\",\"zipCode\":\"string\"},\"gender\":\"string\",\"date_of_birth\":\"string\",\"emergencyContact\":{\"emergencyContactName\":\"string\",\"relationship\":\"string\",\"phone\":\"string\"},\"medicalRecord\":{\"lab_results\":[{\"labId\":\"string\",\"test\":\"string\",\"date\":\"string\"}],\"diagnosis_records\":[{\"date\":\"string\",\"type\":\"string\",\"notes\":\"string\",\"medications\":[{\"medication_name\":\"string\",\"dosage\":\"string\",\"medication_frequency\":\"string\",\"medication_start_date\":\"string\",\"medication_end_date\":\"string\"}]}]}}";
        PatientDetails response1 = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .when().post("/zaga/hospital/patientEnrollment").then().statusCode(200).extract()
                .as(PatientDetails.class);

        pateDetailsResponse = response1;

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
        System.out.println("--------appointment--------" + appointment);
        Appointment response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(appointment)
                .when().post("/zaga/hospital/bookAppointment").then().statusCode(200).extract().as(Appointment.class);
        System.out.println(response);

    }

    // @Test
    // @Order(6)
    // public void getMedicalRecordbyPatientId() {

    // MedicalRecord response = RestAssured.given()
    // .contentType(ContentType.JSON)
    // .accept(ContentType.JSON).pathParam("id", pateDetailsResponse.id)
    // .when().get("/zaga/hospital/patientservice/getMedicalRecord/{id}").then().statusCode(200).extract()
    // .as(MedicalRecord.class);

    // medicalRecordResponse = response;

    // // Not available check while booking
    // // Timeslot Limit full

    // }

    // @Test
    // @Order(7)
    // public void updateLabResultMedicalRecord() {

    // String json =
    // "{\"labId\":\"string\",\"test\":\"string\",\"date\":\"string\"}";
    // RestAssured.given()
    // .contentType(ContentType.JSON)
    // .accept(ContentType.JSON)
    // .body(json).pathParam("id", pateDetailsResponse.id)
    // .when().post("/zaga/hospital/patientservice/updateLabresults/{id}").then().statusCode(200);

    // }

    // @Test
    // @Order(8)
    // public void updateDiagnsisRecordMedicalRecord() {

    // String json =
    // "{\"date\":\"string\",\"type\":\"string\",\"notes\":\"string\",\"medications\":[{\"medication_name\":\"Aspirin\",\"dosage\":\"100mg\",\"medication_frequency\":\"string\",\"medication_start_date\":\"string\",\"medication_end_date\":\"string\"}]}";
    // RestAssured.given()
    // .contentType(ContentType.JSON)
    // .accept(ContentType.JSON)
    // .body(json).pathParam("id", pateDetailsResponse.id)
    // .when().post("/zaga/hospital/patientservice/updateDianoseRecord/{id}").then().statusCode(200);

    // }
}