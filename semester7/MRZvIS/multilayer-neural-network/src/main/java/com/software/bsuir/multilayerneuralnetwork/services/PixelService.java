/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичес
Вариант 1: Реализовать модель линейной рециркуляционной сети с постоянным коэффициентом обучения с ненормированными весами
*/
package com.software.bsuir.multilayerneuralnetwork.services;

import com.software.bsuir.multilayerneuralnetwork.entities.ImageVector;
import com.software.bsuir.multilayerneuralnetwork.entities.Pixel;
import org.hibernate.validator.internal.util.stereotypes.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PixelService {
    private static final int MAX_VALUE_OF_RGB = 255;

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

    public List<Pixel> fromRgbAndCoords(List<List<Double>> colors, List<Pixel> referencePixels) {
        List<Pixel> pixels = new ArrayList<>();

        for (int i = 0; i < referencePixels.size(); i += 1) {
            pixels.add(
                    Pixel.builder()
                            .coordX(referencePixels.get(i).getCoordX())
                            .coordY(referencePixels.get(i).getCoordY())
                            .redValue(colors.get(0).get(3 * i))
                            .greenValue(colors.get(0).get(3 * i + 1))
                            .blueValue(colors.get(0).get(3 * i + 2))
                            .build()
            );
        }

        return pixels;
    }

    public List<ImageVector> restorePixels(List<ImageVector> imageVectors) {
        for (ImageVector imageVector: imageVectors) {
            for (Pixel pixel: imageVector.getPixelsVector()) {
                double red = Math.abs((pixel.getRedValue() + 1) * MAX_VALUE_OF_RGB / 2) % MAX_VALUE_OF_RGB;
                double green = Math.abs((pixel.getGreenValue() + 1) * MAX_VALUE_OF_RGB / 2) % MAX_VALUE_OF_RGB;
                double blue = Math.abs((pixel.getBlueValue() + 1) * MAX_VALUE_OF_RGB / 2) % MAX_VALUE_OF_RGB;

                pixel.setRedValue(red);
                pixel.setRedValue(green);
                pixel.setRedValue(blue);
            }
        }

        return imageVectors;
    }
}