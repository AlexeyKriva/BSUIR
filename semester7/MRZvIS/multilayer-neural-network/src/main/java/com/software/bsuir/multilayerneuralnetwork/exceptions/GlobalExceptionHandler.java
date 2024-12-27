/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичем
Вариант 1: Реализовать модель линейной рециркуляционной сети с постоянным коэффициентом обучения с ненормированными весами
*/
package com.software.bsuir.multilayerneuralnetwork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FileIsNotImageException.class)
    public ResponseEntity<String> fileIsNotImageExceptionHandler(FileIsNotImageException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecirculationNetworkIsNotInitException.class)
    public ResponseEntity<String> recirculationNetworkIsNotInitExceptionHandler(
            RecirculationNetworkIsNotInitException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
