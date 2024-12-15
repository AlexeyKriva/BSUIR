package com.example.relaxationnetwork.entities;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class AssociationPair {
    private List<List<Double>> input;
    private List<List<Double>> output;

    public AssociationPair(List<List<Double>> input, List<List<Double>> output) {
        this.input = input;
        this.output = output;
    }

    public AssociationPair() {}

    public List<List<Double>> getInput() {
        return input;
    }

    public void setInput(List<List<Double>> input) {
        this.input = input;
    }

    public List<List<Double>> getOutput() {
        return output;
    }

    public void setOutput(List<List<Double>> output) {
        this.output = output;
    }
}
