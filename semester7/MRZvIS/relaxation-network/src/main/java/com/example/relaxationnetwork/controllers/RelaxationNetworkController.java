/*
Лабораторная работа №2 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
Вариант 2: Реализовать модель двунаправленной ассоциативной памяти
*/
package com.example.relaxationnetwork.controllers;

import com.example.relaxationnetwork.entities.AssociationPairs;
import com.example.relaxationnetwork.entities.Backward;
import com.example.relaxationnetwork.entities.Forward;
import com.example.relaxationnetwork.services.RelaxationNetwork;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RelaxationNetworkController {
    private final RelaxationNetwork relaxationNetwork;

    @PostMapping("/train")
    public List<List<Integer>> train(
            @RequestBody AssociationPairs associationPairs
    ) {
        return relaxationNetwork.train(associationPairs.getPictures(), associationPairs.getNumbers());
    }

    @PostMapping("/forward")
    public Map<String, List<String>> forward(@RequestBody Forward picture) {
        return relaxationNetwork.forward(picture.getPicture());
    }

    @PostMapping("/backward")
    public Map<String, List<String>> backward(@RequestBody Backward number) {
        return relaxationNetwork.backward(number.getNumber());
    }

    @PostMapping("/test")
    public void test(@RequestParam("image") MultipartFile image) {
        relaxationNetwork.test(image);
    }
}