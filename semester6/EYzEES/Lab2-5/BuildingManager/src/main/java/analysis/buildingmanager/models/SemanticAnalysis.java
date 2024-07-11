package analysis.buildingmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemanticAnalysis {
    private List<NamedEntity> namedEntities;
    private List<RelationInformation> relationInformation;
    private SemanticAnaphora semanticAnaphora;
}