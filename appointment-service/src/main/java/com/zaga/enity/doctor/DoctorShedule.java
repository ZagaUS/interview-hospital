package com.zaga.enity.doctor;

import java.util.List;

import com.zaga.enity.appointment.Appointment;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DoctorShedule extends PanacheEntity {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // public Long id;
    public String doctor_name;
    public String specialty;
    public String email;
    public String phone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private List<Appointment> appointments;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    public List<Schedule> schedules;
}
