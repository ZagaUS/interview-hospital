package com.zaga.enity.doctor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {

    public LocalDate date;
    public LocalTime start_Time;
    public LocalTime end_Time;
    public List<Integer> time_slots;
}
