package com.example.bidirectionalassociativememory.services;

import org.springframework.stereotype.Service;

@Service
public class BidirectionalAssociativeMemory {
    public double activation(double weightedSum) {
        return (Math.pow(Math.E, 2 * weightedSum) - 1) / (Math.pow(Math.E, 2 * weightedSum) + 1);
    }
}