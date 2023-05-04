package com.zaga;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaga.enity.patient.MedicalRecord;
import com.zaga.enity.patient.PatientDetails;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientService {

    private static ObjectMapper mapper;
    private PatientDetails pateDetailsResponse;
    private MedicalRecord medicalRecordResponse;

    // enroll patient

    @Test
    @Order(1)
    public void enrollPatient() {

        String json = "{\"name\":\"Raghul\",\"email\":\"string\",\"patientPhone\":\"string\",\"address\":{\"houseNo\":\"string\",\"street\":\"string\",\"city\":\"string\",\"state\":\"string\",\"zipCode\":\"string\"},\"gender\":\"string\",\"date_of_birth\":\"string\",\"emergencyContact\":{\"emergencyContactName\":\"string\",\"relationship\":\"string\",\"phone\":\"string\"},\"medicalRecord\":{\"lab_results\":null,\"diagnosis_records\":null}}";
        PatientDetails response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .when().post("/zaga/hospital/patientEnrollment").then().statusCode(200).extract()
                .as(PatientDetails.class);

        pateDetailsResponse = response;

        System.out.println(pateDetailsResponse);

    }

    // get medical record by person id
    @Test
    @Order(2)
    public void getMedicalRecordbyPatientId() {

        MedicalRecord response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).pathParam("id", pateDetailsResponse.id)
                .when().get("/zaga/hospital/patientservice/getMedicalRecord/{id}").then().statusCode(200).extract()
                .as(MedicalRecord.class);

        medicalRecordResponse = response;

    }

    // get medical record by medical record id
    // @Test
    // @Order(3)
    // public void getMedicalRecordbymedicalRecordId() {

    // RestAssured.given()
    // .contentType(ContentType.JSON)
    // .accept(ContentType.JSON).pathParam("id", doctorSheduleResponse.id)
    // .when().get("/zaga/hospital/doctor/getById/{id}").then().statusCode(200).extract()
    // .as(DoctorShedule.class);

    // }

    // update labresult by medicalid (whenever labresult is created it should be
    // added in person list)
    @Test
    @Order(3)
    public void updateLabResultMedicalRecord() {

        String json = "{\"labId\":\"string\",\"test\":\"string\",\"date\":\"string\"}";
        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json).pathParam("id", pateDetailsResponse.id)
                .when().post("/zaga/hospital/patientservice/updateLabresults/{id}").then().statusCode(200);

    }

    // update diagnosis by medicalid (whenever diagnosis is created it should be
    // added in person list) it will create parmacy event
    @Test
    @Order(4)
    public void updateDiagnsisRecordMedicalRecord() {

        String json = "{\"date\":\"string\",\"type\":\"string\",\"notes\":\"string\",\"medications\":[{\"medication_name\":\"string\",\"dosage\":\"string\",\"medication_frequency\":\"string\",\"medication_start_date\":\"string\",\"medication_end_date\":\"string\"}]}";
        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json).pathParam("id", pateDetailsResponse.id)
                .when().post("/zaga/hospital/patientservice/updateDianoseRecord/{id}").then().statusCode(200);

    }

}
