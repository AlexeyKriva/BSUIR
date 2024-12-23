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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MatrixService {
    public List<List<Integer>> sum(List<List<Integer>> matrixA, List<List<Integer>> matrixB) {
        List<List<Integer>> outputMatrix = new ArrayList<>();

        for (int i = 0; i < matrixA.size(); i++) {
            outputMatrix.add(new ArrayList<>());
            for (int j = 0; j < matrixA.get(0).size(); j++) {
                outputMatrix.get(i).add(matrixA.get(i).get(j) + matrixB.get(i).get(j));
            }
        }

        return outputMatrix;
    }

    public List<List<Integer>> multiply(List<List<Integer>> matrixA, List<List<Integer>> matrixB) {
        List<List<Integer>> outputMatrix = fillZeroMatrix(matrixA.size(), matrixB.get(0).size());

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

    public List<List<Integer>> fillZeroMatrix(int rows, int cols) {
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
        List<List<Integer>> outputMatrix = fillZeroMatrix(matrixA.size(), matrixB.get(0).size());

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

    public int activation(int weightedSum) {
        //System.out.println("weight: " + weightedSum);
        if (weightedSum > 0) {
            return 1;
        } else if (weightedSum < 0) {
            return -1;
        }

        return 0;
    }

    public void print(List<List<Integer>> matrix) {
        for (List<Integer> row: matrix) {
            for (int number: row) {
                System.out.print(number + " ");
            }

            System.out.println();
        }
        System.out.println("=========================================================================================");
    }

    public boolean isThereSuchMatrix(List<List<Integer>> target, List<List<List<Integer>>> sources) {
        for (List<List<Integer>> matrix: sources) {
            int coincidence = 0;

            for (int i = 0; i < target.size(); i++) {
                for (int j = 0; j < target.get(i).size(); j++) {
                    if (Objects.equals(target.get(i).get(j), matrix.get(i).get(j))) {
                        coincidence++;
                    } else {
                        break;
                    }
                }
            }

            if (coincidence == target.size() * target.get(0).size()) {
                return true;
            }
        }

        return false;
    }
}