package com.example.relaxationnetwork.controllers;

import com.example.relaxationnetwork.entities.AssociationPair;
import com.example.relaxationnetwork.services.RelaxationNetwork;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RelaxationNetworkController {
    private final RelaxationNetwork relaxationNetwork;

    @PostMapping("/train")
    public List<List<Double>> train(@RequestBody AssociationPair associationPair) {
        return relaxationNetwork.train(associationPair.getInput(), associationPair.getOutput());
    }

    @PostMapping("/forward")
    public List<List<Double>> forward(@RequestBody List<List<Double>> input) {
        return relaxationNetwork.forward(input);
    }

    @PostMapping("/backward")
    public List<List<Double>> backward(@RequestBody List<List<Double>> output) {
        return relaxationNetwork.backward(output);
    }
}