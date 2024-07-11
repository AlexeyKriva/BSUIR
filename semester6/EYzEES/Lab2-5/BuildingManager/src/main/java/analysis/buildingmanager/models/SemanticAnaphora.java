package analysis.buildingmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemanticAnaphora {
    private List<List<String>> namedEntities;
    private List<List<Integer>> numbersOfFirstSentence;
}