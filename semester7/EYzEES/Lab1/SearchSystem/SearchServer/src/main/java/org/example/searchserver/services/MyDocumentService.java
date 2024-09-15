package org.example.searchserver.services;

import org.example.searchserver.entities.DocumentsResponse;
import org.example.searchserver.entities.MyDocument;
import org.example.searchserver.entities.MyDocumentDto;
import org.example.searchserver.entities.SearchQuery;

import java.util.List;

public interface MyDocumentService {
    MyDocument saveDocument(MyDocumentDto myDocumentDto);
    MyDocument updateDocumentById(long id, MyDocumentDto myDocumentDto);
    void deleteDocumentById(long id);
    MyDocument getDocumentById(long id);
    List<MyDocument> getAllDocuments();
    DocumentsResponse getResponseToSearchQuery(List<SearchQuery> searchQueries);
}