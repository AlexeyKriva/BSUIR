/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
Вариант 1: Реализовать модель линейной рециркуляционной сети с постоянным коэффициентом обучения с ненормированными весами
*/
package com.software.bsuir.multilayerneuralnetwork.services;

import com.software.bsuir.multilayerneuralnetwork.entities.ImageVector;
import com.software.bsuir.multilayerneuralnetwork.entities.Neuron;
import com.software.bsuir.multilayerneuralnetwork.entities.Pixel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NeuronService {
    public List<Neuron> fillNeurones(int numberOfNeurones) {
        List<Neuron> neurons = new ArrayList<>();

        for (int i = 0; i < numberOfNeurones; i++) {
            neurons.add(new Neuron(
                    new Pixel(),
                    new Pixel()
            ));
        }

        return neurons;
    }

    public Pixel activation(Pixel pixel) {
        return pixel;
    }

    public List<Neuron> calcOutput(List<Neuron> neurons) {
        for (Neuron neuron : neurons) {
            neuron.setOutput(activation(neuron.getInput()));
        }

        return neurons;
    }

    public ImageVector getOutputs(List<Neuron> neurons) {
        List<Pixel> pixels = new ArrayList<>();

        for (Neuron neuron: neurons) {
            pixels.add(neuron.getOutput());
        }

        return new ImageVector(pixels);
    }
}