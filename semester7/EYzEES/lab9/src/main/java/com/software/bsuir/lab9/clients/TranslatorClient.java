package com.software.bsuir.lab9.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "translator-model", url = "https://api-inference.huggingface.co/models")
public interface TranslatorClient {
    @PostMapping("/Helsinki-NLP/opus-mt-en-ru")
    ResponseEntity<String> translateTextFromEnglishToRussian(
            @RequestHeader("Authorization") String token,
            @RequestBody String inputs
    );
}
