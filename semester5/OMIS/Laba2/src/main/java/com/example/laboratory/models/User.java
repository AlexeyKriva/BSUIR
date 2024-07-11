package com.example.laboratory.models;

import lombok.Data;

import java.util.List;

@Data
abstract public class User {
    private UserProfile userProfile;
    private ShiftSchedule shiftSchedule;
    private EmployeeTimeRecording employeeTimeRecording;
    private List<Penalty> recoveries;
}
