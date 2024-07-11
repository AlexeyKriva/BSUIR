package com.example.laboratory.models;

import lombok.Data;

import java.util.Map;
import java.util.List;

@Data
public class EmployeeTimeRecording {
    Map<Employee, List<List<String>>> attendanceWork;
}
