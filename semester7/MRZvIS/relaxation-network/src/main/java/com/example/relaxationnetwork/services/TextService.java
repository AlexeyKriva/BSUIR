package com.example.relaxationnetwork.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TextService {
    private final BinaryService binaryService;
    private final static int LETTER_BITS = 11;

    public List<List<Integer>> encodeText(String text, int numberOfNeurones) {
        StringBuilder binaryString = new StringBuilder();

        for (char letter : text.toCharArray()) {
            int letterIndex = getLetterIndex(letter);
            String binaryRepresentation = "0".repeat(LETTER_BITS - Integer.toBinaryString(letterIndex).length()) +
                    Integer.toBinaryString(letterIndex);

            binaryString.append(binaryRepresentation);
        }

        List<List<Integer>> textMatrix = new ArrayList<>();
        textMatrix.add(binaryService.convertToBinaryVector(
                "0".repeat(numberOfNeurones - binaryString.length()) + binaryString.toString()
        ));

        return textMatrix;
    }

    public int getLetterIndex(char letter) {
        //System.out.println(letter + " : " + (int) letter);
        return letter;
    }

    public String decodeText(List<List<Integer>> binaryText, int numberOfNeurones) {
        binaryText.set(0, binaryText.get(0).subList(0, binaryText.get(0).size()));
        List<List<Integer>> subBinary = Stream.iterate(0, i -> i + LETTER_BITS)
                .limit(numberOfNeurones / LETTER_BITS)
                .map(i -> binaryText.get(0).subList(i, Math.min(i + LETTER_BITS, numberOfNeurones)))
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
        return (char) index;
    }

    public List<List<Integer>> encodePicture(List<String> picture) {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>());

        for (int i = 0; i < picture.get(0).length(); i++) {
            for (int j = 0; j < picture.size(); j++) {
                matrix.get(0).add(picture.get(j).charAt(i) == '+' ? 1: -1);
            }
        }

        return matrix;
    }

    public List<String> decodePicture(List<List<Integer>> matrix) {
        List<String> picture = new ArrayList<>();


        for (int i = 0; i < matrix.get(0).size() / 3; i++) {
            StringBuilder str = new StringBuilder();

            str.append(matrix.get(0).get(i) == 1 ? "+" : "-");
            str.append(matrix.get(0).get(i + 5) == 1 ? "+" : "-");
            str.append(matrix.get(0).get(i + 10) == 1 ? "+" : "-");
            picture.add(str.toString());
        }

        return picture;
    }

    public List<List<Integer>> toBinary(String number) {
        String binary = Integer.toBinaryString(Integer.parseInt(number));

        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>());

        if (number.equals("1")) {
            matrix.get(0).add(-1);
            matrix.get(0).add(-1);
        } else if (number.equals("2") || number.equals("3")){
            matrix.get(0).add(-1);
        }

        for (char num: binary.toCharArray()) {
            matrix.get(0).add(num == '1'? 1: -1);
        }

        return matrix;
    }

    public String fromBinary(List<List<Integer>> matrix) {
        StringBuilder str = new StringBuilder();

        for (List<Integer> row: matrix) {
            for (int number: row) {
                str.append(number == 1 ? "1": "0");
            }
        }

        return String.valueOf(Integer.parseInt(str.toString(), 2));
    }
}