package analysis.buildingmanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationInformation {
    private double confidence;
    private String subject;
    private String relation;
    private String object;
}