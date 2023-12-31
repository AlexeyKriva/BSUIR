package com.example.laboratory.controllers;

import com.example.laboratory.models.UserProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomesController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/authorization")
    public String authorization(Model model) {
        model.addAttribute("userProfile", new UserProfile());
        return "authorization";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userProfile", new UserProfile());
        return "registration";
    }
}
