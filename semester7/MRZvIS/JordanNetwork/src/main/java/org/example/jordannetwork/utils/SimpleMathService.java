package org.example.jordannetwork.utils;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SimpleMathService {
    public static double roundToPrecision(double value) {
        BigDecimal roundedValue = BigDecimal.valueOf(value);
        return roundedValue.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}