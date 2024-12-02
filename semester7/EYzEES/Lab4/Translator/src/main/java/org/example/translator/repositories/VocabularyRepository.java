package org.example.translator.repositories;

import org.example.translator.entities.VocabularyPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VocabularyRepository extends JpaRepository<VocabularyPair, Long> {
    List<VocabularyPair> findByRequestId(Long requestId);
}