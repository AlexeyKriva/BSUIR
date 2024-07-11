package com.example.laboratory.models;

import lombok.Data;

import java.util.List;

@Data
public class Employer extends User{
    List<ShiftSchedule> shiftSchedules;
    List<Employee> employees;
}
