package org.example.searchserver.mappers;

import org.example.searchserver.entities.MyDocument;
import org.example.searchserver.entities.MyDocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

@Mapper
public interface MyDocumentMapper {
    MyDocumentMapper INSTANCE = Mappers.getMapper(MyDocumentMapper.class);

    @Mapping(target = "title", source = "title", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "author", source = "author", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "content", source = "content", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "publishedAt", source = "publishedAt", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MyDocument getFromMyDocumentDtoToMyDocument(MyDocumentDto myDocumentDto);

    default void updateFromMyDocumentDtoToMyDocument(MyDocumentDto myDocumentDto, @MappingTarget MyDocument myDocument) {
        if (myDocumentDto.getTitle() != null) {
            myDocument.setTitle(myDocumentDto.getTitle());
        }
        if (myDocumentDto.getAuthor() != null) {
            myDocument.setAuthor(myDocumentDto.getAuthor());
        }
        if (myDocumentDto.getContent() != null) {
            myDocument.setContent(myDocumentDto.getContent());
        }
        if (myDocumentDto.getPublishedAt() != null) {
            myDocument.setPublishedAt(myDocumentDto.getPublishedAt());
        }
    }
}
