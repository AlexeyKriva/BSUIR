import java.util.List;

public class Main {
    public static final int N = 3;
    public static final String[] ANSWERS = {"0", "1", "0", "1", "0", "1", "0", "1","0", "1", "0", "1", "0", "1", "0", "1"};
    public static final String CONJUCTION = "*";
    public static final String DISJUNCTION = "+";

    public static void main(String[] args) {
        TruthTable table = new TruthTable();
        List<List<String>> truthTable = TruthTable.createTruthTable(N);
        String[][] newTruthTable = table.getTruthTABLE();
        List<List<String>> newTruthTable2 = TruthTable.additionalCreateTruthTable2(N, truthTable, newTruthTable);
        TruthTable.showTruthTable(truthTable, newTruthTable, newTruthTable2, ANSWERS, N);
        String sdnf1 = BuildSDNF.getSDNF(0);
        String sdnf2 = BuildSDNF.getSDNF(1);
        String sdnf3 = BuildSDNF.getSDNF(2);
        System.out.println(sdnf1+"\n"+sdnf2+"\n"+sdnf3);
        System.out.println("Минимизация:");
        String minSDNF1 = TruthTable.minimizationFormula(0);
        String minSDNF2 = TruthTable.minimizationFormula(1);
        String minSDNF3 = TruthTable.minimizationFormula(2);
        System.out.println(minSDNF1 + "\n" + minSDNF2 + "\n" + minSDNF3);
    }
}