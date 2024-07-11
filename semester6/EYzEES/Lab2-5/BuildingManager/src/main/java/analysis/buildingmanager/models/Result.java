package analysis.buildingmanager.models;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private List<String> sentences;
    private List<String> words;
    private List<Integer> frequencyOfOccurrences;
    private List<String> parameters;
}
