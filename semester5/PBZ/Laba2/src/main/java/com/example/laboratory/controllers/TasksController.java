package com.example.laboratory.controllers;

import com.example.laboratory.dao.*;
import com.example.laboratory.dao.Output2Dao;
import com.example.laboratory.dao.UnitDao;
import com.example.laboratory.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TasksController {
    private Output1Dao output1Dao;
    private Output2Dao output2Dao;
    @Autowired
    public TasksController(Output1Dao output1Dao, Output2Dao output2Dao, UnitDao unitDao1, EmployeeDao employeeDao) {
        this.output1Dao = output1Dao;
        this.output2Dao = output2Dao;
        this.unitDao = unitDao1;
        this.employeeDao = employeeDao;
    }
    private UnitDao unitDao;
    private EmployeeDao employeeDao;

    @GetMapping("/elements")
    public String сompHome(){
        return "tasks/elements";
    }

    @GetMapping("/elements/showall")
    public String index(Model model) {
        model.addAttribute("units", unitDao.index());
        return "tasks/showall";
    }

    @GetMapping("/elements/showall1")
    public String index1(Model model) {
        model.addAttribute("employees", employeeDao.index());
        return "tasks/showall1";
    }

    @GetMapping("/elements/results/{id}")
    public String view(Model model, @PathVariable("id") int id) {
        model.addAttribute("output1s", output1Dao.index(id));
        return "tasks/joins";
    }

    @GetMapping("/elements/results1/{id}")
    public String view1(Model model, @PathVariable("id") int id) {
        model.addAttribute("output2s", output2Dao.index(id));
        return "tasks/joins1";
    }

    @GetMapping("/elements/showone/{id}")
    public String showOne(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("output1", output1Dao.showoneResult(id));
        return "tasks/showone";
    }

    @GetMapping("/elements/showone1/{id}")
    public String showOne1(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("output2", output2Dao.showoneResult(id));
        return "tasks/showone1";
    }
}
