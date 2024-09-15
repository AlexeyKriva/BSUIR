package org.example.searchserver.services;

import org.example.searchserver.entities.MyDocument;
import org.example.searchserver.entities.MyDocumentDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MyDocumentService {
    MyDocument saveMyDocument(MyDocumentDto myDocumentDto);
    MyDocument updateDocumentById(long id, MyDocumentDto myDocumentDto);
    void deleteDocumentById(long id);
    MyDocument getDocumentById(long id);
    List<MyDocument> getAllDocuments();
}