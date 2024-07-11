package com.example.laboratory.controllers;

import com.example.laboratory.dao.PenaltyDao;
import com.example.laboratory.dao.ShiftScheduleDao;
import com.example.laboratory.models.Penalty;
import com.example.laboratory.models.ShiftSchedule;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.laboratory.dao.UserProfileDao;
import com.example.laboratory.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/userProfile")
public class UserProfileController {
    UserProfileDao userProfileDao;
    ShiftScheduleDao shiftScheduleDao;
    PenaltyDao penaltyDao;


    @Autowired
    public UserProfileController(UserProfileDao userProfileDao, ShiftScheduleDao shiftScheduleDao, PenaltyDao penaltyDao) {
        this.userProfileDao = userProfileDao;
        this.shiftScheduleDao = shiftScheduleDao;
        this.penaltyDao = penaltyDao;
    }

    @GetMapping("/user-profile/{emailAddress}")
    public String showUserProfile(Model model, @ModelAttribute("userProfile")UserProfile userProfile,@PathVariable("emailAddress") String emailAddress){
        model.addAttribute("userProfiles", userProfileDao.showOneEmployer(emailAddress+".com"));
        model.addAttribute("shiftSchedules", shiftScheduleDao.showShiftSchedule(emailAddress+".com"));
        return "userProfile/user-profile";
    }

    @GetMapping("/user-profile-employer/{emailAddress}")
    public String showUserProfileEmployer(Model model, @PathVariable("emailAddress") String emailAddress){
        model.addAttribute("userProfiles", userProfileDao.showOneEmployer(emailAddress+".com"));
        model.addAttribute("shiftSchedules", shiftScheduleDao.showShiftSchedule(emailAddress+".com"));
        return "userProfile/user-profile-employer";
    }

    @GetMapping("/employees")
    public String showUserProfile(Model model){
        model.addAttribute("userProfiles", userProfileDao.findEmployees());
        return "userProfile/employees";
    }

    @PostMapping("/user-profile-registration")
    public String save(@ModelAttribute("userProfile")UserProfile userProfile){
        String emailAddress = userProfile.getEmailAddress();
        boolean key = userProfileDao.save(userProfile);
        if (key) return "redirect:/userProfile/user-profile-employer/"+emailAddress;
        return "redirect:/userProfile/user-profile/"+emailAddress;
    }

    @PostMapping("/user-profile-authorization")
    public String check(@ModelAttribute("userProfile")UserProfile userProfile){
        String emailAddress = userProfile.getEmailAddress();
        boolean[] key = userProfileDao.check(userProfile.getEmailAddress(), userProfile.getIsPassword());
        if (key[0] && key[1]) return "redirect:/userProfile/user-profile-employer/"+emailAddress;
        if (key[0] && !key[1]) return "redirect:/userProfile/user-profile/"+emailAddress;
        return "redirect:/";
    }
}
