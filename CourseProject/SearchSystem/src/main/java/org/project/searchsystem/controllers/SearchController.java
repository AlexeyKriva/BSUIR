package org.project.searchsystem.controllers;

import org.project.searchsystem.repositories.QuestionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Controller
public class SearchController {
    private QuestionHistoryRepository questionHistoryRepository;
    private RestTemplate restTemplate;

    private static final String PYTHON_SERVER_URL = "http://local:8347/requestToOstis";

    @Autowired
    public SearchController(QuestionHistoryRepository questionHistoryRepository, RestTemplate restTemplate) {
        this.questionHistoryRepository = questionHistoryRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/searchEngine")
    public String searchEngine(Model model) {
        model.addAttribute("questions", questionHistoryRepository.selectQuestions());

        return "search-engine";
    }

    @PostMapping("/searchEngine")
    @ResponseBody
    public String getAnswer(Model model, @RequestBody String request) {
        model.addAttribute("questions", questionHistoryRepository.selectQuestions());
        ResponseEntity<List<String>> response = restTemplate.exchange(PYTHON_SERVER_URL, HttpMethod.POST,
                new HttpEntity<>(request), new ParameterizedTypeReference<List<String>>() {});
//        String response = chatGptService.getChatBotResponse(request);
//        questionHistoryRepository.saveQuestion(request, response);
//        return response;
        return "Hello";
    }
}