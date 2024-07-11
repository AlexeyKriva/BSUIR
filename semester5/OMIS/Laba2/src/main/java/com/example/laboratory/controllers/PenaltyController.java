package com.example.laboratory.controllers;

import com.example.laboratory.dao.PenaltyDao;
import com.example.laboratory.dao.ShiftScheduleDao;
import com.example.laboratory.dao.UserProfileDao;
import com.example.laboratory.models.Penalty;
import com.example.laboratory.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/penalty")
public class PenaltyController {
    PenaltyDao penaltyDao;
    @Autowired
    public PenaltyController(PenaltyDao penaltyDao) {
        this.penaltyDao = penaltyDao;
    }
    @GetMapping("/penaltyes")
    public String showPenalty(Model model){
        model.addAttribute("penaltyes", penaltyDao.showPenalty());
        model.addAttribute("penalty", new Penalty());
        return "penalty/penaltyes";
    }
    @GetMapping("/penaltyes1")
    public String showPenalty1(Model model){
        model.addAttribute("penaltyes", penaltyDao.showPenalty());
        model.addAttribute("penalty", new Penalty());
        return "penalty/penaltyes1";
    }
    @PostMapping("/penaltyes")
    public String savePenalty(@ModelAttribute("penalty") Penalty penalty){
        penaltyDao.save(penalty);
        return "redirect:/penalty/penaltyes";
    }
}
