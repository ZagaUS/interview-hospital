package com.zaga.enity.doctor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties({ "time_slots" })
public class Schedule extends PanacheEntityBase {

    @Id
    public LocalDate date;
    @JsonFormat(pattern = "HH:mm")
    public LocalTime start_Time;
    @JsonFormat(pattern = "HH:mm")
    public LocalTime end_Time;
    @Builder.Default
    @JsonIgnore
    public List<Integer> time_slots = Arrays.asList(0, 0, 0, 0, 0, 0);

}
