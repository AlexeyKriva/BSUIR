/*
Лабораторная работа №2 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
Вариант 2: Реализовать модель двунаправленной ассоциативной памяти
*/
package com.example.relaxationnetwork.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TextService {
    private final static int LETTER_BITS = 11;

    public List<List<Integer>> encodePicture(List<String> picture) {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>());

        for (int i = 0; i < picture.get(0).length(); i++) {
            for (int j = 0; j < picture.size(); j++) {
                matrix.get(0).add(picture.get(j).charAt(i) == '+' ? 1: -1);
            }
        }

        return matrix;
    }

    public List<String> decodePicture(List<List<Integer>> matrix) {
        List<String> picture = new ArrayList<>();


        for (int i = 0; i < matrix.get(0).size() / 3; i++) {
            StringBuilder str = new StringBuilder();

            str.append(matrix.get(0).get(i) == 1 ? "+" : "-");
            str.append(matrix.get(0).get(i + 5) == 1 ? "+" : "-");
            str.append(matrix.get(0).get(i + 10) == 1 ? "+" : "-");
            picture.add(str.toString());
        }

        return picture;
    }

    public List<List<Integer>> toBinary(String number, int bites) {
        String binary = Integer.toBinaryString(Integer.parseInt(number));
        binary = "0".repeat(bites - binary.length()) + binary;

        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>());

        for (char num: binary.toCharArray()) {
            matrix.get(0).add(num == '1'? 1: -1);
        }

        return matrix;
    }

    public String fromBinary(List<List<Integer>> matrix) {
        StringBuilder str = new StringBuilder();

        for (List<Integer> row: matrix) {
            for (int number: row) {
                str.append(number == 1 ? "1": "0");
            }
        }

        System.out.println(Integer.parseInt(str.toString(), 2));

        return String.valueOf(Integer.parseInt(str.toString(), 2));
    }
}