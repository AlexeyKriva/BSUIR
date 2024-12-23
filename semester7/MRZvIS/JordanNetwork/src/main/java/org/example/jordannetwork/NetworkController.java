package org.example.jordannetwork;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class NetworkController {
    private final JordanNetwork jordanNetwork;

    @PostMapping("/train")
    public Map<Double, Double> train() {
        return jordanNetwork.train();
    }
}