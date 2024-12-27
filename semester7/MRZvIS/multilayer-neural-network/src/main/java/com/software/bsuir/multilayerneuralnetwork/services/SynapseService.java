/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
Вариант 1: Реализовать модель линейной рециркуляционной сети с постоянным коэффициентом обучения с ненормированными весами
*/
package com.software.bsuir.multilayerneuralnetwork.services;

import com.software.bsuir.multilayerneuralnetwork.entities.Synapse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SynapseService {
    private static final int LEFT_BORDER = -1;
    private static final int RIGHT_BORDER = 1;
    private static final int NUMBER_OF_DIGITS_AFTER_DOT = 2;

    public List<List<Synapse>> buildRandomSynapses(int rows, int cols) {
        List<List<Synapse>> synapses = new ArrayList<>();


        for (int i = 0; i < rows; i++) {
            synapses.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                synapses.get(i).add(new Synapse(roundRandomWeight()));
            }
        }

        return synapses;
    }

    public double roundRandomWeight() {
        Random random = new Random();

        double randomWeight = LEFT_BORDER + (RIGHT_BORDER - LEFT_BORDER) * random.nextDouble();

        return roundToPrecision(randomWeight);
    }

    private double roundToPrecision(double value) {
        BigDecimal roundedValue = BigDecimal.valueOf(value);
        return roundedValue.setScale(NUMBER_OF_DIGITS_AFTER_DOT, RoundingMode.HALF_UP).doubleValue();
    }

    public double roundRandomWeight(double randomWeight) {
        BigDecimal randomWeightForRound = BigDecimal.valueOf(randomWeight);

        return randomWeightForRound.setScale(NUMBER_OF_DIGITS_AFTER_DOT, RoundingMode.HALF_UP).doubleValue();
    }

    public void printSynapses(List<List<Synapse>> synapses) {
        for (List<Synapse> synapsesForNeuron: synapses) {
            for (Synapse synapse: synapsesForNeuron) {
                System.out.print(synapse.getWeight() + "\t");
            }

            System.out.println();
        }
    }
}