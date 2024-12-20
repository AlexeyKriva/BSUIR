package com.software.bsuir.multilayerneuralnetwork.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompressionMetrics {
    private double totalError;
    private double compressionRate;
}