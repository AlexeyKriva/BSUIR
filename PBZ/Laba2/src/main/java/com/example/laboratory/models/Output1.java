package com.example.laboratory.models;

import lombok.Data;

@Data
public class Output1 {
    private int isId;
    private String isName;
    private String model;
    private String acquisitionDate;
    private int isId1;
    private String isName1;
    private String model1;
    private String acquisitionDate1;
    private String fullNameUnit;

    @Override
    public String toString() {
        return "Output1{" +
                "isId=" + isId +
                ", isName='" + isName + '\'' +
                ", model='" + model + '\'' +
                ", acquisitionDate='" + acquisitionDate + '\'' +
                ", isId1=" + isId1 +
                ", isName1='" + isName1 + '\'' +
                ", model1='" + model1 + '\'' +
                ", acquisitionDate1='" + acquisitionDate1 + '\'' +
                ", unitName='" + fullNameUnit + '\'' +
                '}';
    }
}
