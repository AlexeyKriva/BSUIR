package com.example.laboratory.controllers;

import com.example.laboratory.models.Profiles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String authorization(Model model) {
        model.addAttribute("profile", new Profiles());
        return "authorization";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("profile", new Profiles());
        return "registration";
    }
}
