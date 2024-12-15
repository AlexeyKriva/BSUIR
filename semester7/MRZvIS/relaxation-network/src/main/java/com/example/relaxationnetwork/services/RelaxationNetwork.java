package com.example.relaxationnetwork.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelaxationNetwork {
    private final MatrixService matrixService;

    @Autowired
    public RelaxationNetwork(MatrixService matrixService) {
        this.matrixService = matrixService;
        weights = matrixService.fillMatrix(rows, cols);
    }

    private static final int rows = 2;
    private static final int cols = 2;

    private List<List<Double>> weights;

    public double activation(double weightedSum) {
        return (Math.pow(Math.E, weightedSum) - Math.pow(Math.E, -weightedSum)) /
                Math.pow(Math.E, weightedSum) + Math.pow(Math.E, -weightedSum);
    }

    public List<List<Double>> train(List<List<Double>> input, List<List<Double>> output) {
        if (input.get(0).size() != output.size()) {
            throw new RuntimeException("Входная и выходная матрица не могут быть перемножены.");
        }

        weights = matrixService.multiply(input, output);

        return weights;
    }

    public List<List<Double>> forward(List<List<Double>> input) {
        return multiplyWithActivation(input, weights);
    }

    public List<List<Double>> backward(List<List<Double>> output) {
        return multiplyWithActivation(output, matrixService.transpose(weights));
    }

    public List<List<Double>> multiplyWithActivation(List<List<Double>> matrixA, List<List<Double>> matrixB) {
        List<List<Double>> outputMatrix = matrixService.fillMatrix(matrixA.size(), matrixB.get(0).size());

        for (int i = 0; i < matrixA.size(); i++) {
            for (int j = 0; j < matrixB.get(0).size(); j++) {
                for (int k = 0; k < matrixA.get(0).size(); k++) {
                    outputMatrix.get(i).set(j, outputMatrix.get(i).get(j) + matrixA.get(i).get(k) *
                            matrixB.get(k).get(j));
                }
                outputMatrix.get(i).set(j, activation(outputMatrix.get(i).get(j)));
            }
        }

        return outputMatrix;
    }
}