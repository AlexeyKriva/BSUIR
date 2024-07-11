package org.project.searchsystem.controllers;

import org.project.searchsystem.services.StandfordService;
import org.project.searchsystem.services.OstisService;
import org.project.searchsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController {
    UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String checkUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        if (userService.check(email, password)) {
            return "redirect:/searchEngine";
        }

        return "registration";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@RequestParam("nickname") String nickname, @RequestParam("email") String email,
                           @RequestParam("password") String password) {
        userService.save(nickname, email, password);
        return "redirect:/searchEngine";
    }
}
