package com.example.laboratory.controllers;

import com.example.laboratory.dao.UnitDao;
import com.example.laboratory.models.OfficeEquipment;
import com.example.laboratory.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/units")
public class UnitsController {
    private UnitDao unitDao;
    @Autowired
    public UnitsController(UnitDao unitDao) {
        this.unitDao = unitDao;
    }

    @GetMapping("/elements")
    public String сompHome(){
        return "units/elements";
    }

    @GetMapping("/elements/showall")
    public String index(Model model) {
        model.addAttribute("units", unitDao.index());
        return "units/showall";
    }

    @GetMapping("/elements/showone/{id}")
    public String showOne(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("unit", unitDao.showOneUnit(id));
        return "units/showone";
    }

    @GetMapping("/elements/form")
    public String create(Model model){
        model.addAttribute("unit", new Unit());
        return "units/form";
    }

    @PostMapping("/elements")
    public String save(@ModelAttribute("unit") Unit unit) {
        unitDao.save(unit);
        return "redirect:/units/elements";
    }

    @GetMapping("/elements/showone/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("unit", unitDao.showOneUnit(id));
        return "/units/edit";
    }

    @PostMapping("/elements/showone/{id}")
    public String editEmployee(@ModelAttribute("officeEquipment") Unit unit, @PathVariable("id") int id) {
        unitDao.update(id, unit);
        return "redirect:/units/elements";
    }

    @GetMapping("/elements/showone/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        unitDao.delete(id);
        return "redirect:/units/elements";
    }
}




















