package lab1.dictionary.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParsedWord implements Comparable<ParsedWord>{
    private String word;
    private int frequencyOfOccurrence;
    private String parameters;
    private boolean isLemma;

    @Override
    public int compareTo(ParsedWord triple) {
        if (this.word == null && triple.getWord() == null) {
            return 0;
        } else if (this.word == null) {
            return -1;
        } else if (triple.getWord() == null) {
            return 1;
        } else {
            return this.word.compareTo(triple.getWord());
        }
    }
}