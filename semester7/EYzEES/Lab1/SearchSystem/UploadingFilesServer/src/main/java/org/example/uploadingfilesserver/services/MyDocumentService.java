package org.example.uploadingfilesserver.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.example.uploadingfilesserver.entities.MyDocument;
import org.example.uploadingfilesserver.entities.MyDocumentDto;
import org.example.uploadingfilesserver.entities.UserDocumentVersion;
import org.example.uploadingfilesserver.mappers.MyDocumentMapper;
import org.example.uploadingfilesserver.repositories.MyDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyDocumentService {
    private MyDocumentRepository documentRepository;
    private FindStorageService findStorageService;
    private UserDocumentVersionService userDocumentVersionService;

    public MyDocumentService(MyDocumentRepository documentRepository, FindStorageService findStorageService,
                             UserDocumentVersionService userDocumentVersionService) {
        this.documentRepository = documentRepository;
        this.findStorageService = findStorageService;
        this.userDocumentVersionService = userDocumentVersionService;
    }

    private final ObjectMapper OBJECT_MAPPER;

    private final MyDocumentMapper MY_DOCUMENT_MAPPER;

    {
        MY_DOCUMENT_MAPPER = MyDocumentMapper.INSTANCE;
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    @Scheduled(fixedRate = 30000)
    public void extractFiles() throws IOException {
        List<File> documentsFromStorage = findStorageService.findAndGetDocumentsFromStorage();

        for (File file: documentsFromStorage) {
            MyDocumentDto userDocument = OBJECT_MAPPER.readValue(file, MyDocumentDto.class);

            if (userDocumentVersionService.isNewOrUpdatedDocument(userDocument)) {
                MyDocument document = MY_DOCUMENT_MAPPER.fromMyDocumentDtoToMyDocument(userDocument);

                if (documentRepository.existsByTitle(document.getTitle())) {
                    Optional<MyDocument> documentFromDb = documentRepository.findByTitle(document.getTitle());

                    document.setId(documentFromDb.get().getId());

                    documentRepository.save(document);
                } else {
                    documentRepository.save(document);
                }

                //documentRepository.save(document);

                UserDocumentVersion userDocumentVersion = UserDocumentVersion.builder()
                        .title(document.getTitle())
                        .version(userDocument.getVersion())
                        .build();

                userDocumentVersionService.saveUserDocumentVersion(userDocumentVersion);
            }
        }
    }


}