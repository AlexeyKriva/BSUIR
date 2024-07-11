/////////////////////////////////
// Лабораторная работа №1 по дисциплине МРЗвИС
// Выполнена студентами группы 121702 БГУИР Кривецкий А.Э., Костюков В.С.
// Вычисление произведения пары 6-разрядных чисел умножением с младших разрядов со сдвигом частичной суммы вправо
// 29.02.2024

package com.laborotory.work.lab1.models;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import static com.laborotory.work.lab1.models.Consts.*;
import static com.laborotory.work.lab1.service.BinaryNumberCalculatorService.*;
import java.util.regex.*;

import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pair {
    @Pattern(regexp = MODEL_OF_NUMBER, message = HINT)
    private String firstNumber;
    @Pattern(regexp = MODEL_OF_NUMBER, message = HINT)
    private String secondNumber;
    private int intFirstNumber;
    private int intSecondNumber;

    public Pair checkSystem(){
        if (firstNumber.length() < 6) {
            firstNumber = checkNumber(firstNumber);
        }

        if (secondNumber.length() < 6) {
            secondNumber = checkNumber(secondNumber);
        }

        return new Pair(firstNumber, secondNumber, Integer.parseInt(firstNumber, 2), Integer.parseInt(secondNumber, 2));
    }

    private String checkNumber(String number) {
        int size = number.length();
        for (int i = 0; i < 6 - size; i++) {
            number = "0" + number;
        }

        return number;
    }
}