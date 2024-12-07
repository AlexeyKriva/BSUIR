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

import static com.software.bsuir.multilayerneuralnetwork.exceptions.ExceptionMessage.RECIRCULATION_NETWORK_IS_NOT_INIT_MESSAGE;

@Component
@RequiredArgsConstructor
public class RecirculationNetwork {
    private final ImageService imageService;
    private final SynapseService synapseService;
    private final NeuronService neuronService;

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

            while (totalError > 0.05) {

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
            ImageVector outputVectorFirstLayer = throwOneNeuronLayer(referenceVector.getPixelsVector(), synapsesFirstLayer);
            ImageVector outputVectorSecondLayer = throwOneNeuronLayer(outputVectorFirstLayer.getPixelsVector(), synapsesSecondLayer);

            if (isTrain) {
                List<Double> secondLayerErrors = computeLayerErrors(referenceVector, outputVectorSecondLayer);

                synapsesSecondLayer = adjustingWeights(referenceVector, outputVectorSecondLayer, synapsesSecondLayer,
                        outputVectorFirstLayer, null, null);

                synapsesFirstLayer = adjustingWeights(outputVectorSecondLayer, outputVectorFirstLayer, synapsesFirstLayer,
                        referenceVector, synapsesSecondLayer, secondLayerErrors);
            }

            outputVectors.add(outputVectorSecondLayer);
        }

        return outputVectors;
    }

    private List<Double> computeLayerErrors(ImageVector referenceVector, ImageVector predictedVector) {
        List<Double> errors = new ArrayList<>();
        for (int i = 0; i < referenceVector.getPixelsVector().size(); i++) {
            double errorRed = predictedVector.getPixelsVector().get(i).getRedValue() - referenceVector.getPixelsVector().get(i).getRedValue();
            double errorGreen = predictedVector.getPixelsVector().get(i).getGreenValue() - referenceVector.getPixelsVector().get(i).getGreenValue();
            double errorBlue = predictedVector.getPixelsVector().get(i).getBlueValue() - referenceVector.getPixelsVector().get(i).getBlueValue();
            errors.add(errorRed + errorGreen + errorBlue);
        }
        return errors;
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
                                                List<List<Synapse>> synapses, ImageVector inputVector,
                                                List<List<Synapse>> nextLayerSynapses, List<Double> nextLayerErrors) {
        List<List<Synapse>> newSynapses = new ArrayList<>();
        List<Pixel> reference = referenceVector.getPixelsVector();
        List<Pixel> predicted = predictedVector.getPixelsVector();
        List<Synapse> synapsesForNeuron = new ArrayList<>();

        for (int i = 0; i < reference.size(); i++) {
            double errorRed = 0.0;
            double errorGreen = 0.0;
            double errorBlue = 0.0;

            if (nextLayerSynapses != null && nextLayerErrors != null) {
                for (int j = 0; j < nextLayerSynapses.size(); j++) {
                    errorRed += nextLayerSynapses.get(j).get(i * 3).getWeight() * nextLayerErrors.get(j);
                    errorGreen += nextLayerSynapses.get(j).get(i * 3 + 1).getWeight() * nextLayerErrors.get(j);
                    errorBlue += nextLayerSynapses.get(j).get(i * 3 + 2).getWeight() * nextLayerErrors.get(j);
                }
            } else {
                errorRed = predicted.get(i).getRedValue() - reference.get(i).getRedValue();
                errorGreen = predicted.get(i).getGreenValue() - reference.get(i).getGreenValue();
                errorBlue = predicted.get(i).getBlueValue() - reference.get(i).getBlueValue();
            }

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

        return newSynapses;
    }

}