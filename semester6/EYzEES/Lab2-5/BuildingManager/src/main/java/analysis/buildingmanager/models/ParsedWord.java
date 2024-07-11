package analysis.buildingmanager.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParsedWord{
    private String word;
    private String Lemma;
    private int frequencyOfOccurrence;
    private String parameters;
}