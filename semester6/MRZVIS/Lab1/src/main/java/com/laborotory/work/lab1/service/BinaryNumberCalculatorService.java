/////////////////////////////////
// Лабораторная работа №1 по дисциплине МРЗвИС
// Выполнена студентами группы 121702 БГУИР Кривецкий А.Э., Костюков В.С.
// Вычисление произведения пары 6-разрядных чисел умножением с младших разрядов со сдвигом частичной суммы вправо
// 29.02.2024

package com.laborotory.work.lab1.service;

import com.laborotory.work.lab1.models.Conveyor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinaryNumberCalculatorService {
    public List<Conveyor> workOfConveyor(List<Conveyor> conveyor) {
        for (int i = 0; i < conveyor.size(); i++) {
            conveyor.set(i, multiplication(conveyor.get(i)));
        }

        return conveyor;
    }

    public static Conveyor multiplication(Conveyor conveyor) {
        String multiplicand = conveyor.getMultiplicand();
        List<String> multipliers = conveyor.getMultipliers();
        StringBuilder currentParticalProduct = new StringBuilder();
        StringBuilder currentParticalSum = new StringBuilder(conveyor.getPartialSum());
        int stage = conveyor.getStage();

        if (stage >= 6) {
            return new Conveyor(multiplicand, multipliers, String.valueOf(currentParticalSum), String.valueOf(currentParticalProduct), ++stage);
        }
        char c = multipliers.get(multipliers.size() - 1).charAt(0);
        multipliers.remove(multipliers.size() - 1);
        for (int j = multiplicand.length() - 1; j >= 0; j--) {
            currentParticalProduct.append(bitMultiplication(multiplicand.charAt(j), c));
        }
        currentParticalProduct = currentParticalProduct.reverse();
        currentParticalSum = binarySum(String.valueOf(currentParticalSum), String.valueOf(currentParticalProduct));

        return new Conveyor(multiplicand, multipliers, String.valueOf(currentParticalSum), String.valueOf(currentParticalProduct), ++stage);
    }

    public List<Conveyor> makeShift(List<Conveyor> conveyor) {
        int i = 0;
        for (Conveyor pairOfNumbers: conveyor) {
            String multiplicand = pairOfNumbers.getMultiplicand();
            String currentParticalSum = pairOfNumbers.getPartialSum();
            if (currentParticalSum.length() > multiplicand.length()) {
                conveyor.set(i, new Conveyor(multiplicand, conveyor.get(i).getMultipliers(),
                        currentParticalSum.substring(0, currentParticalSum.length() - 1),
                        conveyor.get(i).getPartialProduct(), conveyor.get(i).getStage()));
            } else {
                conveyor.set(i, new Conveyor(multiplicand, conveyor.get(i).getMultipliers(),
                        "0" + currentParticalSum.substring(0, currentParticalSum.length() - 1),
                        conveyor.get(i).getPartialProduct(), conveyor.get(i).getStage()));
            }
            i++;
        }

        return conveyor;
    }

    private static String bitMultiplication(char firstBit, char secondBit) {
        if (secondBit == '0') {
            return "0";
        }

        return String.valueOf(firstBit);
    }

    private static StringBuilder binarySum(String firstNumber, String secondNumber) {
        StringBuilder resultOfSum = new StringBuilder();
        boolean haveTransfer = false;
        for (int i = firstNumber.length() - 1; i >= 0; i--) {
            String sumOfBits = bitSum(firstNumber.charAt(i), secondNumber.charAt(i), haveTransfer);
            if (sumOfBits.length() == 1) {
                haveTransfer = false;
                resultOfSum.append(sumOfBits);
            } else {
                resultOfSum.append(sumOfBits.charAt(1));
                haveTransfer = true;
            }
        }
        if (haveTransfer) {
            resultOfSum.append("1");
        }
        return resultOfSum.reverse();
    }

    private static String bitSum(char firstBit, char secondBit, boolean haveTransfer) {
        if (firstBit == '0' && secondBit == '0' && !haveTransfer) {
            return "0";
        } else if ((firstBit == '1' && secondBit == '0' && !haveTransfer) ||
                (firstBit == '0' && secondBit == '1' && !haveTransfer) ||
                (firstBit == '0' && secondBit == '0' && haveTransfer)) {
            return "1";
        } else if (firstBit == '1' && secondBit == '1' && haveTransfer) {
            return "11";
        }

        return "10";
    }
}