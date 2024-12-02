/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичес
Вариант 1: Реализовать модель линейной рециркуляционной сети с постоянным коэффициентом обучения с ненормированными весами
*/
package com.software.bsuir.multilayerneuralnetwork.controllers;

import com.software.bsuir.multilayerneuralnetwork.entities.*;
import com.software.bsuir.multilayerneuralnetwork.services.RecirculationNetwork;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/images")
@RequiredArgsConstructor
public class ImageController {
    private final RecirculationNetwork recirculationNetwork;

    @PostMapping("/init")
    public ResponseEntity<Void> initSystem(
            @RequestParam("numberOfEpochs") Integer numberOfEpochs,
            @RequestParam("learningRate") Double learningRate,
            @RequestParam("numRows") Integer numRows,
            @RequestParam("numCols") Integer numCols
    ) {
        recirculationNetwork.initRecirculationNetwork(numberOfEpochs, learningRate, numRows, numCols);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/trains/compress")
    public ResponseEntity<Double> trainCompressImage(
            @RequestParam("image") MultipartFile image
    ) {
        return ResponseEntity.ok(recirculationNetwork.trainToCompressImage(image));
    }

    @PostMapping("/compress")
    public ResponseEntity<Double> compressImage(
            @RequestParam("image") MultipartFile image
    ) {
        return ResponseEntity.ok(recirculationNetwork.compressImage(image));
    }
}
