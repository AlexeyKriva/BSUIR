package com.example.relaxationnetwork.controllers;

import com.example.relaxationnetwork.services.RelaxationNetwork;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RelaxationNetworkController {
    private final RelaxationNetwork relaxationNetwork;

    @PostMapping("/train")
    public List<List<Integer>> train(
            @RequestParam("image") MultipartFile image,
            @RequestParam("text") String text
    ) {
        return relaxationNetwork.train(image, text);
    }

    @PostMapping("/forward")
    public Map<String, String> forward(@RequestParam("image") MultipartFile image) {
        return relaxationNetwork.forward(image);
    }

    @PostMapping("/backward")
    public List<List<Integer>> backward(@RequestBody List<List<Integer>> output) {
        return relaxationNetwork.backward(output);
    }

    @PostMapping("/test")
    public void test(@RequestParam("image") MultipartFile image) {
        relaxationNetwork.test(image);
    }
}