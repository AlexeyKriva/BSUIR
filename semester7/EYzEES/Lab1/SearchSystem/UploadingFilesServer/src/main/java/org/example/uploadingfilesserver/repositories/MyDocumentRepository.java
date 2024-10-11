package org.example.uploadingfilesserver.repositories;

import org.example.uploadingfilesserver.entities.MyDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyDocumentRepository extends JpaRepository<MyDocument, Long> {
    Optional<MyDocument> findById(long id);
    Optional<MyDocument> findByTitle(String title);
    boolean existsByTitle(String title);
}
