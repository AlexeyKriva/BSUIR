package org.example.searchserver.controllers;

import jakarta.validation.Valid;
import org.example.searchserver.entities.MyDocument;
import org.example.searchserver.entities.MyDocumentDto;
import org.example.searchserver.entities.DocumentsResponse;
import org.example.searchserver.entities.SearchQuery;
import org.example.searchserver.services.MyDocumentService;
import org.example.searchserver.services.MyDocumentServiceImpl;
import org.example.searchserver.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/document", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8081")
public class MyDocumentController {
    @Autowired
    private MyDocumentServiceImpl myDocumentService;
    @Autowired
    private SearchService searchService;

    @GetMapping("/{id}")
    public ResponseEntity<MyDocument> getDocument(@PathVariable("id") long id) {
        return ResponseEntity.ok(myDocumentService.getDocumentById(id));
    }

    @GetMapping
    public ResponseEntity<List<MyDocument>> getDocuments() {
        return ResponseEntity.ok(myDocumentService.getAllDocuments());
    }

    @PostMapping
    public ResponseEntity<MyDocument> addNewDocument(@RequestBody MyDocumentDto myDocumentDto) {
        System.out.println("-----------------------------------------------------------");
        System.out.println(myDocumentDto);
        System.out.println("-----------------------------------------------------------");
        return ResponseEntity.ok(myDocumentService.saveDocument(myDocumentDto));
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

    @PostMapping("/with-properties")
    public ResponseEntity<DocumentsResponse> getResponseOnSearchQuery(@RequestBody List<SearchQuery> searchQuery) {
        DocumentsResponse documentsResponse = searchService.getResponseToSearchQuery(searchQuery);
        documentsResponse.print();
        return ResponseEntity.ok(documentsResponse);
    }
}