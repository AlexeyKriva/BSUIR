package org.example.uploadingfilesserver.repositories;

import org.example.uploadingfilesserver.entities.UserDocumentVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDocumentVersionRepository extends JpaRepository<UserDocumentVersion, Long> {
    List<UserDocumentVersion> findAllByTitle(String title);
}
