package com.example.laboratory.controllers;

import com.example.laboratory.dao.*;
import com.example.laboratory.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/computings")
public class ComputingesController {
    private ComputingDao computingDao;
    @Autowired
    public ComputingesController(ComputingDao computingDao) {
        this.computingDao = computingDao;
    }


    @GetMapping("/elements")
    public String сompHome(){
        return "computings/elements";
    }

    @GetMapping("/elements/showall")
    public String index(Model model) {
        model.addAttribute("computings", computingDao.index());
        return "computings/showall";
    }

    @GetMapping("/elements/showone/{id}")
    public String showOne(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("computing", computingDao.showOneComputing(id));
        return "computings/showone";
    }

    @GetMapping("/elements/form")
    public String create(Model model){
        model.addAttribute("computing", new Computing());
        return "computings/form";
    }

    @PostMapping("/elements")
    public String save(@ModelAttribute("computing") Computing computing) {
        computingDao.save(computing);
        return "redirect:/computings/elements";
    }

    @GetMapping("/elements/showone/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("computing", computingDao.showOneComputing(id));
        return "/computings/edit";
    }

    @PostMapping("/elements/showone/{id}")
    public String editComputing(@ModelAttribute("computing") Computing computing, @PathVariable("id") int id) {
        computingDao.update(id, computing);
        return "redirect:/computings/elements";
    }

    @GetMapping("/elements/showone/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        computingDao.delete(id);
        return "redirect:/computings/elements";
    }
}
