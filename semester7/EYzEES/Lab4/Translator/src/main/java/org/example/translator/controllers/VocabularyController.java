package org.example.translator.controllers;

import lombok.RequiredArgsConstructor;
import org.example.translator.entities.VocabularyPair;
import org.example.translator.services.VocabularyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vocabulary")
@CrossOrigin("http://localhost:8091")
@RequiredArgsConstructor
public class VocabularyController {
    private final VocabularyService vocabularyService;

    @GetMapping
    public ResponseEntity<List<VocabularyPair>> getAllVocabularyPairs() {
        return ResponseEntity.ok(vocabularyService.getAllVocabularyPairs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VocabularyPair> getVocabularyPair(@PathVariable("id") Long id) {
        return ResponseEntity.ok(vocabularyService.getVocabularyPairById(id));
    }

    @GetMapping("/requests/{requestId}")
    public ResponseEntity<List<VocabularyPair>> getVocabularyPairsByRequestId(@PathVariable("requestId")
                                                                                  Long requestId) {
        return ResponseEntity.ok(vocabularyService.getVocabularyPairsByRequestId(requestId));
    }

    @PostMapping
    public ResponseEntity<VocabularyPair> saveVocabularyPair(@RequestBody VocabularyPair vocabularyPair) {
        return ResponseEntity.ok(vocabularyService.saveVocabularyPair(vocabularyPair));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VocabularyPair> updateVocabularyPair(
            @PathVariable("id") Long id, @RequestBody VocabularyPair vocabularyPair) {
        return ResponseEntity.ok(vocabularyService.updateVocabularyPair(id, vocabularyPair));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVocabularyPair(@PathVariable("id") Long id) {
        return ResponseEntity.ok(vocabularyService.deleteVocabularyPair(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllVocabularyPairs() {
        return ResponseEntity.ok(vocabularyService.deleteAllVocabularyPair());
    }
}