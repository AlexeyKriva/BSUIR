/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
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
    private final MatrixService matrixService;
    private final PixelService pixelService;

    private final static double MAX_ERROR = 3000.0;
    private static int numberOfEpochs;
    private static double learningRate;
    private static List<List<Synapse>> synapsesFirstLayer;
    private static List<List<Synapse>> synapsesSecondLayer;
    private static int numRows;
    private static int numCols;
    private static final int FACTOR = 1;
    private static final int DIVIDER = 6;
    private static double iterError = 0.0;
    private static double NUMBER_OF_BITS = 8;

    public void initNetwork(int userNumberOfEpochs, double userLearningRate, int initNumRows,
                            int initNumCols) {
        if (learningRate == 0) {
            synapsesFirstLayer = synapseService.buildRandomSynapses(ImageService.NUMBER_OF_COMPONENTS_IN_PIXEL *
                            initNumRows * initNumCols,
                    16);

            List<List<Double>> weightsLayer1 = synapsesFirstLayer.stream()
                    .map(row -> row.stream()
                            .map(Synapse::getWeight)
                            .toList())
                    .toList();

            List<List<Double>> transposedWeightsLayer1 = matrixService.transpose(weightsLayer1);

            synapsesSecondLayer = transposedWeightsLayer1.stream()
                    .map(weights -> weights.stream()
                            .map(Synapse::new)
                            .toList())
                    .toList();
        }

        numberOfEpochs = userNumberOfEpochs;

        learningRate = userLearningRate;

        imageService.initRectangleSize(initNumRows, initNumCols);

        numRows = initNumRows;
        numCols = initNumCols;
    }

    private Boolean isNetworkInit() {
        if (!isParametersInit()) {
            StringBuilder parameters = new StringBuilder();

            if (learningRate == 0) {
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

    private Boolean isParametersInit() {
        return learningRate != 0 && synapsesFirstLayer != null;
    }

    public CompressionMetrics compressAndRestore(MultipartFile image) {
        isNetworkInit();

        List<ImageVector> referenceVectors = imageService.splitImageIntoVectors(image);

        List<ImageVector> outputVectors = throughNeurones(referenceVectors, false);

        List<ImageRectangle> predictedRectangles = imageService.buildPredicted(outputVectors);

        imageService.buildImageToFile(predictedRectangles);

        CompressionMetrics compressionMetrics = new CompressionMetrics(
                iterError,
                (double) (ImageService.imageHeight * ImageService.imageWidth * 3 * 8 * 8) /
                        (double) ((numRows * numCols * 3 * FACTOR / DIVIDER * numRows * numCols * 3 * FACTOR / DIVIDER *
                                8 * 8 + numRows * numCols * 3 * numRows * numCols * 3 * FACTOR / DIVIDER * 8 + 16 + 16)
                                * 8)
        );

        return compressionMetrics;
    }

    public CompressionMetrics trainToCompressAndRestore(MultipartFile image) {
        int count = 0;
        double totalError = 0;

        List<ImageRectangle> predictedRectangles = new ArrayList<>();

        for (int epoch = 0; epoch < numberOfEpochs; epoch++) {
            isNetworkInit();

            totalError = 100000000;

            List<ImageVector> referenceVectors = imageService.splitImageIntoVectors(image);

            while (totalError > MAX_ERROR) {

                List<ImageVector> outputVectors = throughNeurones(referenceVectors, true);

                predictedRectangles = imageService.buildPredicted(outputVectors);

                totalError = iterError;
                iterError = 0.0;

                System.out.println("current error: " + totalError);

                System.out.println("count=" + (++count));
            }
        }

        iterError = totalError;
        imageService.buildImageToFile(predictedRectangles);

        CompressionMetrics compressionMetrics = new CompressionMetrics(
                totalError,
                (double) (ImageService.imageHeight * ImageService.imageWidth * 3 * 8 * 8) /
                        (double) ((numRows * numCols * 3 * FACTOR / DIVIDER * numRows * numCols * 3 * FACTOR / DIVIDER *
                                8 * 8 + numRows * numCols * 3 * numRows * numCols * 3 * FACTOR / DIVIDER * 8 + 16 + 16)
                                * 8)
        );

        return compressionMetrics;
    }

    public List<ImageVector> throughNeurones(List<ImageVector> referenceVectors, boolean isTrain) {
        List<ImageVector> outputVectors = new ArrayList<>();

        List<List<Double>> predictedSecondLayer;

        int count = 0;

        //double totalError = 0;

        for (ImageVector referenceVector : referenceVectors) {
//            double currentError = 100000000;
//
//            while (currentError > MAX_ERROR) {
                List<List<Double>> weightsLayer1 = synapsesFirstLayer.stream()
                        .map(row -> row.stream()
                                .map(Synapse::getWeight)
                                .toList())
                        .toList();

                List<List<Double>> weightsLayer2 = synapsesSecondLayer.stream()
                        .map(row -> row.stream()
                                .map(Synapse::getWeight)
                                .toList())
                        .toList();

                List<List<Double>> references = List.of(referenceVector.getPixelsVector().stream()
                        .flatMap(pixel -> List.of(pixel.getRedValue(), pixel.getGreenValue(), pixel.getBlueValue()).stream())
                        .toList());

                List<List<Double>> predictedFirstLayer = throwNeuronLayer(references, weightsLayer1);

                predictedSecondLayer = throwNeuronLayer(predictedFirstLayer, weightsLayer2);

                if (isTrain) {
                    iterError += backPropagation(predictedFirstLayer, predictedSecondLayer, weightsLayer1, weightsLayer2, references);
                }
//                } else {
//                    totalError += currentError * 2;
//                    errors += totalError;
//                    break;
//                }
//            }

            outputVectors.add(new ImageVector(
                    pixelService.fromRgbAndCoords(predictedSecondLayer, referenceVector.getPixelsVector())
            ));
            //System.out.println(++count);
        }

        return outputVectors;
    }

    public List<List<Double>> throwNeuronLayer(List<List<Double>> colors, List<List<Double>> weights) {
        return matrixService.multiply(colors, weights);
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

        return sumSquaredError;
    }

    public Double backPropagation(List<List<Double>> predictLayer1,
                                  List<List<Double>> predictLayer2,
                                  List<List<Double>> weightsLayer1,
                                  List<List<Double>> weightsLayer2,
                                  List<List<Double>> references) {
        List<List<Double>> errorsLayer2 = matrixService.subtract(predictLayer2, references);

        List<List<Double>> newWeightsLayer2 = matrixService.subtract(
                weightsLayer2, matrixService.transpose(
                        matrixService.multiplyByNumber(
                                matrixService.multiply(
                                        matrixService.transpose(errorsLayer2), predictLayer1
                                ), learningRate
                        )
                )
        );

        synapsesSecondLayer = newWeightsLayer2.stream()
                .map(weights -> weights.stream()
                        .map(Synapse::new)
                        .toList())
                .toList();

        List<List<Double>> errorsLayer1 = matrixService.multiply(errorsLayer2, matrixService.transpose(
                newWeightsLayer2
        ));

        List<List<Double>> newWeightsLayer1 = matrixService.subtract(
                weightsLayer1, matrixService.multiplyByNumber(
                        matrixService.multiply(
                                matrixService.transpose(references), errorsLayer1
                        ), learningRate
                )
        );

        synapsesFirstLayer = newWeightsLayer1.stream()
                .map(weights -> weights.stream()
                        .map(Synapse::new)
                        .toList())
                .toList();

        //for (int i = 0; i < errorsLayer2.size(); i++)

        return errorsLayer2.stream()
                .mapToDouble(row -> row.stream()
                        .mapToDouble(val -> Math.pow(val, 2))
                        .sum())
                .sum();
    }
}