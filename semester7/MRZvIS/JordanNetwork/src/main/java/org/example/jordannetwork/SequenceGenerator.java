package org.example.jordannetwork;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SequenceGenerator {
    public List<Long> generateFibonacciByIndices(int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex < 0) {
            throw new IllegalArgumentException("Indices must be non-negative.");
        }
        if (startIndex > endIndex) {
            throw new IllegalArgumentException("Start index cannot be greater than end index.");
        }

        List<Long> fibonacciNumbers = new ArrayList<>();
        long a = 0, b = 1;

        for (int i = 0; i <= endIndex; i++) {
            if (i >= startIndex) {
                fibonacciNumbers.add(a);
            }
            long next = a + b;
            a = b;
            b = next;
        }

        return fibonacciNumbers;
    }
}
