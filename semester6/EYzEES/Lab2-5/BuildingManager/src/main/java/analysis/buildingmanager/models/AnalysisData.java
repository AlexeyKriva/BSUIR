package analysis.buildingmanager.models;

import lombok.Data;
import java.util.*;

@Data
public class AnalysisData {
    private String text1;
    private List<String> vertices;
    private List<String> relations;
}
