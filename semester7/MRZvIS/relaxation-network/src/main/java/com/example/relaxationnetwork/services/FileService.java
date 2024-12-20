package com.example.relaxationnetwork.services;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    public static void saveMatrixToFile(List<List<Integer>> matrix, int index) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                "/Users/aliaksei/Desktop/BSUIR/semester7/MRZvIS/relaxation-network/files/fileName" + index))) {
            for (List<Integer> row : matrix) {
                for (int value : row) {
                    writer.write(value + " "); // Пишем элемент с пробелом
                }
                writer.newLine(); // Переход на новую строку
            }
            System.out.println("Матрица успешно сохранена в " + "fileName");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}