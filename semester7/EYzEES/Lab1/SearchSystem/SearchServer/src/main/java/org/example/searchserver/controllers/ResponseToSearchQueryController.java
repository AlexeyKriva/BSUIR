package org.example.searchserver.controllers;

import org.example.searchserver.entities.ResponseToSearchQuery;
import org.example.searchserver.entities.SearchQuery;
import org.example.searchserver.services.ResponseToSearchQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/search-query", produces = "application/json")
public class ResponseToSearchQueryController {
    @Autowired
    private ResponseToSearchQueryService responseToSearchQueryService;
    @GetMapping
    public ResponseEntity<List<ResponseToSearchQuery>> getResponseOnSearchQuery(@RequestBody SearchQuery searchQuery) {
        return ResponseEntity.ok(responseToSearchQueryService.getResponseToSearchQuery(searchQuery));
    }
}
