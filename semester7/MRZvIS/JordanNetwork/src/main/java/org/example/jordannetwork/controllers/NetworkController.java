/*
Лабораторная работа №3 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
Вариант 1: Реализовать модель сети Джордана с логарифмической функцией активации (гиперболический арксинус)
*/
package org.example.jordannetwork.controllers;

import lombok.RequiredArgsConstructor;
import org.example.jordannetwork.network.JordanNetwork;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class NetworkController {
    private final JordanNetwork jordanNetwork;

    @PostMapping("/train")
    public Map<String, String> train() {
        return jordanNetwork.train();
    }
}