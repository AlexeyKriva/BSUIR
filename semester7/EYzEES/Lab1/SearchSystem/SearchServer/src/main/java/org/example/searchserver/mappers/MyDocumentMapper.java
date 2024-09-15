package org.example.searchserver.mappers;

import org.example.searchserver.entities.MyDocument;
import org.example.searchserver.entities.MyDocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper
public interface MyDocumentMapper {
    MyDocumentMapper INSTANCE = Mappers.getMapper(MyDocumentMapper.class);

    MyDocument fromMyDocumentDtoToMyDocument(MyDocumentDto myDocumentDto);
}
