package com.example.relaxationnetwork.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinaryService {
    public String convertFromBinaryVector(List<List<Integer>> currentBinaryNumber) {
        StringBuilder targetBinaryString = new StringBuilder();

        for (List<Integer> row: currentBinaryNumber) {
            for (double number: row) {
                if (number == -1.0) {
                    targetBinaryString.append(0);
                } else {
                    targetBinaryString.append(1);
                }
            }
        }

        return targetBinaryString.toString();
    }

    public List<Integer> convertToBinaryVector(String binaryString) {
        List<Integer> vector = new ArrayList<>();

        for (int i = 0; i < binaryString.length(); i++) {
            vector.add(binaryString.charAt(i) == '1' ? 1 : -1);
        }

        return vector;
    }

    public int getValue(boolean flag) {
        return flag ? 1 : -1;
    }
}