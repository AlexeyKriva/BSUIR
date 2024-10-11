package org.example.uploadingfilesserver.services;

import lombok.AllArgsConstructor;
import org.example.uploadingfilesserver.entities.MyDocumentDto;
import org.example.uploadingfilesserver.entities.UserDocumentVersion;
import org.example.uploadingfilesserver.repositories.UserDocumentVersionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserDocumentVersionService {
    private UserDocumentVersionRepository userDocumentVersionRepository;

    public boolean isNewOrUpdatedDocument(MyDocumentDto myDocumentDto) {
        List<UserDocumentVersion> userDocumentVersionsFromDb = findUserDocumentVersionByTitle(myDocumentDto.getTitle());

        if (userDocumentVersionsFromDb.isEmpty()) {
            return true;
        }

        String currentVersion = myDocumentDto.getVersion();

        for (UserDocumentVersion userDocumentVersion: userDocumentVersionsFromDb) {
            if (userDocumentVersion.getVersion().equals(currentVersion)) {
                return false;
            }
        }

        return true;
    }

    public UserDocumentVersion saveUserDocumentVersion(UserDocumentVersion userDocumentVersion) {
        return userDocumentVersionRepository.save(userDocumentVersion);
    }

    private List<UserDocumentVersion> findUserDocumentVersionByTitle(String title) {
        return userDocumentVersionRepository.findAllByTitle(title);
    }
}
