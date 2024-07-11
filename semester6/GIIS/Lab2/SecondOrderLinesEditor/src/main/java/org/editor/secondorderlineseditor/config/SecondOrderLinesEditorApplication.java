package org.editor.secondorderlineseditor.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.editor")
public class SecondOrderLinesEditorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondOrderLinesEditorApplication.class, args);
    }

}
