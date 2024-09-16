package org.example.searchserver.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.example.searchserver.entities.*;
import org.example.searchserver.mappers.MyDocumentMapper;
import org.example.searchserver.repositories.MyDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyDocumentServiceImpl implements MyDocumentService {
    @Autowired
    private MyDocumentRepository myDocumentRepository;
    private final MyDocumentMapper MY_DOCUMENT_MAPPER = MyDocumentMapper.INSTANCE;

    public MyDocument getDocumentById(long id) {
        Optional<MyDocument> documentFromDb = myDocumentRepository.findById(id);
        if (documentFromDb.isPresent()) {
            return documentFromDb.get();
        }

        throw new RuntimeException("Document not found");
    }

    public List<MyDocument> getAllDocuments() {
        return myDocumentRepository.findAll();
    }

    public MyDocument saveDocument(MyDocumentDto myDocumentDto) {
        MyDocument savingDocument = MY_DOCUMENT_MAPPER.getFromMyDocumentDtoToMyDocument(myDocumentDto);
        return myDocumentRepository.save(savingDocument);
    }

    public MyDocument updateDocumentById(long id, MyDocumentDto myDocumentDto) {
        Optional<MyDocument> documentFromDb = myDocumentRepository.findById(id);
        if (documentFromDb.isPresent()) {
            MyDocument updatingDocument = documentFromDb.get();
            MY_DOCUMENT_MAPPER.updateFromMyDocumentDtoToMyDocument(myDocumentDto, updatingDocument);

            return myDocumentRepository.save(updatingDocument);
        }

        throw new RuntimeException("Document not found");
    }

    public void deleteDocumentById(long id) {
        if (myDocumentRepository.existsById(id)) {
            myDocumentRepository.deleteById(id);
        }
    }
}