package com.lab3.interpolationandapproximationofcurves.models;

import lombok.Data;

@Data
public class Curve {
    private int PointX1;
    private int PointY1;
    private int PointX2;
    private int PointY2;
    private int PointX3;
    private int PointY3;
    private int PointX4;
    private int PointY4;
    private Algorithm algorithm;
}