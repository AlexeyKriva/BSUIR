package org.example.ontology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.example")
public class OntologyApplication {
    public static void main(String[] args) {
        SpringApplication.run(OntologyApplication.class, args);
    }
}