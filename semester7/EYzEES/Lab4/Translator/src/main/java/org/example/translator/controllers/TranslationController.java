package org.example.translator.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.translator.entities.Response;
import org.example.translator.entities.TranslationDto;
import org.example.translator.services.TranslationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8091")
public class TranslationController {
    private final TranslationService translationService;

    @PostMapping("/translate")
    public ResponseEntity<Response> translateText(
            @Valid @RequestBody TranslationDto translationDto
            ) {
        return new ResponseEntity<>(translationService.translateSourceText(translationDto.source(),
                translationDto.from(), translationDto.to()), HttpStatus.OK);
    }
}