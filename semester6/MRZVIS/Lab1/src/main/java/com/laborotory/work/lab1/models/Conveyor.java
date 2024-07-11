/////////////////////////////////
// Лабораторная работа №1 по дисциплине МРЗвИС
// Выполнена студентами группы 121702 БГУИР Кривецкий А.Э., Костюков В.С.
// Вычисление произведения пары 6-разрядных чисел умножением с младших разрядов со сдвигом частичной суммы вправо
// 29.02.2024

package com.laborotory.work.lab1.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Conveyor {
    private String multiplicand;
    private List<String> multipliers;
    private String partialSum;
    private String partialProduct;
    private int stage;

    public static Conveyor addToConveyor(Pair pairOfNumbers) {
            return new Conveyor(pairOfNumbers.getFirstNumber() + "0".repeat(6),
                    Arrays.stream(pairOfNumbers.getSecondNumber().split("")).collect(Collectors.toList()),
                    "0".repeat(12),
                    "0".repeat(12),
                    0);
    }


}