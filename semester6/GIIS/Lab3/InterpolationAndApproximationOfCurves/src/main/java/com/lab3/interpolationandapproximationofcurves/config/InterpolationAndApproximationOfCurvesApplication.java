package com.lab3.interpolationandapproximationofcurves.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lab3.interpolationandapproximationofcurves")
public class InterpolationAndApproximationOfCurvesApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterpolationAndApproximationOfCurvesApplication.class, args);
    }

}
