package com.example.laboratory.controllers;

import com.example.laboratory.dao.EmployeeDao;
import com.example.laboratory.models.Computing;
import com.example.laboratory.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/employees")
public class EmployeesController {
    private EmployeeDao employeeDao;
    @Autowired
    public EmployeesController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @GetMapping("/elements")
    public String сompHome(){
        return "employees/elements";
    }

    @GetMapping("/elements/showall")
    public String index(Model model) {
        model.addAttribute("employees", employeeDao.index());
        return "employees/showall";
    }

    @GetMapping("/elements/showone/{id}")
    public String showOne(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("employee", employeeDao.showOneEmployee(id));
        return "employees/showone";
    }

    @GetMapping("/elements/form")
    public String create(Model model){
        model.addAttribute("employee", new Employee());
        return "employees/form";
    }

    @PostMapping("/elements")
    public String save(@ModelAttribute("employee") Employee employee) {
        employeeDao.save(employee);
        return "redirect:/employees/elements";
    }

    @GetMapping("/elements/showone/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeeDao.showOneEmployee(id));
        return "/employees/edit";
    }

    @PostMapping("/elements/showone/{id}")
    public String editEmployee(@ModelAttribute("employee") Employee employee, @PathVariable("id") int id) {
        employeeDao.update(id, employee);
        return "redirect:/employees/elements";
    }

    @GetMapping("/elements/showone/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        employeeDao.delete(id);
        return "redirect:/employees/elements";
    }
}
