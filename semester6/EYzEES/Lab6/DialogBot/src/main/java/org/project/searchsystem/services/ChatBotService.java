package org.project.searchsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatBotService {
    private static final String API_URL = "https://api-inference.huggingface.co/models/deepset/roberta-base-squad2";
    private static final String API_TOKEN = "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";
    private final RestTemplate restTemplate;
    private ScanTextFromFileService scanTextFromFileService;
    private static final int SKIP_SYMBOLS = 9;
    private static final int LAST_NOT_NEEDS_SYMBOLS = 2;
    private static final String ERROR_RESPONSE = "Sorry, I don't know the answer for this question";

    @Autowired
    public ChatBotService(RestTemplate restTemplate, ScanTextFromFileService scanTextFromFileService) {
        this.restTemplate = restTemplate;
        this.scanTextFromFileService = scanTextFromFileService;
    }

    public String getChatBotResponse(String request) {
        String textBase = scanTextFromFileService.readFile();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(new ChatBotRequest(request, textBase));
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        ResponseEntity<String> responseEntity;
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        try {
            responseEntity = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);
        } catch (Exception e) {
            return ERROR_RESPONSE;
        }

        String response = responseEntity.getBody();
        return createCorrectResponse(response);
    }

    private String createCorrectResponse(String response) {
        if (response.indexOf("answer") == -1 || response.indexOf("answer") + SKIP_SYMBOLS > response.length() - LAST_NOT_NEEDS_SYMBOLS) {
            return ERROR_RESPONSE;
        }
        return response.substring(response.indexOf("answer") + SKIP_SYMBOLS, response.length() - LAST_NOT_NEEDS_SYMBOLS);
    }

    @Data
    private static class ChatBotRequest {
        private final String question;
        private final String context;

        public ChatBotRequest(String question, String context) {
            this.question = question;
            this.context = context;
        }

        public String getQuestion() {
            return question;
        }

        public String getContext() {
            return context;
        }
    }
}