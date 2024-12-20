/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичес
Вариант 1: Реализовать модель линейной рециркуляционной сети с постоянным коэффициентом обучения с ненормированными весами
*/
package com.software.bsuir.multilayerneuralnetwork.services;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatrixService {
    public List<List<Double>> multiply(List<List<Double>> matrixA, List<List<Double>> matrixB) {
        List<List<Double>> outputMatrix = fillMatrix(matrixA.size(), matrixB.get(0).size());

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

    public List<List<Double>> fillMatrix(int rows, int cols) {
        List<List<Double>> outputMatrix = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            outputMatrix.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                outputMatrix.get(i).add(0.0);
            }
        }

        return outputMatrix;
    }

    public List<List<Double>> transpose(List<List<Double>> matrix) {
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

    public List<List<Double>> subtract(List<List<Double>> matrixA, List<List<Double>> matrixB) {
        List<List<Double>> outputMatrix = new ArrayList<>();

        for (int i = 0; i < matrixA.size(); i++) {
            List<Double> newRow = new ArrayList<>();

            for (int j = 0; j < matrixA.get(0).size(); j++) {
                newRow.add(matrixA.get(i).get(j) - matrixB.get(i).get(j));
            }
            outputMatrix.add(newRow);
        }

        return outputMatrix;
    }

    public List<List<Double>> multiplyByNumber(List<List<Double>> matrix, double num) {
        List<List<Double>> outputMatrix = new ArrayList<>();

        for (List<Double> row : matrix) {
            List<Double> newRow = new ArrayList<>();

            for (Double number : row) {
                newRow.add(number * num);
            }
            outputMatrix.add(newRow);
        }

        return outputMatrix;
    }

    public void print(List<List<Double>> matrix) {
        for (List<Double> row: matrix) {
            for (double number: row) {
                System.out.print(number + " | ");
            }

            System.out.println("New row");
        }
        System.out.println("=========================================================================================");
    }
}
