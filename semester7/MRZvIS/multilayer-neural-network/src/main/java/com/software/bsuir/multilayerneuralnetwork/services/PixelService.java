/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичес
Вариант 1: Реализовать модель линейной рециркуляционной сети с постоянным коэффициентом обучения с ненормированными весами
*/
package com.software.bsuir.multilayerneuralnetwork.services;

import com.software.bsuir.multilayerneuralnetwork.entities.Pixel;
import org.springframework.stereotype.Service;

@Service
public class PixelService {
    public Pixel updatePixelCoords(Pixel pixel, int coordX, int coordY) {
        pixel.setCoordX(coordX);
        pixel.setCoordY(coordY);

        return pixel;
    }

    public Pixel updatePixelColorsByWeight(Pixel pixel, double redColorValue, double greenColorValue,
                                           double blueColorValue, double weightRed, double weightGreen, double weightBlue) {
        pixel.setRedValue(pixel.getRedValue() + redColorValue * weightRed);
        pixel.setGreenValue(pixel.getGreenValue() + greenColorValue * weightGreen);
        pixel.setBlueValue(pixel.getBlueValue() + blueColorValue * weightBlue);

        return pixel;
    }
}