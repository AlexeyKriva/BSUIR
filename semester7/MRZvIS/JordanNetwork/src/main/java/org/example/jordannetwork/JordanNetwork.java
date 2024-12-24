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
    private final int HIDDEN_NEURONES = 5;
    private final int OUTPUT_NEURONES = 1;
    private final int NORMAL_COEF = 100;
    private final int MEMORY_NEURONES = 1;
    private final int LEFT_BOARD = 3;
    private final int RIGHT_BOARD = 6;
    private final int HORIZONTAL_LAYERS = 2;
    private final int INPUT_NEURONES = RIGHT_BOARD - LEFT_BOARD + 1;
    private final double MAX_ERROR = 0.00001;
    private final double LEARNING_RATE = 0.1;

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

    public Map<Double, Double> train() {
        List<Double> sequence = sequenceGenerator.generateFibonacciByIndices(LEFT_BOARD, RIGHT_BOARD).stream()
                .map(Long::doubleValue)
                .map(num -> num / NORMAL_COEF)
                .collect(Collectors.toCollection(ArrayList::new));

        initMemory(sequence);

        List<List<Double>> sequenceAndMemory = new ArrayList<>();
        sequenceAndMemory.add(sequence);

        System.out.println("sequenceAndMemory:");
        sequenceAndMemory.stream().forEach(list -> {
            list.stream().forEach(element -> System.out.print(element + "\t"));
            System.out.println();
        });

        double nextNumber = sequenceGenerator.generateFibonacciByIndices(RIGHT_BOARD + 1, RIGHT_BOARD + 1).stream()
                .map(Long::doubleValue)
                .toList().get(0);

        initWeights();

        System.out.println("Next number: " + nextNumber);

        double currentError = 1.0;

        List<List<List<Double>>> predicted = new ArrayList<>();

        while (currentError > MAX_ERROR) {
            predicted = propagateForward(sequenceAndMemory);

            System.out.println("Hidden");
            predicted.get(0).stream().forEach(list -> {
                list.stream().forEach(element -> System.out.print(element + "\t"));
                System.out.println();
            });

            System.out.println("Output:");
            predicted.get(1).stream().forEach(list -> {
                list.stream().forEach(element -> System.out.print(element + "\t"));
                System.out.println();
            });

            currentError = propagateBackward(nextNumber, predicted.get(0), predicted.get(1), sequenceAndMemory);
            sequenceAndMemory.get(0).set(sequenceAndMemory.get(0).size() - 1, predicted.get(1).get(0).get(0));
        }

        return Map.of(nextNumber, predicted.get(1).get(0).get(0));
    }

    public List<List<List<Double>>> propagateForward(List<List<Double>> sequenceAndMemory) {
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

        List<List<Double>> newWeightsLayer1 = subtractMatrix(
                weights.get(0), multiplyByNumber(
                        multiplyMatrix(
                                transposeMatrix(sequenceAndMemory), hiddenErrors
                        ), LEARNING_RATE
                )
        );

        weights.set(0, newWeightsLayer1);
        weights.set(1, newWeightsLayer2);

        return outputErrors.stream()
                .mapToDouble(row -> row.stream()
                        .mapToDouble(val -> Math.pow(val, 2))
                        .sum())
                .sum();
    }

    public double arsinh(double x) {
        return Math.log(x + Math.sqrt(x * x + 1));
    }

    public double darsinh(double x) {
        return 1.0 / Math.sqrt(1 + x * x);
    }
}