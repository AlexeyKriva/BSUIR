package org.project.searchsystem.models.nlpModels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair {
    private String word;
    private String relation;
}
