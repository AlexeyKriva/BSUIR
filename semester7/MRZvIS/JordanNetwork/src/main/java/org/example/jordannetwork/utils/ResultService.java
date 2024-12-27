package org.example.jordannetwork.utils;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResultService {
    public static Map<String, String> buildResult(List<Double> sequence, List<Double> nextNumbers,
                                           List<Double> predictedNumbers, int itCount, int numbersFroPrediction) {
        Map<String, String> result = new LinkedHashMap<>();

        int numberOfWindow = 1;

        StringBuilder window = new StringBuilder();

        for (Double number: sequence) {
            window.append(number).append(" ");
        }

        result.put("окно " + numberOfWindow, window.toString());

        window.setLength(0);
        StringBuilder references = new StringBuilder();
        StringBuilder predicted = new StringBuilder();
        for (int i = 0; i < nextNumbers.size(); i++) {
            if (i != 0 && i % numbersFroPrediction == 0) {
                result.put("ожидаемые значения " + numberOfWindow, references.toString());
                result.put("предсказанные значения " + numberOfWindow, predicted.toString());
                result.put("окно " + (++numberOfWindow), references.toString());
                references.setLength(0);
                predicted.setLength(0);
            }

            references.append(nextNumbers.get(i)).append(" ");
            predicted.append(predictedNumbers.get(i)).append(" ");
        }

        result.put("ожидаемые значения " + numberOfWindow, references.toString());
        result.put("предсказанные значения " + numberOfWindow, predicted.toString());

        result.put("количество итераций", String.valueOf(itCount));

        return result;
    }
}