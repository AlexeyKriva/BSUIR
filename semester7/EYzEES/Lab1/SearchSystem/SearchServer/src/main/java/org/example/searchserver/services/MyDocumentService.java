package org.example.searchserver.services;

import org.example.searchserver.entities.MyDocument;
import org.example.searchserver.entities.MyDocumentDto;
import org.springframework.stereotype.Service;

public interface MyDocumentService {
    MyDocument saveMyDocument(MyDocumentDto myDocumentDto);
}