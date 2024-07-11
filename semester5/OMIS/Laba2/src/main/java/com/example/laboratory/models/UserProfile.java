package com.example.laboratory.models;

import lombok.Data;

import java.util.Date;

@Data
public class UserProfile {
    private String fullName;
    private String education;
    private String emailAddress;
    private String birthdayDate;
    private String post;
    private String isPassword;
}
