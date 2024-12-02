package com.software.bsuir.abstractbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AbstractBuilderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbstractBuilderApplication.class, args);
    }

}
