package org.example.searchserver.controllers;

import org.example.searchserver.entities.MyDocument;
import org.example.searchserver.entities.MyDocumentDto;
import org.example.searchserver.services.MyDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/document", produces = "application/json")
public class MyDocumentController {
    @Autowired
    private MyDocumentService myDocumentService;
    @PostMapping
    public ResponseEntity<MyDocument> addNewDocument(@RequestBody MyDocumentDto myDocumentDto) {
        return ResponseEntity.ok(myDocumentService.saveMyDocument(myDocumentDto));
    }
}