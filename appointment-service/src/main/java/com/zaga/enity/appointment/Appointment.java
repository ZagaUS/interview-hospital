package com.zaga.enity.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zaga.enity.doctor.DoctorShedule;
import com.zaga.enity.patient.PatientDetails;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Appointment extends PanacheEntity {
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // private Long id;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    public LocalDate date;

    public Long doctor_id;
    public Long patient_id;
    public String timeSlot;
    public Integer time_slot_id;

    public String appointment_status;

    public String appointment_type;
    public String appointment_reason;
}
