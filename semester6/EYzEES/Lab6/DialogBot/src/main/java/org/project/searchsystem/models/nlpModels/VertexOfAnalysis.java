package org.project.searchsystem.models.nlpModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.searchsystem.models.nlpModels.Pair;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VertexOfAnalysis {
    private String word;
    private List<Pair> relations;

}
