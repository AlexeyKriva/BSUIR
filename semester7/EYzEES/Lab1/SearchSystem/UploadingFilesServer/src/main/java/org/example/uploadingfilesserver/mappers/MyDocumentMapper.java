package org.example.uploadingfilesserver.mappers;

import org.example.uploadingfilesserver.entities.MyDocument;
import org.example.uploadingfilesserver.entities.MyDocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MyDocumentMapper {
    MyDocumentMapper INSTANCE = Mappers.getMapper(MyDocumentMapper.class);

    MyDocument fromMyDocumentDtoToMyDocument(MyDocumentDto myDocumentDto);
}
