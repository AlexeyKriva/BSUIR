package com.example.laboratory.controllers;

import com.example.laboratory.dao.OfficeEquipmentDao;
import com.example.laboratory.models.Employee;
import com.example.laboratory.models.OfficeEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/offices")
public class OfficeEquipmentsController {
    private OfficeEquipmentDao officeEquipmentDao;
    @Autowired
    public OfficeEquipmentsController(OfficeEquipmentDao officeEquipmentDao) {
        this.officeEquipmentDao = officeEquipmentDao;
    }

    @GetMapping("/elements")
    public String сompHome(){
        return "offices/elements";
    }

    @GetMapping("/elements/showall")
    public String index(Model model) {
        model.addAttribute("officeEquipments", officeEquipmentDao.index());
        return "offices/showall";
    }

    @GetMapping("/elements/showone/{id}")
    public String showOne(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("officeEquipment", officeEquipmentDao.showOneOfficeEquipment(id));
        return "offices/showone";
    }

    @GetMapping("/elements/form")
    public String create(Model model){
        model.addAttribute("officeEquipment", new OfficeEquipment());
        return "offices/form";
    }

    @PostMapping("/elements")
    public String save(@ModelAttribute("officeEquipment") OfficeEquipment officeEquipment) {
        officeEquipmentDao.save(officeEquipment);
        return "redirect:/offices/elements";
    }

    @GetMapping("/elements/showone/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("officeEquipment", officeEquipmentDao.showOneOfficeEquipment(id));
        return "/offices/edit";
    }

    @PostMapping("/elements/showone/{id}")
    public String editEmployee(@ModelAttribute("officeEquipment") OfficeEquipment officeEquipment, @PathVariable("id") int id) {
        officeEquipmentDao.update(id, officeEquipment);
        return "redirect:/offices/elements";
    }

    @GetMapping("/elements/showone/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        officeEquipmentDao.delete(id);
        return "redirect:/offices/elements";
    }
}
