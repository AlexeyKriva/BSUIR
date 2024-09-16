package org.example.searchserver.services;

import org.example.searchserver.entities.DocumentsResponse;
import org.example.searchserver.entities.MyDocument;
import org.example.searchserver.entities.SearchQuery;

import java.util.List;

public interface SearchService {
    DocumentsResponse getResponseToSearchQuery(List<SearchQuery> searchQueries);
    List<MyDocument> getMyDocumentsBySearchQuery(List<SearchQuery> searchQueries);
}