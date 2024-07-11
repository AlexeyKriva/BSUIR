package analysis.buildingmanager.models;

import lombok.Data;

@Data
public class SentenceAnalysis {
    private String sentence;
    private String word;
    private String word1;
    private String relation;
}