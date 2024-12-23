package org.example.jordannetwork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.example.jordannetwork.MatrixService.fillZeroMatrix;
import static org.example.jordannetwork.MatrixService.initLayer;

@Service
@RequiredArgsConstructor
public class JordanNetwork {
    //Services
    private final SequenceGenerator sequenceGenerator;



    //Constants
    private final int NUMBER_OF_LAYERS = 4;
    private final int HIDDEN_NEURONES = 6;
    private final int OUTPUT_NEURONES = 1;
    private final int NORMAL_COEF = 100;
    private final int LEFT_BOARD = 0;
    private final int RIGHT_BOARD = 4;
    private final double MAX_ERROR = 0.00001;

    private List<List<Double>> layers = new ArrayList<>();
    private List<List<List<Double>>> weights = new ArrayList<>();
    private List<List<List<Double>>> dw = new ArrayList<>();

    public Map<Double, Double> train() {
        List<Double> sequence = sequenceGenerator.generateFibonacciByIndices(LEFT_BOARD, RIGHT_BOARD).stream()
                .map(Long::doubleValue)
                .toList();

        List<Double> result = sequenceGenerator.generateFibonacciByIndices(RIGHT_BOARD + 1, RIGHT_BOARD + 1).stream()
                .map(Long::doubleValue)
                .toList();

        double currentError = 1.0;

//        while (currentError > MAX_ERROR) {
//
//        }

        return Map.of(0.0, 0.0);
    }

    public void init() {
        layers.add(initLayer(NUMBER_OF_LAYERS + 1 + OUTPUT_NEURONES));
        layers.add(initLayer(HIDDEN_NEURONES));
        layers.add(initLayer(OUTPUT_NEURONES));

        for (int i = 0; i < 2; i++) {
            weights.add(fillZeroMatrix(layers.get(i).size(), layers.get(i + 1).size()));
            dw.add(fillZeroMatrix(layers.get(i).size(), layers.get(i + 1).size())); // dw инициализируется нулями
        }
    }

    public void genWeights() {
        Random random = new Random();

        for (int i = 0; i < weights.size(); i++) {
            for (int j = 0; j < weights.get(i).size(); j++) {
                for (int k = 0; k < weights.get(i).get(j).size(); k++) {
                    weights.get(i).get(j).set(k, 2 * random.nextDouble() - 1);
                }
            }
        }
    }
}