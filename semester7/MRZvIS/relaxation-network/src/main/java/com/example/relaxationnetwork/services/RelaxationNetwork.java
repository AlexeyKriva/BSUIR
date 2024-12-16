package com.example.relaxationnetwork.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RelaxationNetwork {
    private final MatrixService matrixService;
    private final TextService textService;
    private final BinaryService binaryService;
    private final ImageService imageService;

    private final int MAX_ITER = 50;
    private List<List<Integer>> pixelValues = new ArrayList<>();
    private List<List<Integer>> encodedText = new ArrayList<>();

    private List<List<Integer>> weights = new ArrayList<>();

    public void test(MultipartFile image) {
        List<Integer> values = imageService.getPixelValues(image);

        imageService.restoreAndSaveImage(values);
    }

    public List<List<Integer>> train(MultipartFile image, String text) {
        pixelValues.add(imageService.getPixelValues(image));
        encodedText.add(textService.encodeText(text, ImageService.whitePixels, ImageService.blackPixels));

        weights = matrixService.multiply(matrixService.transpose(pixelValues), encodedText);

        return weights;
    }

    public Map<String, String> forward(MultipartFile image) {
        if (weights.isEmpty()) {
            throw new RuntimeException("Матрица весов не инициализирована!!!");
        }

        List<List<Integer>> forwardOutput = new ArrayList<>();
        List<List<Integer>> backOutput = new ArrayList<>();
        backOutput.add(imageService.getPixelValues(image));

        int i = 0;
        while (i < MAX_ITER) {
            forwardOutput = matrixService.multiplyWithActivation(backOutput, weights);

            if (isTextsMatched(forwardOutput, encodedText)) {// && isImagesMatched(backOutput, matrixService.transpose(pixelValues))) {
                String targetText = textService.decodeText(forwardOutput);
                String sourceText = textService.decodeText(encodedText);

                imageService.restoreAndSaveImage(backOutput.get(0));
                imageService.restoreAndSaveImage(pixelValues.get(0));

                return Map.of(sourceText, targetText);
            }

            System.out.println(i);

            backOutput = matrixService.multiplyWithActivation(forwardOutput, matrixService.transpose(weights));
            i++;
        }

        imageService.restoreAndSaveImage(backOutput.get(0));

        return null;
    }

    public List<List<Integer>> backward(List<List<Integer>> output) {
        if (weights.isEmpty()) {
            throw new RuntimeException("Матрица весов не инициализирована!!!");
        }

        return matrixService.multiplyWithActivation(output, matrixService.transpose(weights));
    }

    public boolean isTextsMatched(List<List<Integer>> predicted, List<List<Integer>> reference) {
        int coincidence = 0;

        for (int i = 0; i < predicted.get(0).size(); i++) {
            if (reference.get(0).get(i).equals(predicted.get(0).get(i))) {
                coincidence++;
            }
        }

        return predicted.get(0).size() == reference.get(0).size() && coincidence == reference.get(0).size();
    }

//    public boolean isImagesMatched(List<List<Integer>> predicted, List<List<Integer>> reference) {
//        int coincidence = 0;
//
//        for (int i = 0; i < predicted.get(0).size(); i++) {
//            if (reference.get(0).get(i).equals(predicted.get(0).get(i))) {
//                coincidence++;
//            }
//        }
//
//        System.out.println(coincidence);
//        System.out.println(reference.get(0).size() * 0.98);
//
//        return predicted.get(0).size() == reference.get(0).size() && (double) coincidence > (double)
//                reference.get(0).size() * 0.98;
//    }
}