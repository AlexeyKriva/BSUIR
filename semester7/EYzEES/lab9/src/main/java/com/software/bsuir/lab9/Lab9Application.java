package com.software.bsuir.lab9;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.vosk.Model;
import org.vosk.Recognizer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.software.bsuir.lab9.clients")
public class Lab9Application {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(Lab9Application.class, args);
    }

}
