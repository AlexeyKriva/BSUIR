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
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RelaxationNetwork {
    private final MatrixService matrixService;
    private final TextService textService;

    private List<List<Integer>> weights;
    private List<List<List<Integer>>> picturesMatrixes = new ArrayList<>();
    private List<List<List<Integer>>> numbersMatrixes = new ArrayList<>();

    private final int MAX_ITER = 100;
    private final int BITE = 5;

    public List<List<Integer>> train(List<List<String>> pictures, List<String> numbers) {
        if (pictures.size() != numbers.size()) {
            throw new RuntimeException("Не все слова имеют ассоциации");
        }

        weights = matrixService.fillZeroMatrix(15, BITE);

        for (int i = 0; i < pictures.size(); i++) {
            List<String> picture = pictures.get(i);
            String number = numbers.get(i);

            picturesMatrixes.add(textService.encodePicture(picture));
            numbersMatrixes.add(textService.toBinary(number, BITE));

            weights = matrixService.sum(weights, matrixService.multiply(matrixService.transpose(picturesMatrixes.get(i)),
                    numbersMatrixes.get(i)));
        }

        return weights;
    }

    public Map<String, List<String>> forward(List<String> picture) {
        if (weights.isEmpty()) {
            throw new RuntimeException("Матрица весов не инициализирована!!!");
        }

        List<List<Integer>> previousPicture = new ArrayList<>();
        List<List<Integer>> previousNumber = new ArrayList<>();
        List<List<Integer>> lastPicture = textService.encodePicture(picture);
        List<List<Integer>> lastNumber = new ArrayList<>();

        int i = 0;
        while (i < MAX_ITER) {
            lastNumber = matrixService.multiplyWithActivation(lastPicture, weights);
            lastPicture = matrixService.multiplyWithActivation(lastNumber, matrixService.transpose(weights));
            lastNumber = matrixService.multiplyWithActivation(lastPicture, weights);

            System.out.println("STEP #" + (i++));

            if (!previousPicture.isEmpty() && !previousNumber.isEmpty() &&
                    isNetworkFindResult(previousPicture, previousNumber, lastPicture, lastNumber)) {
                matrixService.print(lastNumber);
                String targetNumber = textService.fromBinary(lastNumber);
                List<String> sourcePicture = textService.decodePicture(lastPicture);

                return Map.of("найденная цифра", List.of(targetNumber), "найденное изображение", sourcePicture,
                        "посланное изображение", picture, "количество итераций", List.of(String.valueOf(i)));
            }

            previousPicture = lastPicture;
            previousNumber = lastNumber;
        }

        return Map.of("ошибка", List.of("сеть не может найти ассоциацию"), "посланное изображение",
                textService.decodePicture(lastPicture));
    }

    public Map<String, List<String>> backward(String number) {
        if (weights.isEmpty()) {
            throw new RuntimeException("Матрица весов не инициализирована!!!");
        }

        List<List<Integer>> previousPicture = new ArrayList<>();
        List<List<Integer>> previousNumber = new ArrayList<>();
        List<List<Integer>> lastPicture = new ArrayList<>();
        List<List<Integer>> lastNumber = textService.toBinary(number, BITE);

        int i = 0;
        while (i < MAX_ITER) {
            lastPicture = matrixService.multiplyWithActivation(lastNumber, matrixService.transpose(weights));
            lastNumber = matrixService.multiplyWithActivation(lastPicture, weights);

            System.out.println("STEP #" + (i++));

            if (!previousPicture.isEmpty() && !previousNumber.isEmpty() &&
                    isNetworkFindResult(previousPicture, previousNumber, lastPicture, lastNumber)) {
                matrixService.print(lastNumber);
                String targetNumber = textService.fromBinary(lastNumber);
                List<String> sourcePicture = textService.decodePicture(lastPicture);

                return Map.of("найденное изображение", sourcePicture, "найденная цифра", List.of(targetNumber),
                        "посланная цифра", List.of(number), "количество итераций", List.of(String.valueOf(i)));
            }

            previousPicture = lastPicture;
            previousNumber = lastNumber;
        }

        return Map.of("ошибка", List.of("сеть не может найти ассоциацию"), "посланная цифра",
                List.of(textService.fromBinary(lastNumber)));
    }

    public boolean isNetworkFindResult(List<List<Integer>> previousPicture, List<List<Integer>> previousNumber,
                                       List<List<Integer>> lastPicture, List<List<Integer>> lastNumber) {
        int coincidenceRussian = 0;

        for (int i = 0; i < previousPicture.get(0).size(); i++) {
            if (previousPicture.get(0).get(i).equals(lastPicture.get(0).get(i))) {
                coincidenceRussian++;
            }
        }

        int coincidenceEnglish = 0;

        for (int i = 0; i < previousNumber.get(0).size(); i++) {
            if (previousNumber.get(0).get(i).equals(lastNumber.get(0).get(i))) {
                coincidenceEnglish++;
            }
        }

        return coincidenceRussian == lastPicture.get(0).size() &&
                coincidenceEnglish == lastNumber.get(0).size();
    }
}