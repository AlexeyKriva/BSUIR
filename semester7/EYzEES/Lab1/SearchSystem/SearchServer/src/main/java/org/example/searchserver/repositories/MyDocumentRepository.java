package org.example.searchserver.repositories;

import org.example.searchserver.entities.MyDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyDocumentRepository extends JpaRepository<MyDocument, Long> {
    static String START_USER_REQUEST = "SELECT * FROM documents d WHERE ";
    Optional<MyDocument> findById(long id);
}
