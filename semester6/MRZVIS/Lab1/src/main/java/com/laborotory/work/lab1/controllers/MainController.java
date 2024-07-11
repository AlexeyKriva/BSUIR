/////////////////////////////////
// Лабораторная работа №1 по дисциплине МРЗвИС
// Выполнена студентами группы 121702 БГУИР Кривецкий А.Э., Костюков В.С.
// Вычисление произведения пары 6-разрядных чисел умножением с младших разрядов со сдвигом частичной суммы вправо
// 29.02.2024

package com.laborotory.work.lab1.controllers;

import com.laborotory.work.lab1.models.*;

import static com.laborotory.work.lab1.models.Pairs.*;
import static com.laborotory.work.lab1.models.Conveyor.*;
import static com.laborotory.work.lab1.models.Result.*;
import static com.laborotory.work.lab1.models.InformationHistory.*;

import com.laborotory.work.lab1.service.BinaryNumberCalculatorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@SessionAttributes("amountOfNumbers")
public class MainController {
    private BinaryNumberCalculatorService calculatorService;
    private List<Pair> checkedPairsOfNumbers;
    public List<Conveyor> conveyor;
    public List<Result> results;
    public List<InformationHistory> informationHistory;
    private int tact;
    private int index;
    private int number;

    @Autowired
    public MainController(BinaryNumberCalculatorService calculatorService) {
        this.calculatorService = calculatorService;
        this.checkedPairsOfNumbers = new ArrayList<>();
        this.conveyor = new ArrayList<>();
        this.results = new ArrayList<>();
        this.informationHistory = new ArrayList<>();
        this.tact = 0;
        this.index = 0;
        this.number = 0;
    }

    @GetMapping("/")
    public String home(){
        clear();
        return "home";
    }

    @PostMapping("/input-numbers")
    public String inputNumbers(Model model, @RequestParam("amountOfNumbers") int amountOfNumbers) {
        if (amountOfNumbers <= 0) {
            return "home";
        }

        List<Pair> pairsOfNumbers = createListOfPairsOfNumbers(amountOfNumbers);
        Pairs pairs = new Pairs(pairsOfNumbers);

        model.addAttribute("pairs", pairs);
        model.addAttribute("amountOfNumbers", amountOfNumbers);

        return "input-numbers";
    }

    @PostMapping("/conveyor")
    public String savePairs(@Valid @ModelAttribute("pairs") Pairs pairsOfNumbers,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "input-numbers";
        }

        checkedPairsOfNumbers = pairsOfNumbers.checkSystem();

        return "redirect:/calculating";
    }

    @GetMapping("/calculating")
    public String calculating(Model model) {
        if (index < checkedPairsOfNumbers.size()) {
            conveyor.add(addToConveyor(checkedPairsOfNumbers.get(index++)));
            informationHistory.add(new InformationHistory(checkedPairsOfNumbers.get(index - 1),
                    new ArrayList<>(), new ArrayList<>()));
        }

        if (conveyor.isEmpty()) {
            clear();
            return "home";
        }
        if (conveyor.get(0).getStage() < 7) {
            conveyor = calculatorService.makeShift(conveyor);
        }

        conveyor = calculatorService.workOfConveyor(conveyor);
        informationHistory = createHistory(informationHistory, conveyor, number);
        System.out.println(conveyor);

        model.addAttribute("currentStep" , conveyor);
        model.addAttribute("pairs", checkedPairsOfNumbers);

        if (conveyor.get(0).getStage() > 6) {
            Conveyor pair = conveyor.remove(0);
            results.add(new Result(saveResults(pair), Integer.parseInt(saveResults(pair), 2), tact));
            number++;
        }

        model.addAttribute("results" , results);
        model.addAttribute("tact", ++tact);

        return "conveyor";
    }

    @GetMapping("/buildGraph")
    public String buildGraph(Model model) {
        model.addAttribute("informationHistory", informationHistory);
        return "build-information-history";
    }

    private void clear() {
        this.checkedPairsOfNumbers = new ArrayList<>();
        this.conveyor = new ArrayList<>();
        this.results = new ArrayList<>();
        this.informationHistory = new ArrayList<>();
        this.tact = 0;
        this.index = 0;
    }
}