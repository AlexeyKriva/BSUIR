/*
Лабораторная работа №2 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
Вариант 1: Реализовать модель сети Джордана с логарифмической функцией активации (гиперболический арксинус)
*/
package org.example.jordannetwork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.jordannetwork.MatrixService.*;

@Service
@RequiredArgsConstructor
public class JordanNetwork {
    //Services
    private final SequenceGenerator sequenceGenerator;


    //Constants
    private final int HIDDEN_NEURONES = 4;
    private final int OUTPUT_NEURONES = 1;
    private final int NORMAL_COEF = 10;
    private final int MEMORY_NEURONES = OUTPUT_NEURONES;
    private final int LEFT_BOARD = 0;
    private final int RIGHT_BOARD = 2;
    private final int HORIZONTAL_LAYERS = 2;
    private final int INPUT_NEURONES = RIGHT_BOARD - LEFT_BOARD + 1;
    private final double MAX_ERROR = 0.000001;
    private final double LEARNING_RATE = 0.0001;
    private final int NUMBERS_FOR_PREDICTION = RIGHT_BOARD - LEFT_BOARD + 1;
    private final int TOTAL_NUMBERS_FOR_PREDICTION = 3 * NUMBERS_FOR_PREDICTION;

    private List<List<List<Double>>> weights = new ArrayList<>();

    public void initMemory(List<Double> sequence) {
        for (int i = 0; i < OUTPUT_NEURONES; i++) {
            sequence.add(0.0);
        }
    }

    public void initWeights() {
        for (int layer = 0; layer < HORIZONTAL_LAYERS; layer++) {
            List<List<Double>> weightMatrix = new ArrayList<>();

            if (layer == 0) {
                for (int i = 0; i < INPUT_NEURONES + MEMORY_NEURONES; i++) {
                    weightMatrix.add(new ArrayList<>());

                    for (int j = 0; j < HIDDEN_NEURONES; j++) {
                        weightMatrix.get(i).add(roundRandomWeight());
                    }
                }
            } else {
                for (int i = 0; i < HIDDEN_NEURONES; i++) {
                    weightMatrix.add(new ArrayList<>());

                    for (int j = 0; j < OUTPUT_NEURONES; j++) {
                        weightMatrix.get(i).add(roundRandomWeight());
                    }
                }
            }
            weights.add(weightMatrix);
        }
    }

    public double roundRandomWeight() {
        Random random = new Random();

        double randomWeight = -1 + (1 - -1) * random.nextDouble();

        return roundToPrecision(randomWeight);
    }

    private double roundToPrecision(double value) {
        BigDecimal roundedValue = BigDecimal.valueOf(value);
        return roundedValue.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public Map<String, String> train() {
        List<Double> sequence = sequenceGenerator.generateFibonacciByIndices(LEFT_BOARD, RIGHT_BOARD).stream()
                .map(Long::doubleValue)
                .collect(Collectors.toCollection(ArrayList::new));

        List<Double> sourceSequence = new ArrayList<>(sequence);

        sequence.forEach(System.out::println);

        initMemory(sequence);

        List<List<Double>> sequenceAndMemory = new ArrayList<>();
        sequenceAndMemory.add(sequence);

        sequenceAndMemory.stream().forEach(list -> {
            list.stream().forEach(element -> System.out.print(element + "\t"));
            System.out.println();
        });

        List<Double> nextNumbers = sequenceGenerator.generateFibonacciByIndices(RIGHT_BOARD + 1, RIGHT_BOARD +
                        TOTAL_NUMBERS_FOR_PREDICTION).stream().map(Long::doubleValue).toList();

        initWeights();

        //System.out.println("Next number: " + nextNumber);

        int count = 0;

        List<Double> predictedNumbers = new ArrayList<>();

        for (int x = 0; x < TOTAL_NUMBERS_FOR_PREDICTION; x += NUMBERS_FOR_PREDICTION) {

            for (int i = x; i < x + NUMBERS_FOR_PREDICTION; i++) {
                double currentError = 1.0;
                List<List<List<Double>>> predicted = new ArrayList<>();

                while (currentError > MAX_ERROR) {
                    predicted = propagateForward(new ArrayList<>(sequenceAndMemory));

                    currentError = propagateBackward(nextNumbers.get(i), predicted.get(0), predicted.get(1), sequenceAndMemory);
                    sequenceAndMemory.get(0).set(sequenceAndMemory.get(0).size() - 1, predicted.get(1).get(0).get(0));
                    System.out.println("Count=" + (count++));
                    System.out.println("Current predicted=" + predicted.get(1).get(0).get(0));
                }

                predictedNumbers.add(predicted.get(1).get(0).get(0));

                for (int j = 1; j < sequenceAndMemory.get(0).size() - 1; j++) {
                    sequenceAndMemory.get(0).set(j - 1, sequenceAndMemory.get(0).get(j));
                }

                sequenceAndMemory.get(0).set(sequenceAndMemory.get(0).size() - 1, predicted.get(1).get(0).get(0));
            }
        }

        return result(sourceSequence, nextNumbers, predictedNumbers, count);
    }

    public List<List<List<Double>>> propagateForward(List<List<Double>> sequenceAndMemory) {
        sequenceAndMemory.set(0, sequenceAndMemory.get(0).stream()
                .map(num -> num / NORMAL_COEF).collect(Collectors.toCollection(ArrayList::new)));

        List<List<Double>> predictedHidden = multiplyMatrix(sequenceAndMemory, weights.get(0));

        for (int i = 0; i < predictedHidden.size(); i++) {
            for (int j = 0; j < predictedHidden.get(i).size(); j++) {
                predictedHidden.get(i).set(j, arsinh(predictedHidden.get(i).get(j)));
            }
        }

        List<List<Double>> predictedOutput = multiplyMatrix(predictedHidden, weights.get(1));

        for (int i = 0; i < predictedOutput.size(); i++) {
            for (int j = 0; j < predictedOutput.get(i).size(); j++) {
                predictedOutput.get(i).set(j, arsinh(predictedOutput.get(i).get(j)));
            }
        }

        predictedOutput.get(0).set(0, predictedOutput.get(0).get(0) * NORMAL_COEF);

        return List.of(predictedHidden, predictedOutput);
    }

    public double propagateBackward(double nextNumber, List<List<Double>> predictedHidden,
                                    List<List<Double>> predictedOutput, List<List<Double>> sequenceAndMemory) {
        List<List<Double>> outputErrors = subtractMatrix(predictedOutput, List.of(List.of(nextNumber)));

        List<List<Double>> newWeightsLayer2 = subtractMatrix(
                weights.get(1), transposeMatrix(
                        multiplyByNumber(
                                multiplyMatrix(
                                        outputErrors, predictedHidden
                                ), LEARNING_RATE
                        )
                )
        );

        List<List<Double>> hiddenErrors = multiplyMatrix(outputErrors, transposeMatrix(
                newWeightsLayer2
        ));

//        System.out.println("Hidden errors:");
//        hiddenErrors.forEach(errors -> {
//            errors.stream().forEach(num -> System.out.print(num + " "));
//            System.out.println();
//        });

        List<List<Double>> newWeightsLayer1 = subtractMatrix(
                weights.get(0), multiplyByNumber(
                        multiplyMatrix(
                                transposeMatrix(sequenceAndMemory), hiddenErrors
                        ), LEARNING_RATE
                )
        );

        weights.set(0, newWeightsLayer1);
        weights.set(1, newWeightsLayer2);

//        System.out.println("Weights[0]");
//        for (int i = 0; i < weights.get(0).size(); i++) {
//            for (int j = 0; j < weights.get(0).get(i).size(); j++) {
//                System.out.print(weights.get(0).get(i).get(j) + " ");
//            }
//            System.out.println();
//        }

        return outputErrors.stream()
                .mapToDouble(row -> row.stream()
                        .mapToDouble(val -> Math.pow(val, 2))
                        .sum())
                .sum();
    }

    public double arsinh(double x) {
        return Math.log(x + Math.sqrt(x * x + 1));
    }

    public double dArsinh(double x) {
        return 1 / Math.sqrt(x * x + 1);
    }

    public Map<String, String> result(List<Double> sequence, List<Double> nextNumbers, List<Double> predictedNumbers, int itCount) {
        Map<String, String> result = new LinkedHashMap<>();

        int numberOfWindow = 1;

        StringBuilder window = new StringBuilder();

        for (Double number: sequence) {
            window.append(number).append(" ");
        }

        result.put("окно " + numberOfWindow, window.toString());

        window.setLength(0);
        StringBuilder references = new StringBuilder();
        StringBuilder predicted = new StringBuilder();
        for (int i = 0; i < nextNumbers.size(); i++) {
            if (i != 0 && i % NUMBERS_FOR_PREDICTION == 0) {
                result.put("ожидаемые значения " + numberOfWindow, references.toString());
                result.put("предсказанные значения " + numberOfWindow, predicted.toString());
                result.put("окно " + (++numberOfWindow), references.toString());
                references.setLength(0);
                predicted.setLength(0);
            }

            references.append(nextNumbers.get(i)).append(" ");
            predicted.append(predictedNumbers.get(i)).append(" ");
        }

        result.put("ожидаемые значения " + numberOfWindow, references.toString());
        result.put("предсказанные значения " + numberOfWindow, predicted.toString());

        result.put("количество итераций", String.valueOf(itCount));

        return result;
    }
}