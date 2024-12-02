/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичес
Вариант 1: Реализовать модель линейной рециркуляционной сети с постоянным коэффициентом обучения с ненормированными весами
*/
package com.software.bsuir.multilayerneuralnetwork.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageRectangle {
    private int leftCoordX;
    private int leftCoordY;
    private int numRows;
    private int numCols;
    private List<List<Pixel>> pixels;

    public List<List<Pixel>> setPixel(int numRow, Pixel pixel) {
        pixels.get(numRow).add(pixel);

        return this.pixels;
    }
}
