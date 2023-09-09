import java.util.ArrayList;
import java.util.List;

public class TruthTable {
    public static final String ONE = "1";
    public static final String ZERO = "0";
    public static final String[] VARIABLES = {"q1", "q2", "q3", "q1'", "q2'", "q3'", "h1", "h2", "h3"};
    public static final String V = "V";
    public static final int TWO = 2;

    public static List<List<String>> createTruthTable(int n){
        List<List<String>> truthTable = new ArrayList<>();
        List<String> firstLine = new ArrayList<>();
        for (int i = 0; i < n; i++) firstLine.add(ZERO);
        truthTable.add(firstLine);
        truthTable.add(firstLine);
        while (true){
            int element = firstLine.size() - 1;
            List<String> currentLine = new ArrayList<String>(truthTable.get(truthTable.size() - 1));
            while (element >= 0){
                if (currentLine.get(element).equals(ZERO)) break;
                element--;
            }
            if (element < 0) break;
            currentLine.add(element, ONE);
            element++;
            while (element < n){
                currentLine.add(element, ZERO);
                element++;
            }
            truthTable.add(currentLine);
            truthTable.add(currentLine);
        }
        return truthTable;
    }

    //Функция для дополнительной таблицы истинности
    public static List<List<String>> additionalCreateTruthTable(int n, List<List<String>> truthTable, String[] answers){
        List<List<String>> newTruthTable = new ArrayList<>();
        List<String> oneLine = new ArrayList<>();
        List<String> zeroLine = new ArrayList<>();
        for (int i = 0; i < n; i++) oneLine.add(ONE);
        for (int i = 0; i < n; i++) zeroLine.add(ZERO);
        newTruthTable.add(zeroLine);
        newTruthTable.add(oneLine);
        List<String> line = new ArrayList<>();
        line.add(ZERO);
        line.add(ZERO);
        line.add(ONE);
        newTruthTable.add(line);
        newTruthTable.add(zeroLine);
        line.clear();
        line.add(ZERO);
        line.add(ONE);
        line.add(ZERO);
        newTruthTable.add(line);
        line.clear();
        line.add(ZERO);
        line.add(ZERO);
        line.add(ONE);
        newTruthTable.add(line);
        line.clear();
        line.add(ZERO);
        line.add(ONE);
        line.add(ONE);
        newTruthTable.add(line);
        line.clear();
        line.add(ZERO);
        line.add(ONE);
        line.add(ZERO);
        newTruthTable.add(line);
        line.clear();
        line.add(ONE);
        line.add(ZERO);
        line.add(ZERO);
        newTruthTable.add(line);
        line.clear();
        line.add(ZERO);
        line.add(ONE);
        line.add(ONE);
        newTruthTable.add(line);
        line.clear();
        line.add(ONE);
        line.add(ZERO);
        line.add(ONE);
        newTruthTable.add(line);
        line.clear();
        line.add(ONE);
        line.add(ZERO);
        line.add(ZERO);
        newTruthTable.add(line);
        line.clear();
        line.add(ONE);
        line.add(ONE);
        line.add(ZERO);
        newTruthTable.add(line);
        line.clear();
        line.add(ONE);
        line.add(ONE);
        line.add(ONE);
        newTruthTable.add(line);
        line.clear();
        line.add(ONE);
        line.add(ONE);
        line.add(ZERO);
        newTruthTable.add(line);
        for (int i = 0; i < newTruthTable.size(); i++){
            for (int j = 0; j < n; j++){
                System.out.print(newTruthTable.get(i).get(j) + "\t");
            }
            System.out.println();
        }
        return newTruthTable;
    }



    private String[][] truthTABLE = {{"0", "0", "0"},
            {"1", "1", "1"},
            {"0", "0", "1"},
            {"0", "0", "0"},
            {"0", "1", "0"},
            {"0", "0", "1"},
            {"0", "1", "1"},
            {"0", "1", "0"},
            {"1", "0", "0"},
            {"0", "1", "1"},
            {"1", "0", "1"},
            {"1", "0", "0"},
            {"1", "1", "0"},
            {"1", "0", "1"},
            {"1", "1", "1"},
            {"1", "1", "0"}};

    public String[][] getTruthTABLE() {
        return truthTABLE;
    }

    //Функция для дополнительной таблицы истинности
    public static List<List<String>> additionalCreateTruthTable2(int n, List<List<String>> truthTable, String[][] newTruthTable1){
        List<List<String>> newTruthTable = new ArrayList<>();
        for (int i = 0; i < truthTable.size(); i++){
            List<String> line = new ArrayList<>();
            for (int j = 0; j < n; j++){
                if (truthTable.get(i).get(j).equals(newTruthTable1[i][j])){
                    line.add(ZERO);
                }
                else {
                    line.add(ONE);
                }
            }
            newTruthTable.add(line);
        }
        return newTruthTable;
    }


    //Функция вывода таблицы истинности
    public static void showTruthTable(List<List<String>> truthTable, String[][] newTruthTable, List<List<String>> newTruthTable2, String[] answers, int n){
        int index = 0;
        for (String variable: VARIABLES){
            System.out.print(variable+"\t");
            if (index == TWO) System.out.print(V+"\t");
            index++;
        }
        System.out.println();
        index = 0;
        for (int i = 0; i < TWO * Math.pow(TWO, n); i++){
            for(int j = 0; j < n; j++){
                int space = n;
                System.out.print(truthTable.get(i).get(j));
                while (space > 0){
                    System.out.print(" ");
                    space--;
                }
            }
            System.out.print(answers[index] + "\t");
            for(int j = 0; j < n; j++){
                int space = n;
                System.out.print(newTruthTable[i][j]);
                while (space > 0){
                    System.out.print(" ");
                    space--;
                }
            }
            for(int j = 0; j < n; j++){
                int space = n;
                System.out.print(newTruthTable2.get(i).get(j));
                while (space > 0){
                    System.out.print(" ");
                    space--;
                }
            }
            System.out.println();
            index++;
        }
    }

    public static String minimizationFormula(int n){
        if (n == 0){
            return "!q2*!q3*v";
        }
        else if (n == 1){
            return "!q3*v";
        }
        else {
            return "v";
        }
    }
}
