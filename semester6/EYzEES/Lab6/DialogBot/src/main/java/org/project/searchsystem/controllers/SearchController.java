package org.project.searchsystem.controllers;

import org.project.searchsystem.repositories.QuestionHistoryRepository;
import org.project.searchsystem.services.ChatBotService;
import org.project.searchsystem.services.OstisService;
import org.project.searchsystem.services.ScanTextFromFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchController {
    private OstisService ostisService;
    private ChatBotService chatGptService;
    private QuestionHistoryRepository questionHistoryRepository;
    private static final String RESPONSE_FOR_EMPTY_REQUEST = "Ask me question";


    @Autowired
    public SearchController(OstisService ostisService, ChatBotService chatGptService,
                            QuestionHistoryRepository questionHistoryRepository) {
        this.ostisService = ostisService;
        this.chatGptService = chatGptService;
        this.questionHistoryRepository = questionHistoryRepository;
    }

    @GetMapping("/searchEngine")
    public String searchEngine(Model model) {
        model.addAttribute("questions", ostisService.getHistoryQuestions());

        return "search-engine";
    }

    @PostMapping("/searchEngine")
    @ResponseBody
    public String getAnswer(Model model, @RequestBody String prompt) {
        model.addAttribute("questions", ostisService.getHistoryQuestions());
        String response = chatGptService.getChatBotResponse(prompt);
        questionHistoryRepository.saveQuestion(prompt, response);
        return response;
    }
}