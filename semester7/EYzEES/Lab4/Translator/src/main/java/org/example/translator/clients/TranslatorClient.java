package org.example.translator.clients;

import org.example.translator.entities.TranslationText;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "translator-model")
public interface TranslatorClient {
    @PostMapping("/Helsinki-NLP/opus-mt-en-de")
    ResponseEntity<List<TranslationText>> translateTextFromEnglishToDeutsch(
            @RequestHeader("Authorization") String token,
            @RequestBody String inputs
    );

    @PostMapping("/Helsinki-NLP/opus-mt-de-en")
    ResponseEntity<List<TranslationText>> translateTextFromDeutschToEnglish(
            @RequestHeader("Authorization") String token,
            @RequestBody String inputs
    );
}
