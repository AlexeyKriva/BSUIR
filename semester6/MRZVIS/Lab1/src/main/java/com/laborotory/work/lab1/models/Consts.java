/////////////////////////////////
// Лабораторная работа №1 по дисциплине МРЗвИС
// Выполнена студентами группы 121702 БГУИР Кривецкий А.Э., Костюков В.С.
// Вычисление произведения пары 6-разрядных чисел умножением с младших разрядов со сдвигом частичной суммы вправо
// 29.02.2024

package com.laborotory.work.lab1.models;

import lombok.Data;

@Data
public class Consts {
    public static final String BINARY_NUMBER = "[01]{1,6}";
    public static final String MODEL_OF_NUMBER = "^([01]{1,6})$";
    public static final String HINT = "Введите число от 0 до 111111 в двоичной системе";
}