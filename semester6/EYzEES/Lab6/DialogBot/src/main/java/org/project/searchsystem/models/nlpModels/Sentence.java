package org.project.searchsystem.models.nlpModels;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Sentence {
    private String sentence;
    private List<ParsedWord> listOfWords;

    public static String fromSentencesToStrings(List<Sentence> sentences, int startIndex) {
        if (startIndex < sentences.size()) {
            return sentences.get(startIndex - 1).getSentence();
        }

        return "";
    }
}
