/*
Лабораторная работа №2 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
Вариант 2: Реализовать модель двунаправленной ассоциативной памяти
*/
package org.example.jordannetwork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatrixService {
    public static List<List<Double>> sum(List<List<Double>> matrixA, List<List<Double>> matrixB) {
        List<List<Double>> outputMatrix = new ArrayList<>();

        for (int i = 0; i < matrixA.size(); i++) {
            outputMatrix.add(new ArrayList<>());
            for (int j = 0; j < matrixA.get(0).size(); j++) {
                outputMatrix.get(i).add(matrixA.get(i).get(j) + matrixB.get(i).get(j));
            }
        }

        return outputMatrix;
    }

    public static List<List<Double>> multiply(List<List<Double>> matrixA, List<List<Double>> matrixB) {
        List<List<Double>> outputMatrix = fillZeroMatrix(matrixA.size(), matrixB.get(0).size());

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

    public static List<List<Double>> fillZeroMatrix(int rows, int cols) {
        List<List<Double>> outputMatrix = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            outputMatrix.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                outputMatrix.get(i).add(0.0);
            }
        }

        return outputMatrix;
    }

    public static List<Double> initLayer(int size) {
        List<Double> layer = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            layer.add(0.0);
        }
        return layer;
    }

    public static List<List<Double>> transpose(List<List<Double>> matrix) {
        List<List<Double>> transposed = new ArrayList<>();

        for (int i = 0; i < matrix.get(0).size(); i++) {
            List<Double> newRow = new ArrayList<>();

            for (int j = 0; j < matrix.size(); j++) {
                newRow.add(matrix.get(j).get(i));
            }
            transposed.add(newRow);
        }

        return transposed;
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