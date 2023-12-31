package com.example.laboratory.models;

import lombok.Data;

import java.util.List;

@Data
public class ShiftSchedule {
    private List<String> workDays;
    private List<String> startTime;
    private List<String> finishTime;
}