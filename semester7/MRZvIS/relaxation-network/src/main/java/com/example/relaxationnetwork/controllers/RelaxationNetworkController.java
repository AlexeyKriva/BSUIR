package com.example.relaxationnetwork.controllers;

import com.example.relaxationnetwork.entities.AssociationPairs;
import com.example.relaxationnetwork.entities.TestAssociationTriple;
import com.example.relaxationnetwork.services.RelaxationNetwork;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RelaxationNetworkController {
    private final RelaxationNetwork relaxationNetwork;

    @PostMapping("/train")
    public List<List<Integer>> train(
            @RequestBody AssociationPairs associationPairs
    ) {
        return relaxationNetwork.train(associationPairs.getRussian(), associationPairs.getEnglish());
    }

    @PostMapping("/forward")
    public Map<String, String> forward(@RequestParam("russian") String russian) {
        return relaxationNetwork.forward(russian);
    }

    @PostMapping("/backward")
    public Map<String, String> backward(@RequestParam("english") String english) {
        return relaxationNetwork.backward(english);
    }

    @PostMapping("/test")
    public void test(@RequestParam("russian") String text) {
        relaxationNetwork.test(text);
    }

    @PostMapping("/test-train-forward")
    public Map<String, List<String>> testTrainAndForward(
            @RequestBody TestAssociationTriple associationTriple
    ) {
        relaxationNetwork.testTrain(associationTriple.getPictures(), associationTriple.getNumbers());
        return relaxationNetwork.testForward(associationTriple.getForward());
    }
}