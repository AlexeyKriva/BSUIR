package com.lab3.interpolationandapproximationofcurves.controllers;

import com.lab3.interpolationandapproximationofcurves.models.Curve;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurvesController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("curve", new Curve());
        return "home";
    }
}
