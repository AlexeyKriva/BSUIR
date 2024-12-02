package com.software.bsuir.lab9.controllers;

import com.software.bsuir.lab9.services.MicrophoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MicrophoneController {
    private final MicrophoneService microphoneService;

    @PostMapping("/launch")
    public ResponseEntity<String> launch() {
        return ResponseEntity.ok(microphoneService.launch());
    }
}