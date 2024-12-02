package com.software.bsuir.abstractbuilder.services;

import com.software.bsuir.abstractbuilder.clients.HuggingfaceClient;
import com.software.bsuir.abstractbuilder.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@ConditionalOnBean(StandfordNlpService.class)
public class AbstractBuilderService {
    private final FileService fileService;
    private final HuggingfaceClient huggingfaceClient;
    private final StandfordNlpService standfordNlpService;

    @Value("${huggingface.api.key}")
    private String huggingFaceApiKey;

    public List<AbstractResponse> buildAbstractFromFilesWithType(List<MultipartFile> multipartFiles,
                                                             AbstractType abstractType) {
        List<FileProperty> fileProperties = fileService.processTxtFiles(multipartFiles);

        List<AbstractResponse> abstractResponses = new ArrayList<>();

        for (FileProperty fileProperty: fileProperties) {
            GeneralAbstract generalAbstract = buildAbstractWithType(fileProperty, abstractType);

            abstractResponses.add(new AbstractResponse(fileProperty.getContent(), generalAbstract));

            fileService.saveAbstractResponseInFile(abstractResponses.get(
                    abstractResponses.size() - 1
            ));
        }

        return abstractResponses;
    }

    private GeneralAbstract buildAbstractWithType(FileProperty fileProperty, AbstractType abstractType) {
        switch (abstractType) {
            case KEY_WORDS -> {
                return standfordNlpService.buildAbstractByKeyWords(fileProperty);
            }
            case CLASSIC_ABSTRACT -> {
                return standfordNlpService.generalBuildClassicAbstract(fileProperty);
            }
            case TRAINED_MODEL -> {
                return huggingfaceClient.buildAbstractsWithModel(huggingFaceApiKey,
                        new HuggingFaceRequestBody(fileProperty.getContent())).getBody().get(0);
            }
            default -> {
                return new GeneralAbstract("Cannot build abstract.");
            }
        }
    }
}