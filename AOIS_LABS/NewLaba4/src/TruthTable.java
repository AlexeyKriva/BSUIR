import java.util.ArrayList;
import java.util.List;

public class TruthTable {
    public static final String ONE = "1";
    public static final String ZERO = "0";
    public static final char[] VARIABLES = {'X', 'Y', 'Z'};
    public static final String FUNCTIONS = "F1\tF2";
    public static final String VARS = "X1\tX2\tX3\tX4\tY1\tY2\tY3\tY4";
    public static final String TWO = "0010";
    public static final int SIZE = 9;
    public static final String EMPTY = "-\t-\t-\t-";
    public static List<List<String>> createTruthTable(int n){
        List<List<String>> truthTable = new ArrayList<>();
        List<String> firstLine = new ArrayList<>();
        for (int i = 0; i < n; i++) firstLine.add(ZERO);
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
        }
        return truthTable;
    }

    //Функция вывода таблицы истинности
    public static void showTruthTable(List<List<String>> truthTable, List<String> answers, int n){
        for (char variable: VARIABLES){
            System.out.print(variable+"\t");
        }
        System.out.println(FUNCTIONS);
        for (int i = 0; i < Math.pow(2, n); i++){
            for(int j = 0; j < n; j++){
                int space = n;
                if (truthTable.get(i).get(j).equals(ONE)) System.out.print(ONE);
                else System.out.print(ZERO);
                while (space > 0){
                    System.out.print(" ");
                    space--;
                }
            }
            System.out.println(answers.get(i));
        }
    }

    //Функция для построения таблицы истинности 4 на 4
    public static List<List<String>> tableD8421(List<List<String>> truthTable, int n){
        List<List<String>> answers = new ArrayList<>();
        System.out.println(VARS);
        for (int i = 0; i < Math.pow(2, n); i++){
            String number = "";
            for (int j = n - 1; j >= 0; j--) {
                System.out.print(truthTable.get(i).get(j) + "\t");
                if (i < SIZE)
                    number += truthTable.get(i).get(j);
            }
            if (i < SIZE) {
                number = Sum.createAnswer(number);
                String answer = Sum.sum(number, TWO);
                List<String> line = new ArrayList<>();
                for (int j = answer.length() - 1; j >= 0; j--) {
                    System.out.print(answer.charAt(j) + "\t");
                    line.add(String.valueOf(answer.charAt(j)));
                }
                answers.add(line);
                System.out.println();
            }
            else
                System.out.println(EMPTY);
        }
        return answers;
    }
}
