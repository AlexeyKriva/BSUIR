package org.project.searchsystem.models.nlpModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParsedWord{
    private String word;
    private String Lemma;
    private int frequencyOfOccurrence;
    private String parameters;
}