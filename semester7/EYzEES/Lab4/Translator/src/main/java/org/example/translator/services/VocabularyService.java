package org.example.translator.services;

import lombok.RequiredArgsConstructor;
import org.example.translator.entities.VocabularyPair;
import org.example.translator.repositories.VocabularyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VocabularyService {
    private final VocabularyRepository vocabularyRepository;

    public List<VocabularyPair> getAllVocabularyPairs() {
        return vocabularyRepository.findAll();
    }

    public VocabularyPair getVocabularyPairById(Long id) {
        return vocabularyRepository.findById(id).get();
    }

    public List<VocabularyPair> getVocabularyPairsByRequestId(Long requestId) {
        return vocabularyRepository.findByRequestId(requestId);
    }

    public VocabularyPair saveVocabularyPair(VocabularyPair vocabularyPair) {
        return vocabularyRepository.save(vocabularyPair);
    }

    public VocabularyPair updateVocabularyPair(Long id, VocabularyPair vocabularyPair) {
        VocabularyPair vocabularyPairFromDb = vocabularyRepository.findById(id).get();

        if (vocabularyPair.getId() != null) {
            vocabularyPairFromDb.setId(id);
        }

        if (vocabularyPair.getRequestId() != null) {
            vocabularyPairFromDb.setRequestId(vocabularyPair.getRequestId());
        }

        if (vocabularyPair.getEnWord() != null) {
            vocabularyPairFromDb.setEnWord(vocabularyPair.getEnWord());
        }

        if (vocabularyPair.getDeWord() != null) {
            vocabularyPairFromDb.setDeWord(vocabularyPair.getDeWord());
        }

        if (vocabularyPair.getFrequencyEn() != null) {
            vocabularyPairFromDb.setFrequencyEn(vocabularyPair.getFrequencyEn());
        }

        if (vocabularyPair.getFrequencyDe() != null) {
            vocabularyPairFromDb.setFrequencyDe(vocabularyPair.getFrequencyDe());
        }

        return vocabularyRepository.save(vocabularyPairFromDb);
    }

    public String deleteVocabularyPair(Long id) {
        vocabularyRepository.deleteById(id);

        return "{\"message\": \"Пара с id " + id + " была успешно удалена.\"}";
    }

    public String deleteAllVocabularyPair() {
        vocabularyRepository.deleteAll();

        return "{\"message\": \"Словарь был успешно удален.\"}";
    }

    public List<VocabularyPair> saveAllVocabularyPairs(List<VocabularyPair> vocabularyPairs) {
        return vocabularyRepository.saveAll(vocabularyPairs);
    }
}
