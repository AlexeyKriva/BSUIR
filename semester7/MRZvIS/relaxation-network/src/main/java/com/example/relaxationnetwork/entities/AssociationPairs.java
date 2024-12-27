/*
Лабораторная работа №2 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
Вариант 2: Реализовать модель двунаправленной ассоциативной памяти
*/
package com.example.relaxationnetwork.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationPairs {
    private List<List<String>> pictures;
    private List<String> numbers;
}
