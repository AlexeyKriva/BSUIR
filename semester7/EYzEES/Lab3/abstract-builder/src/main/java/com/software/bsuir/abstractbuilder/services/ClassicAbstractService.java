package com.software.bsuir.abstractbuilder.services;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassicAbstractService {
    private static final Integer LOG_DB_DFT = 1;

    private static final List<String> PROHIBITED_TERMS = List.of(" ", ".", ",", "?", "!", "\n", ":", "'", "(",
            ")", ";", "\"");

    public Double scoreS(String sentence, String document) {
        Double score = 0.0;

        for (Character term: sentence.toCharArray()) {
            score += tf(toTerm(term), sentence) * wtD(toTerm(term), document);
        }

        return score;
    }

    public Integer tf(String term, String text) {
        Integer numberOfTerm = 0;

        for (int i = 0; i < text.length(); i++) {
            if (!isTermProhibited(term) && term.equals(toTerm(text.charAt(i)))) {
                numberOfTerm++;
            }
        }

        return numberOfTerm;
    }

    public Double wtD(String term, String text) {
        return 0.5 * (1 + tf(term, text) / tfMaxD(text)) * LOG_DB_DFT;
    }

    public Integer tfMaxD(String text) {
        Map<String, Integer> numberOfSymbols = new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            String term = String.valueOf(text.charAt(i));

            if (!isTermProhibited(term)) {
                numberOfSymbols.put(term, numberOfSymbols.getOrDefault(term, 0) + 1);
            }
        }

        Optional<Map.Entry<String, Integer>> maxFrequentSymbol = numberOfSymbols.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));

        return maxFrequentSymbol.get().getValue();
    }

    public Boolean isTermProhibited(String term) {
        return PROHIBITED_TERMS.contains(term);
    }

    public Integer termsInDoc(String text) {
        Integer numberOfTerms = 0;

        for (Character term: text.toCharArray()) {
            if (!isTermProhibited(toTerm(term))) {
                numberOfTerms++;
            }
        }

        return numberOfTerms;
    }

    public String toTerm(Character term) {
        return String.valueOf(term);
    }
}