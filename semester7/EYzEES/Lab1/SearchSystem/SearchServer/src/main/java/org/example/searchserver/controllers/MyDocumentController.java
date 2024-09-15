package org.example.searchserver.controllers;

import jakarta.validation.Valid;
import org.example.searchserver.entities.MyDocument;
import org.example.searchserver.entities.MyDocumentDto;
import org.example.searchserver.services.MyDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/document", produces = "application/json")
public class MyDocumentController {
    @Autowired
    private MyDocumentService myDocumentService;

    @GetMapping("/{id}")
    public ResponseEntity<MyDocument> getDocument(@PathVariable("id") long id) {
        return ResponseEntity.ok(myDocumentService.getDocumentById(id));
    }

    @GetMapping
    public ResponseEntity<List<MyDocument>> getDocuments() {
        return ResponseEntity.ok(myDocumentService.getAllDocuments());
    }

    @PostMapping
    public ResponseEntity<MyDocument> addNewDocument(@Valid @RequestBody MyDocumentDto myDocumentDto) {
        return ResponseEntity.ok(myDocumentService.saveMyDocument(myDocumentDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MyDocument> updateDocument(@PathVariable("id") long id,
                                                     @RequestBody MyDocumentDto myDocumentDto) {
        return ResponseEntity.ok(myDocumentService.updateDocumentById(id, myDocumentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MyDocument> deleteDocument(@PathVariable("id") long id) {
        myDocumentService.deleteDocumentById(id);
        return ResponseEntity.noContent().build();
    }
}