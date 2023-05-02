package com.zaga.apis;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@QuarkusTest
public class PatientEnrollmentApiTest {

    @Test
    public void testEnrollment() {

        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/zaga/hospital/patientEnrollment").then()
                .statusCode(204);
    }

}
