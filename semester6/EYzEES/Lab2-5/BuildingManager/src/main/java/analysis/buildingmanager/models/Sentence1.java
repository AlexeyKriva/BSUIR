package analysis.buildingmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Sentence1 {
    private String sentence;
    private List<ParsedWord> listOfWords;

    public static String fromSentenceToStrings(List<Sentence1> sentences, int startIndex) {
        if (startIndex < sentences.size()) {
            return sentences.get(startIndex - 1).getSentence();
        }

        return "";
    }

    public static String fromSentencesToStrings(List<Sentence1> sentences) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < sentences.size(); i++) {
            text.append(sentences.get(i).getSentence());
        }

        return text.toString();
    }
}