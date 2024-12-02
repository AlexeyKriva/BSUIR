package com.software.bsuir.abstractbuilder.controllers;

import com.software.bsuir.abstractbuilder.entities.AbstractResponse;
import com.software.bsuir.abstractbuilder.entities.AbstractType;
import com.software.bsuir.abstractbuilder.entities.GeneralAbstract;
import com.software.bsuir.abstractbuilder.services.AbstractBuilderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8113")
public class AbstractBuilderController {
    private final AbstractBuilderService abstractBuilderService;

    @PostMapping("/abstracts")
    public ResponseEntity<List<AbstractResponse>> getAbstractByFiles(
            @RequestParam(value = "files", required = false) List<MultipartFile> multipartFiles,
            @RequestParam("type") AbstractType abstractType
    ) {
        System.out.println(abstractType);
        return new ResponseEntity<>(abstractBuilderService.buildAbstractFromFilesWithType(multipartFiles,
                abstractType), HttpStatus.CREATED);
    }
}