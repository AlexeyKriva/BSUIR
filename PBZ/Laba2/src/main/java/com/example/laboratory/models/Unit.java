package com.example.laboratory.models;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

@Data
public class Unit {
    private int unitNumber;
    private String fullNameUnit;
    private String shortNameUnit;
}
