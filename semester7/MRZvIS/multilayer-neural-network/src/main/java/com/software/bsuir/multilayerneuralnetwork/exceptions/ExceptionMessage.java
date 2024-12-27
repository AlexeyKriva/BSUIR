/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
Вариант 1: Реализовать модель линейной рециркуляционной сети с постоянным коэффициентом обучения с ненормированными весами
*/
package com.software.bsuir.multilayerneuralnetwork.exceptions;

public class ExceptionMessage {
    public static final String FILE_IS_NOT_IMAGE = "{\"error\": \"Such file is not image.\"}";
    public static final String RECIRCULATION_NETWORK_IS_NOT_INIT_MESSAGE = "{\"error\": \"Please, complete init " +
            "recirculation network. The following parameters are not set: %s\"}";
    public static final String IMAGE_RECTANGLE_MESSAGE = "{\"error\": \"There are problems with building rectangle from" +
            " image.\"}";
}
