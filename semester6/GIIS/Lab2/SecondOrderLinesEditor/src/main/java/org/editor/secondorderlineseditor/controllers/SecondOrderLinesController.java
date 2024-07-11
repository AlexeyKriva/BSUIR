package org.editor.secondorderlineseditor.controllers;

import org.editor.secondorderlineseditor.models.SecondOrderLine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SecondOrderLinesController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("secondOrderLine", new SecondOrderLine());
        return "home";
    }
}
