package com.example.relaxationnetwork.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TextService {
    private final BinaryService binaryService;

    private final static int NEURONES_FIRST_LAYER = 100;
    private final static int LETTER_BITS = 5;
    private final static int PIXELS_BITS = 15;

    public List<Integer> encodeText(String text, int whitePixels, int blackPixels) {
        StringBuilder binaryString = new StringBuilder();

        for (char letter : text.toCharArray()) {
            int letterIndex = getLetterIndex(letter);
            String binaryRepresentation = "0".repeat(LETTER_BITS - Integer.toBinaryString(letterIndex).length()) +
                    Integer.toBinaryString(letterIndex);

            binaryString.append(binaryRepresentation);
        }

        String whiteValue = Integer.toBinaryString(blackPixels);
        String blackValue = Integer.toBinaryString(blackPixels);

        return binaryService.convertToBinaryVector(
                "0".repeat(PIXELS_BITS - whiteValue.length()) + whiteValue +
                        "0".repeat(PIXELS_BITS - blackValue.length()) + blackValue +
                        "0".repeat(NEURONES_FIRST_LAYER - binaryString.length()) + binaryString.toString()
        );
    }

    private int getLetterIndex(char letter) {
        if (letter >= 'a' && letter <= 'z') {
            return letter - 'a' + 1;
        }

        throw new IllegalArgumentException("Недопустимый символ: " + letter);
    }

    public String decodeText(List<List<Integer>> binaryText) {
        binaryText.set(0, binaryText.get(0).subList(30, binaryText.get(0).size()));
        List<List<Integer>> subBinary = Stream.iterate(0, i -> i + LETTER_BITS)
                .limit(NEURONES_FIRST_LAYER / LETTER_BITS)
                .map(i -> binaryText.get(0).subList(i, Math.min(i + LETTER_BITS, NEURONES_FIRST_LAYER)))
                .toList();

        StringBuilder sourceText = new StringBuilder();

        for (List<Integer> binaryLetter : subBinary) {
            StringBuilder letter = new StringBuilder();

            for (int bite : binaryLetter) {
                letter.append(bite);
            }

            int letterIndex = Integer.parseInt(letter.toString().replaceAll("-1", "0"), 2);

            if (letterIndex > 0) {
                sourceText.append(getLetterByIndex(letterIndex));
            }
        }

        return sourceText.toString();
    }

    private char getLetterByIndex(int index) {
        if (index >= 1 && index <= 26) {
            return (char) ('a' + index - 1);
        }

        throw new RuntimeException("Недопустимый символ: " + index);
    }
}