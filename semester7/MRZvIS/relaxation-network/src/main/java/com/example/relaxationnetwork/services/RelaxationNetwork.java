package com.example.relaxationnetwork.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RelaxationNetwork {
    private final MatrixService matrixService;
    private final TextService textService;

    private List<List<Integer>> weights;
    private List<List<List<Integer>>> rusMatrixes = new ArrayList<>();
    private List<List<List<Integer>>> engMatrixes = new ArrayList<>();
//    Source: ЀЀайер
//    Target: `amn

    private static final int FIRST_LAYER_NEURONES = 77;
    private static final int SECOND_LAYER_NEURONES = 44;

    private final int MAX_ITER = 10;

    public List<List<Integer>> train(List<String> russian, List<String> english) {
        if (russian.size() != english.size()) {
            throw new RuntimeException("Не все слова имеют ассоциации");
        }

        russian = russian.stream().map(String::toLowerCase).toList();
        english = english.stream().map(String::toLowerCase).toList();

        weights = matrixService.fillZeroMatrix(FIRST_LAYER_NEURONES, SECOND_LAYER_NEURONES);

        for (int i = 0; i < russian.size(); i++) {
            String rus = russian.get(i);
            String eng = english.get(i);

            rusMatrixes.add(textService.encodeText(rus, FIRST_LAYER_NEURONES));
            engMatrixes.add(textService.encodeText(eng, SECOND_LAYER_NEURONES));

            weights = matrixService.sum(weights, matrixService.multiply(matrixService.transpose(rusMatrixes.get(i)), engMatrixes.get(i)));
            //FileService.saveMatrixToFile(weights, i);
        }

        //matrixService.print(weights);

        return weights;
    }

    public List<List<Integer>> testTrain(List<List<String>> pictures, List<String> numbers) {
        if (pictures.size() != numbers.size()) {
            throw new RuntimeException("Не все слова имеют ассоциации");
        }

        weights = matrixService.fillZeroMatrix(15, 3);

        for (int i = 0; i < pictures.size(); i++) {
            List<String> picture = pictures.get(i);
            String number = numbers.get(i);

            System.out.println("Picture: " + picture);
            rusMatrixes.add(textService.encodePicture(picture));
            matrixService.print(rusMatrixes.get(i));
            System.out.println("\n\n\n");
            System.out.println("Number: " + number);
            engMatrixes.add(textService.toBinary(number));
            matrixService.print(engMatrixes.get(i));
            System.out.println("\n\n\n");

            weights = matrixService.sum(weights, matrixService.multiply(matrixService.transpose(rusMatrixes.get(i)), engMatrixes.get(i)));
            //FileService.saveMatrixToFile(weights, i);
        }

        System.out.println("Weights: ");
        matrixService.print(weights);

        return weights;
    }

    public Map<String, String> forward(String russian) {
        if (weights.isEmpty()) {
            throw new RuntimeException("Матрица весов не инициализирована!!!");
        }

        List<List<Integer>> previousRussian = new ArrayList<>();
        List<List<Integer>> previousEnglish = new ArrayList<>();
        List<List<Integer>> lastRussian = textService.encodeText(russian, FIRST_LAYER_NEURONES);
        List<List<Integer>> lastEnglish = new ArrayList<>();

//        System.out.println("Last russian:");
//        matrixService.print(lastRussian);

        int i = 0;
        while (i < MAX_ITER) {
            lastEnglish = matrixService.multiplyWithActivation(lastRussian, weights);
            //matrixService.print(lastEnglish);
            lastRussian = matrixService.multiplyWithActivation(lastEnglish, matrixService.transpose(weights));

            System.out.println("Last russian:");
            matrixService.print(lastRussian);
            System.out.println("Last english:");
            matrixService.print(lastEnglish);
//            System.out.println("Last english: " + textService.decodeText(lastEnglish, SECOND_LAYER_NEURONES));
//            System.out.println("Last russian: " + textService.decodeText(lastRussian, FIRST_LAYER_NEURONES));
            //matrixService.print(lastRussian);
            i++;
            //System.out.println("STEP #" + i);


            if (!previousRussian.isEmpty() && !previousEnglish.isEmpty() &&
                    isNetworkFindResult(previousRussian, previousEnglish, lastRussian, lastEnglish)) {
                String targetText = textService.decodeText(lastEnglish, SECOND_LAYER_NEURONES);
                String sourceText = textService.decodeText(lastRussian, FIRST_LAYER_NEURONES);
//                System.out.println("Target: " + targetText);
//                System.out.println("Source: " + sourceText);
                if (sourceText.equals(russian)) {
                    return Map.of(targetText, russian, sourceText, russian);
                }
            }

            previousRussian = lastRussian;
            previousEnglish = lastEnglish;
        }

        //System.out.println("Russia predicte: " + lastRussian);
        //System.out.println("English predicte: " + lastEnglish);

        return Map.of("ошибка", "сеть не может найти ассоциацию");
    }

    public Map<String, List<String>> testForward(List<String> picture) {
        if (weights.isEmpty()) {
            throw new RuntimeException("Матрица весов не инициализирована!!!");
        }

        List<List<Integer>> previousPicture = new ArrayList<>();
        List<List<Integer>> previousNumber = new ArrayList<>();
        List<List<Integer>> lastPicture = textService.encodePicture(picture);
        List<List<Integer>> lastNumber = new ArrayList<>();

        int i = 0;
        while (i < MAX_ITER) {
            lastNumber = matrixService.multiplyWithActivation(lastPicture, weights);
            //matrixService.print(lastEnglish);
            lastPicture = matrixService.multiplyWithActivation(lastNumber, matrixService.transpose(weights));

//            System.out.println("Last russian:");
//            matrixService.print(lastPicture);
//            System.out.println("Last english:");
//            matrixService.print(lastNumber);
//            System.out.println("Last english: " + textService.decodeText(lastEnglish, SECOND_LAYER_NEURONES));
//            System.out.println("Last russian: " + textService.decodeText(lastRussian, FIRST_LAYER_NEURONES));
            //matrixService.print(lastRussian);
            i++;
            System.out.println("STEP #" + i);


            if (!previousPicture.isEmpty() && !previousNumber.isEmpty() &&
                    isNetworkFindResult(previousPicture, previousNumber, lastPicture, lastNumber)) {
                System.out.println("Number:");
                matrixService.print(lastNumber);
                String targetText = textService.fromBinary(lastNumber);
                List<String> sourceText = textService.decodePicture(lastPicture);
//                System.out.println("Target: " + targetText);
//                System.out.println("Source: " + sourceText);
                //if (sourceText.equals(picture)) {
                    return Map.of(targetText, picture, "исходное изображение", sourceText);
                //}
            }

            previousPicture = lastPicture;
            previousNumber = lastNumber;
        }

        //System.out.println("Russia predicte: " + lastRussian);
        //System.out.println("English predicte: " + lastEnglish);

        return null;
    }

    public boolean isNetworkFindResult(List<List<Integer>> previousRussian, List<List<Integer>> previousEnglish,
                                       List<List<Integer>> lastRussian, List<List<Integer>> lastEnglish) {
        int coincidenceRussian = 0;

        for (int i = 0; i < previousRussian.get(0).size(); i++) {
            if (previousRussian.get(0).get(i).equals(lastRussian.get(0).get(i))) {
                coincidenceRussian++;
            }
        }

        int coincidenceEnglish = 0;

        for (int i = 0; i < previousEnglish.get(0).size(); i++) {
            if (previousEnglish.get(0).get(i).equals(lastEnglish.get(0).get(i))) {
                coincidenceEnglish++;
            }
        }

        return coincidenceRussian == lastRussian.get(0).size() &&
                coincidenceEnglish == lastEnglish.get(0).size();
    }

    public Map<String, String> backward(String english) {
        return Map.of();
    }

    public void test(String text) {
        List<List<Integer>> list = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                1, -1, -1, -1, -1, 1, 1, -1, -1, 1, -1,
                1, -1, -1, -1, -1, 1, 1, 1, 1, 1, -1,
                1, -1, -1, -1, -1, 1, 1, 1, -1, 1, 1,
                1, -1, -1, -1, -1, 1, 1, 1, -1, 1, -1
                ))));
        System.out.println(textService.decodeText(list, 88));
        for (char a: text.toCharArray()) {
            textService.getLetterIndex(a);
        }
    }
}