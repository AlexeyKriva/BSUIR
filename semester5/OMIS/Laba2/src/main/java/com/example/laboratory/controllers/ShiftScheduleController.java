package com.example.laboratory.controllers;

import com.example.laboratory.dao.PenaltyDao;
import com.example.laboratory.dao.ShiftScheduleDao;
import com.example.laboratory.dao.UserProfileDao;
import com.example.laboratory.models.ShiftSchedule;
import com.example.laboratory.models.HelpShiftSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.StringJoiner;

@Controller
@RequestMapping("/shiftSchedule")
public class ShiftScheduleController {
    ShiftScheduleDao shiftScheduleDao;

    @Autowired
    public ShiftScheduleController(ShiftScheduleDao shiftScheduleDao) {
        this.shiftScheduleDao = shiftScheduleDao;
    }
    @GetMapping("/shift-schedule")
    public String change(Model model){
        model.addAttribute("helpShiftSchedule", new HelpShiftSchedule());
        return "shiftSchedule/shift-schedule";
    }

    @PostMapping("/commit-changes")
    public String commitChanges(@ModelAttribute("helpShiftSchedule") HelpShiftSchedule shiftSchedule){
//        String workDays = shiftSchedule.getWorkDays();
//        String startTime = shiftSchedule.getStartTime();
//        String finishTime = shiftSchedule.getFinishTime();
//        System.out.println("1");
//        String[] daysArray = workDays.split(", "); // Разделяем строку по запятой с пробелом
//
//        StringJoiner arrayJoiner = new StringJoiner(", ", "", "");
//
//        for (String day : daysArray) {
//            arrayJoiner.add("'" + day + "'");
//        }
//        System.out.println(arrayJoiner);
//        String[] daysArray1 = startTime.split(", "); // Разделяем строку по запятой с пробелом
//
//        StringJoiner arrayJoiner1 = new StringJoiner(", ", "", "");
//
//        for (String day : daysArray1) {
//            arrayJoiner1.add("'" + day + "'");
//        }
//        System.out.println(arrayJoiner1);
//        String[] daysArray2 = finishTime.split(", "); // Разделяем строку по запятой с пробелом
//
//        StringJoiner arrayJoiner2 = new StringJoiner(", ", "", "");
//
//        for (String day : daysArray2) {
//            arrayJoiner2.add("'" + day + "'");
//        }
//        System.out.println(arrayJoiner2);
//        shiftScheduleDao.save(shiftSchedule);
        return "redirect:userProfile/user-profile-employer";
    }
}
