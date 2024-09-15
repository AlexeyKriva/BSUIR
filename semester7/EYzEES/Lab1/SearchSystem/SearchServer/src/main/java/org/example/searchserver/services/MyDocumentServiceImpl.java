package org.example.searchserver.services;

import org.example.searchserver.entities.MyDocument;
import org.example.searchserver.entities.MyDocumentDto;
import org.example.searchserver.mappers.MyDocumentMapper;
import org.example.searchserver.repositories.MyDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyDocumentServiceImpl implements MyDocumentService {
    @Autowired
    private MyDocumentRepository myDocumentRepository;
    private final MyDocumentMapper MY_DOCUMENT_MAPPER = MyDocumentMapper.INSTANCE;

    public MyDocument saveMyDocument(MyDocumentDto myDocumentDto) {
        MyDocument savingDocument = MY_DOCUMENT_MAPPER.fromMyDocumentDtoToMyDocument(myDocumentDto);
        return myDocumentRepository.save(savingDocument);
    }
}