/*
Лабораторная работа №3 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
Вариант 1: Реализовать модель сети Джордана с логарифмической функцией активации (гиперболический арксинус)
*/
package org.example.jordannetwork.network;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.jordannetwork.utils.MatrixService.*;
import static org.example.jordannetwork.utils.ResultService.buildResult;
import static org.example.jordannetwork.utils.SimpleMathService.roundToPrecision;

import org.example.jordannetwork.utils.SequenceGenerator;


@Service
@RequiredArgsConstructor
public class JordanNetwork {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    //Services
    private final SequenceGenerator sequenceGenerator;

    //Constants
    private final int HIDDEN_NEURONES = 4;
    private final int OUTPUT_NEURONES = 1;
    private final int NORMAL_COEF = 10;
    private final int MEMORY_NEURONES = OUTPUT_NEURONES;
    private final int LEFT_SEQUENCE_BOARD = 0;
    private final int RIGHT_SEQUENCE_BOARD = 2;
    private final int NUMBER_OF_WEIGHT_MATRICES = 2;
    private final int INPUT_NEURONES = RIGHT_SEQUENCE_BOARD - LEFT_SEQUENCE_BOARD + 1;
    private final double MAX_ERROR = 0.000001;
    private final double LEARNING_RATE = 0.0001;
    private final int NUMBERS_FOR_PREDICTION = RIGHT_SEQUENCE_BOARD - LEFT_SEQUENCE_BOARD + 1;
    private final int TOTAL_NUMBERS_FOR_PREDICTION = 3 * NUMBERS_FOR_PREDICTION;

    private List<List<List<Double>>> weights = new ArrayList<>();

    public void initMemory(List<Double> sequence) {
        for (int i = 0; i < OUTPUT_NEURONES; i++) {
            sequence.add(0.0);
        }
    }

    public void initWeights() {
        for (int layer = 0; layer < NUMBER_OF_WEIGHT_MATRICES; layer++) {
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

    public Map<String, String> train() {
        initWeights();

        List<Double> sequence = sequenceGenerator.generateDoubleSequenceByNumberAndIndexes(0,
                LEFT_SEQUENCE_BOARD, RIGHT_SEQUENCE_BOARD);

        initMemory(sequence);

        List<List<Double>> sequenceAndMemory = new ArrayList<>();
        sequenceAndMemory.add(sequence);

        List<Double> nextNumbers = sequenceGenerator.generateDoubleSequenceByNumberAndIndexes(0,
                RIGHT_SEQUENCE_BOARD + 1, RIGHT_SEQUENCE_BOARD +
                        TOTAL_NUMBERS_FOR_PREDICTION);

        int count = 0;

        List<Double> predictedNumbers = new ArrayList<>();

        List<Double> copiedSequence = new ArrayList<>(sequence);

        for (int x = 0; x < TOTAL_NUMBERS_FOR_PREDICTION; x += NUMBERS_FOR_PREDICTION) {

            for (int i = x; i < x + NUMBERS_FOR_PREDICTION; i++) {
                double currentError = 1.0;
                List<List<List<Double>>> predicted = new ArrayList<>();

                while (currentError > MAX_ERROR) {
                    predicted = propagateForward(new ArrayList<>(sequenceAndMemory));

                    currentError = propagateBackward(nextNumbers.get(i), predicted.get(0), predicted.get(1), sequenceAndMemory);
                    sequenceAndMemory.get(0).set(sequenceAndMemory.get(0).size() - 1, predicted.get(1).get(0).get(0));
                    LOGGER.info("Step = {}", (count++));
                    LOGGER.info("Current prediction = {}", predicted.get(1).get(0).get(0));
                    //System.out.println("Count=" + (count++));
                    //System.out.println("Current predicted=" + predicted.get(1).get(0).get(0));
                }

                predictedNumbers.add(predicted.get(1).get(0).get(0));

                for (int j = 1; j < sequenceAndMemory.get(0).size() - 1; j++) {
                    sequenceAndMemory.get(0).set(j - 1, sequenceAndMemory.get(0).get(j));
                }

                sequenceAndMemory.get(0).set(sequenceAndMemory.get(0).size() - 1, predicted.get(1).get(0).get(0));
            }
        }

        return buildResult(copiedSequence, nextNumbers, predictedNumbers, count, NUMBERS_FOR_PREDICTION);
    }

    public List<List<List<Double>>> propagateForward(List<List<Double>> sequenceAndMemory) {
        sequenceAndMemory.set(0, sequenceAndMemory.get(0).stream()
                .map(num -> num / NORMAL_COEF).collect(Collectors.toCollection(ArrayList::new)));

        List<List<Double>> predictedHidden = multiplyMatrix(sequenceAndMemory, weights.get(0));

        for (int i = 0; i < predictedHidden.size(); i++) {
            for (int j = 0; j < predictedHidden.get(i).size(); j++) {
                predictedHidden.get(i).set(j, activation(predictedHidden.get(i).get(j)));
            }
        }

        List<List<Double>> predictedOutput = multiplyMatrix(predictedHidden, weights.get(1));

        for (int i = 0; i < predictedOutput.size(); i++) {
            for (int j = 0; j < predictedOutput.get(i).size(); j++) {
                predictedOutput.get(i).set(j, activation(predictedOutput.get(i).get(j)));
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

    public double activation(double x) {
        return Math.log(x + Math.sqrt(x * x + 1));
    }

    public double activationDerivative(double x) {
        return 1 / Math.sqrt(x * x + 1);
    }
}