import java.util.List;
import java.util.List;

public interface createSKNFandSDNF {
    public void sknfElements(List<List<String>> lines, List<String> function, List<String> variables);
    public void sdnfElements(List<List<String>> lines, List<String> function, List<String> variables);
    public void sknfNumbers(List<List<String>> lines, List<String> function, List<String> variables);
    public void sdnfNumbers(List<List<String>> lines, List<String> function, List<String> variables);
    public void sknfandsdnfIndex(List<List<String>> lines, List<String> function, List<String> variables);
}
