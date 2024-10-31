package org.example.languageguide

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class LanguageGuideApplication

fun main(args: Array<String>) {
    runApplication<LanguageGuideApplication>(*args)
}