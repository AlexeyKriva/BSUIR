package analysis.buildingmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleSemanticAnaphora {
    private String namedEntity;
    private String numberOfSentence;
}