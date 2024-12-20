/*
Лабораторная работа №1 по дисциплине МРЗВИС
Выполнена студентом группы 121702 БГУИР Кривецким Алексеем Эдуардовичес
Вариант 1: Реализовать модель линейной рециркуляционной сети с постоянным коэффициентом обучения с ненормированными весами
*/
package com.software.bsuir.multilayerneuralnetwork.controllers;

import com.software.bsuir.multilayerneuralnetwork.entities.CompressionMetrics;
import com.software.bsuir.multilayerneuralnetwork.services.RecirculationNetwork;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        recirculationNetwork.initNetwork(numberOfEpochs, learningRate, numRows, numCols);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/train-compression")
    public ResponseEntity<CompressionMetrics> trainCompressImage(
            @RequestParam("image") MultipartFile image
    ) {
        return ResponseEntity.ok(recirculationNetwork.trainToCompressAndRestore(image));
    }

    @PostMapping("/compress")
    public ResponseEntity<CompressionMetrics> compressImage(
            @RequestParam("image") MultipartFile image
    ) {
        return ResponseEntity.ok(recirculationNetwork.compressAndRestore(image));
    }
}
