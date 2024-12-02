package com.software.bsuir.lab9.services;

import com.software.bsuir.lab9.clients.TranslatorClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vosk.Model;
import org.vosk.Recognizer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

@Service
@RequiredArgsConstructor
public class MicrophoneService {
    @Value("${model.path}")
    private String pathToModel;

    @Value("${huggingface.api.key}")
    private String accessKey;

    private final TranslatorClient translatorClient;

    private Model model;
    private AudioFormat format;
    private DataLine.Info info;
    private TargetDataLine microphone;

    @PostConstruct
    @SneakyThrows
    public void init() {
        model = new Model(pathToModel);

        format = new AudioFormat(16000, 16, 1, true, false);
        info = new DataLine.Info(TargetDataLine.class, format);

        microphone = (TargetDataLine) AudioSystem.getLine(info);
    }

    @SneakyThrows
    public String launch() {
        microphone.open(format);
        microphone.start();

        out.println("Слушаю пользователя...");

        try (Recognizer recognizer = new Recognizer(model, 16000)) {
            byte[] buffer = new byte[4096];

            while (true) {
                int bytesRead = microphone.read(buffer, 0, buffer.length);

                if (bytesRead > 0) {
                    if (recognizer.acceptWaveForm(buffer, bytesRead)) {
                        String result = recognizer.getResult();

                        out.println("Result: " + result);

                        return doOperation(result);
                    }
                }
            }
        } finally {
            microphone.stop();
            microphone.close();
        }
    }

    public String doOperation(String result) {
        result = deleteExtraSymbols(result);

        if (result.contains("say")) {
            return "{\n\t\"message\": \"" + defineSay(result) + "\"\n}";
        } else if (result.contains("calculate")) {
            return "{\n\t\"message\": \"" + doCalculate(result) + "\"\n}";
        } else if (result.contains("translate")) {
            return "{\n\t\"message\": \"" + doTranslateToRussian(result) + "\"\n}";
        }

        return "{\n\t\"unknown operation\": \"" + deleteExtraSymbols(result) + "\"\n}";
    }

    public String defineSay(String phrase) {
        phrase = phrase.replaceAll("say", "");

        int start = 0;

        for (int i = 0; i < phrase.length(); i++) {
            if (Character.isLetterOrDigit(phrase.charAt(i))) {
                start = i;
                break;
            }
        }

        if (!phrase.isBlank()) {
            return phrase.substring(start);
        }

        return "unknown phrase";
    }

    public String doCalculate(String expression) {
        expression = expression.replaceAll("calculate", "");

        int start = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (Character.isLetterOrDigit(expression.charAt(i))) {
                start = i;
                break;
            }
        }

        if (!expression.isBlank()) {
            expression = expression.substring(start);
        }

        List<String> tokens = Arrays.stream(expression.split(" ")).toList();

        out.println(tokens.size());
        tokens.forEach(out::println);

        if (tokens.size() >= 3) {
            Integer firstNumber = numbers.get(tokens.get(0).toLowerCase());
            Integer secondNumber = numbers.get(tokens.get(2).toLowerCase());

            if (firstNumber != null && secondNumber != null) {
                Integer answer = 0;
                if (tokens.get(1).equalsIgnoreCase("plus")) {
                    answer = firstNumber + secondNumber;
                } else if (tokens.get(1).equalsIgnoreCase("minus")) {
                    answer = firstNumber - secondNumber;
                } else if (tokens.get(1).equalsIgnoreCase("multiply")) {
                    answer = firstNumber * secondNumber;
                } else if (tokens.get(1).equalsIgnoreCase("divide")) {
                    answer = firstNumber / secondNumber;
                } else if (tokens.get(1).equalsIgnoreCase("mod")) {
                    answer = firstNumber % secondNumber;
                } else {
                    return "unknown math operation: " + tokens.get(1);
                }

                return String.valueOf(answer);
            }

            return "unknown numbers: " + firstNumber + " and " + secondNumber;
        }

        return "unknown expression: " + expression;
    }

    public String deleteExtraSymbols(String result) {
        result = result.trim();
        result = result.replaceAll("\\{", "");
        result = result.replaceAll("}", "");
        result = result.replaceAll("\"", "");
        result = result.replaceAll("text", "");
        result = result.replaceAll("translation_", "");
        result = result.replaceAll(":", "");
        result = result.replaceAll("\n", "");
        result = result.replaceAll("\\[", "");
        result = result.replaceAll("]", "");

        return result;
    }

    private final Map<String, Integer> numbers = Map.of(
            "one", 1, "two", 2, "three", 3, "four", 4, "five", 5,
            "six", 6, "seven", 7, "eight", 8, "nine", 9, "zero", 0
    );

    public String doTranslateToRussian(String phrase) {
        phrase = phrase.replaceAll("translate", "");

        int start = 0;

        for (int i = 0; i < phrase.length(); i++) {
            if (Character.isLetterOrDigit(phrase.charAt(i))) {
                start = i;
                break;
            }
        }

        if (!phrase.isBlank()) {
            phrase = phrase.substring(start);
        }

        String result = translatorClient.translateTextFromEnglishToRussian(accessKey, phrase).getBody();

        result = deleteExtraSymbols(result);

        if (!result.isBlank()) {
            return result;
        }

        return "unknown phrase";
    }
}