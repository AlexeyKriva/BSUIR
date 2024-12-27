/*
Лабораторная работа №3 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
Вариант 1: Реализовать модель сети Джордана с логарифмической функцией активации (гиперболический арксинус)
*/
package org.example.jordannetwork.utils;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SequenceGenerator {
    public List<Double> generateDoubleSequenceByNumberAndIndexes(int number, int startIndex, int endIndex) {
        switch (number) {
            case 0 -> {
                return generateFibonacciByIndices(startIndex, endIndex).stream()
                        .map(Long::doubleValue)
                        .collect(Collectors.toCollection(ArrayList::new));
            }
            default -> throw new IllegalArgumentException(number + " is not available sequence number.");
        }
    }

    public List<Long> generateFibonacciByIndices(int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex < 0) {
            throw new IllegalArgumentException("Indices must be non-negative.");
        }
        if (startIndex > endIndex) {
            throw new IllegalArgumentException("Start index cannot be greater than end index.");
        }

        List<Long> fibonacciNumbers = new ArrayList<>();
        long a = 0, b = 1;

        for (int i = 0; i <= endIndex; i++) {
            if (i >= startIndex) {
                fibonacciNumbers.add(a);
            }
            long next = a + b;
            a = b;
            b = next;
        }

        return fibonacciNumbers;
    }

    public List<Long> generate() {
        List<Long> numbers = new ArrayList<>();
        numbers.add(1L);
        numbers.add(4L);
        numbers.add(9L);
        numbers.add(16L);

        return numbers;
    }
}
