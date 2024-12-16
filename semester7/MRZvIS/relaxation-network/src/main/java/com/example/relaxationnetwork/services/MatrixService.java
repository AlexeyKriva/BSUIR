package com.example.relaxationnetwork.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MatrixService {
    public List<List<Integer>> multiply(List<List<Integer>> matrixA, List<List<Integer>> matrixB) {
        List<List<Integer>> outputMatrix = fillMatrix(matrixA.size(), matrixB.get(0).size());

        for (int i = 0; i < matrixA.size(); i++) {
            for (int j = 0; j < matrixB.get(0).size(); j++) {
                for (int k = 0; k < matrixA.get(0).size(); k++) {
                    outputMatrix.get(i).set(j, outputMatrix.get(i).get(j) + matrixA.get(i).get(k) *
                            matrixB.get(k).get(j));
                }
            }
        }

        return outputMatrix;
    }

    public List<List<Integer>> fillMatrix(int rows, int cols) {
        List<List<Integer>> outputMatrix = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            outputMatrix.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                outputMatrix.get(i).add(0);
            }
        }

        return outputMatrix;
    }

    public List<List<Integer>> transpose(List<List<Integer>> matrix) {
        List<List<Integer>> transposed = new ArrayList<>();

        for (int i = 0; i < matrix.get(0).size(); i++) {
            List<Integer> newRow = new ArrayList<>();

            for (int j = 0; j < matrix.size(); j++) {
                newRow.add(matrix.get(j).get(i));
            }
            transposed.add(newRow);
        }

        return transposed;
    }

    public List<List<Integer>> multiplyWithActivation(List<List<Integer>> matrixA, List<List<Integer>> matrixB) {
        List<List<Integer>> outputMatrix = fillMatrix(matrixA.size(), matrixB.get(0).size());

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

    public Integer findMismatches(List<List<Double>> matrixA, List<List<Double>> matrixB) {
        int mismatches = 0;

        for (int i = 0; i < matrixA.size(); i++) {
            for (int j = 0; j < matrixA.get(0).size(); j++) {
                if (!Objects.equals(matrixA.get(i).get(j), matrixB.get(i).get(j))) {
                    mismatches++;
                }
            }
        }

        return mismatches;
    }

    public int activation(int weightedSum) {
        if (weightedSum >= 30) {
            return 1;
        }

        return -1;
    }

    public void print(List<List<Double>> matrix) {
        for (List<Double> row: matrix) {
            for (double number: row) {
                System.out.print(number + " ");
            }

            System.out.println();
        }
        System.out.println("=========================================================================================");
    }
}