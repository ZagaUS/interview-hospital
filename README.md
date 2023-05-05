# Hospital Service

## Services

### Appointment Services

This service allows patients to book appointments with doctors. Patients can search for doctors based on their specialty, availability criteria. Once a patient selects a doctor and an appointment time, the system sends a confirmation to the patient and the doctor.

### Pharmacy Services

Patient Service: This service allows doctors to view and manage patient records. Doctors can view the patient's medical history, update medical records, and prescribe medications to patients.

### Notification Services

Notification Service: This service enables the system to send notifications to patients, doctors, and other staff members. The notification service can send sms notifications to patients about their appointments, medication shipments, and other related.This service Uses Twilio to send notifications sms .

## Architecture

![Architecture ](applicationdocs\Infrastructure.png)

## Technical Stack

Quarkus - BackEnd .<br>
Postgres - Database .<br>
Kafka - EventBus .<br>
Docker - Deployment .<br>

## Deployment

Used Docker Compose to run full stack as Docker Container

First Package the Application

```shell script
./mvnw package
```

Docker compose

```shell script
docker compose up
```
