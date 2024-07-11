package com.laborotory.work.lab1.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private String result;
    private int numberResult;
    private int tact;

    public static String saveResults(Conveyor pairOfNumbers) {
        if (pairOfNumbers.getStage() > 6) {
            return pairOfNumbers.getPartialSum();
        }

        return "";
    }
}
