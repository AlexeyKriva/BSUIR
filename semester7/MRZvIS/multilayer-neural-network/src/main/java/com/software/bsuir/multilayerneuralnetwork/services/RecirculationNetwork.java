/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичес
Вариант 1: Реализовать модель линейной рециркуляционной сети с постоянным коэффициентом обучения с ненормированными весами
*/
package com.software.bsuir.multilayerneuralnetwork.services;

import com.software.bsuir.multilayerneuralnetwork.entities.*;
import com.software.bsuir.multilayerneuralnetwork.exceptions.RecirculationNetworkIsNotInitException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.software.bsuir.multilayerneuralnetwork.exceptions.ExceptionMessage.RECIRCULATION_NETWORK_IS_NOT_INIT_MESSAGE;

@Component
@RequiredArgsConstructor
public class RecirculationNetwork {
    private final ImageService imageService;
    private final SynapseService synapseService;
    private final NeuronService neuronService;
    private final PixelService pixelService;

    private static Integer numberOfEpochs;
    private static Double learningRate;
    private static List<List<Synapse>> synapsesFirstLayer;
    private static List<List<Synapse>> synapsesSecondLayer;

    public void initRecirculationNetwork(int userNumberOfEpochs, double userLearningRate, int numRows, int numCols) {
        if (learningRate == null) {
            synapsesFirstLayer = synapseService.buildRandomSynapses(1, numRows * numCols);
            synapsesSecondLayer = synapseService.buildRandomSynapses(1, numRows * numCols);
        }

        numberOfEpochs = userNumberOfEpochs;

        learningRate = userLearningRate;

        imageService.initRectangleSize(numRows, numCols);
    }

    private Boolean isRecirculationNetworkInit() {
        if (!isParametersOfRecirculationNetworkInit()) {
            StringBuilder parameters = new StringBuilder();

            if (learningRate == null) {
                parameters.append("learningRate ");
            }

            if (synapsesFirstLayer == null) {
                parameters.append("synapses layer #1");
            }

            if (synapsesSecondLayer == null) {
                parameters.append("synapses layer #2");
            }

            throw new RecirculationNetworkIsNotInitException(String.format(
                    RECIRCULATION_NETWORK_IS_NOT_INIT_MESSAGE, parameters
            ));
        }

        return true;
    }

    private Boolean isParametersOfRecirculationNetworkInit() {
        return learningRate != null && synapsesFirstLayer != null;
    }

    public Double compressImage(MultipartFile image) {
        isRecirculationNetworkInit();

        List<ImageVector> referenceVectors = imageService.splitImageIntoVectors(image);

        List<ImageVector> outputVectors = throughNeurones(referenceVectors, false);

        List<ImageRectangle> predictedRectangles = imageService.buildPredicted(outputVectors);

        imageService.buildImageToFile(predictedRectangles);

        double totalError = calcErrorPercentage(referenceVectors, outputVectors);

        return totalError;
    }

    public Double trainToCompressImage(MultipartFile image) {
        double totalError = 0;

        List<ImageRectangle> predictedRectangles = new ArrayList<>();

        for (int epoch = 0; epoch < numberOfEpochs; epoch++) {
            isRecirculationNetworkInit();

            totalError = 100000000;

            List<ImageVector> referenceVectors = imageService.splitImageIntoVectors(image);

            while (totalError > 0.1) {

                List<ImageVector> outputVectors = throughNeurones(referenceVectors, true);

                predictedRectangles = imageService.buildPredicted(outputVectors);

                totalError = calcErrorPercentage(referenceVectors, outputVectors);
            }
        }

        imageService.buildImageToFile(predictedRectangles);

        return totalError;
    }

    public List<ImageVector> throughNeurones(List<ImageVector> referenceVectors, boolean isTrain) {
        List<ImageVector> outputVectors = new ArrayList<>();

        for (ImageVector referenceVector : referenceVectors) {
            ImageVector outputVectorFirstLayer = throwOneNeuronLayer(referenceVector.getPixelsVector(),
                    synapsesFirstLayer);

            ImageVector outputVectorSecondLayer = throwOneNeuronLayer(outputVectorFirstLayer.getPixelsVector(),
                    synapsesSecondLayer);

            if (isTrain) {
                synapsesSecondLayer = adjustingWeights(referenceVector, outputVectorSecondLayer, synapsesSecondLayer,
                        outputVectorFirstLayer);
                synapsesFirstLayer = adjustingWeights(outputVectorSecondLayer, outputVectorFirstLayer, synapsesFirstLayer,
                        referenceVector);
            }

            outputVectors.add(outputVectorSecondLayer);
        }

        return outputVectors;
    }

    public ImageVector throwOneNeuronLayer(List<Pixel> pixelVector, List<List<Synapse>> synapses) {
        List<Neuron> neurons = new ArrayList<>();

        for (int i = 0; i < pixelVector.size(); i++) {
            Pixel currentPixel = pixelVector.get(i);

            neurons.add(new Neuron(
                    Pixel.builder()
                            .coordX(currentPixel.getCoordX())
                            .coordY(currentPixel.getCoordY())
                            .redValue(currentPixel.getRedValue() * synapses.get(0).get(3 * i).getWeight())
                            .greenValue(currentPixel.getGreenValue() * synapses.get(0).get(3 * i + 1).getWeight())
                            .blueValue(currentPixel.getBlueValue() * synapses.get(0).get(3 * i + 2).getWeight())
                            .build(),
                    new Pixel()));
        }

        neurons = neuronService.calcOutput(neurons);

        return neuronService.getOutputs(neurons);
    }

    public double calcErrorPercentage(List<ImageVector> referencePixels, List<ImageVector> predictedPixels) {
        double sumSquaredError = 0.0;

        for (int i = 0; i < referencePixels.size(); i++) {
            for (int j = 0; j < referencePixels.get(0).getPixelsVector().size(); j++) {
                Pixel referencePixel = referencePixels.get(i).getPixelsVector().get(j);
                Pixel predictedPixel = predictedPixels.get(i).getPixelsVector().get(j);

                double redDelta = referencePixel.getRedValue() - predictedPixel.getRedValue();
                double greenDelta = referencePixel.getGreenValue() - predictedPixel.getGreenValue();
                double blueDelta = referencePixel.getBlueValue() - predictedPixel.getBlueValue();

                sumSquaredError += Math.pow(redDelta, 2) + Math.pow(greenDelta, 2) + Math.pow(blueDelta, 2);
            }
        }

        return Math.sqrt(sumSquaredError / (ImageService.NUMBER_OF_COMPONENTS_IN_PIXEL *
                ImageService.imageHeight * ImageService.imageWidth));
    }

    public List<List<Synapse>> adjustingWeights(ImageVector referenceVector, ImageVector predictedVector,
                                                List<List<Synapse>> synapses, ImageVector inputVector) {
        List<List<Synapse>> newSynapses = new ArrayList<>();

        List<Pixel> reference = referenceVector.getPixelsVector();
        List<Pixel> predicted = predictedVector.getPixelsVector();

        List<Synapse> synapsesForNeuron = new ArrayList<>();

        for (int i = 0; i < reference.size(); i++) {
            double errorRed = predicted.get(i).getRedValue() - reference.get(i).getRedValue();
            double errorGreen = predicted.get(i).getGreenValue() - reference.get(i).getGreenValue();
            double errorBlue = predicted.get(i).getBlueValue() - reference.get(i).getBlueValue();

            double newWeightRed = synapses.get(0).get(3 * i).getWeight() - learningRate * errorRed *
                    inputVector.getPixelsVector().get(i).getRedValue();
            double newWeightGreen = synapses.get(0).get(3 * i + 1).getWeight() - learningRate * errorGreen *
                    inputVector.getPixelsVector().get(i).getGreenValue();
            double newWeightBlue = synapses.get(0).get(3 * i + 2).getWeight() - learningRate * errorBlue *
                    inputVector.getPixelsVector().get(i).getBlueValue();

            synapsesForNeuron.add(new Synapse(newWeightRed));
            synapsesForNeuron.add(new Synapse(newWeightGreen));
            synapsesForNeuron.add(new Synapse(newWeightBlue));
        }

        newSynapses.add(synapsesForNeuron);

        synapses = newSynapses;

//        System.out.println("=============================================================");
//        synapseService.printSynapses(synapses);
//        System.out.println("=============================================================");

        return synapses;
    }
}