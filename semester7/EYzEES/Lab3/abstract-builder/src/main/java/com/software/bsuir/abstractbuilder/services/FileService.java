package com.software.bsuir.abstractbuilder.services;

import com.software.bsuir.abstractbuilder.entities.AbstractResponse;
import com.software.bsuir.abstractbuilder.entities.FileProperty;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    @Value("${full.file-storage.path}")
    private String fullFileStoragePath;

    private final String ADDITIONAL_PATH = "/abstract";

    private final String PATH = "/Users/aliaksei/Desktop/BSUIR/semester7/EYzEES/Lab3/abstract-builder/files/";

    @SneakyThrows
    public List<FileProperty> processTxtFiles(List<MultipartFile> multipartFiles) {
        List<FileProperty> fileProperties = new ArrayList<>();

        for (MultipartFile multipartFile: multipartFiles) {
            fileProperties.add(processTxtFile(multipartFile));
        }

        return fileProperties;
    }

    @SneakyThrows
    public FileProperty processTxtFile(MultipartFile multipartFile) {
        String content = new String(Files.readAllBytes(Paths.get(PATH + multipartFile.getOriginalFilename())));

        return new FileProperty(content);
    }

    @SneakyThrows
    public AbstractResponse saveAbstractResponseInFile(AbstractResponse abstractResponse) {
        Files.write(Path.of(fullFileStoragePath + ADDITIONAL_PATH + LocalDateTime.now()),
                ("Source:\n" + abstractResponse.getSource() +
                        "\nBuilt abstract:\n" + abstractResponse.getGeneralAbstract()
                        .getSummaryText()).getBytes()
        );

        return abstractResponse;
    }
}
