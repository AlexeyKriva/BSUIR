package com.example.laboratory.models;

import lombok.Data;

import java.util.List;

@Data
public class HelpShiftSchedule {
    private String emailAddress;
    private String workDays;
    private String startTime;
    private String finishTime;
}