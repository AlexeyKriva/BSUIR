package org.example.uploadingfilesserver.runner;

import lombok.AllArgsConstructor;
import org.example.uploadingfilesserver.services.MyDocumentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppRunner implements CommandLineRunner {
    private MyDocumentService myDocumentService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Служба документирования запущена. Ожидание файлов...");
    }
}
