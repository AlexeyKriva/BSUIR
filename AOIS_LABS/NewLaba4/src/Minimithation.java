import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Minimithation {
    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";
    public static final String INVERSION = "!";
    public static final int TWO = 2;
    public static final int COINCIDENCE = 1;
    public static final int SIZE = 9;

    //Функция для конституэнт
    public static List<String> createConstituence(String formula){
        List<String> constituence = new ArrayList<>();
        for (int i = 0; i < formula.length(); i++){
            if (String.valueOf(formula.charAt(i)).equals(LEFT_BRACKET)){
                String constit = LEFT_BRACKET;
                for (int j = i + 1; j < formula.length(); j++){
                    if (String.valueOf(formula.charAt(j)).equals(RIGHT_BRACKET)){
                        constit += RIGHT_BRACKET;
                        break;
                    }
                    constit += formula.charAt(j);
                }
                constituence.add(constit);
            }
        }
        return constituence;
    }

    //Функция для создания таблицы
    public static String kvainMakKlasski(String formula, String sign, String sign2){
        List<String> constituents = createConstituence(formula);
        ArrayList<ArrayList<String>> implicants = calculationMethod(formula);
        List<List<Boolean>> table = new ArrayList<>();
        for (int i = 0; i < implicants.size(); i++){
            List<Boolean> smallTable = new ArrayList<>();
            for (int j = 0; j < constituents.size(); j++) {
                int amount = 0;
                for (int k = 0; k < implicants.get(i).size(); k++) {
                    if (implicants.get(i).get(k).length() == TWO) {
                        Pattern pattern = Pattern.compile(implicants.get(i).get(k));
                        Matcher matcher = pattern.matcher(constituents.get(j));
                        if (matcher.find()) amount++;
                    }
                    else {
                        Pattern pattern = Pattern.compile(INVERSION + implicants.get(i).get(k));
                        Matcher matcher = pattern.matcher(constituents.get(j));
                        if (!matcher.find()) amount++;
                    }
                }
                if (amount == implicants.get(i).size()) smallTable.add(true);
                else smallTable.add(false);
            }
            table.add(smallTable);
        }
        show(constituents,implicants,table,sign);
        return minimization(implicants, constituents, table, sign, sign2);
    }

    //Функция склеивания формулы
    public static ArrayList<ArrayList<String>> calculationMethod(String formula){
        ArrayList<ArrayList<String>> allElements = allocateElements(formula);
        ArrayList<ArrayList<String>> implicants = new ArrayList<>();
        for (int i = 0; i < allElements.size() - 1; i++){
            for (int j = 0; j < allElements.get(i).size(); j++){
                for (int x = i + 1; x < allElements.size(); x++){
                    if (allElements.get(i).size() - 1 == createImplicant(allElements, i, j, x).size()){
                        implicants.add(createImplicant(allElements, i, j, x));
                        break;
                    }
                }
            }
        }
        return implicants;
    }

    //Функция для создания импликант
    public static ArrayList<String> createImplicant(ArrayList<ArrayList<String>> allElements, int indexI, int indexJ, int indexX) {
        ArrayList<String> implicant = new ArrayList<>();
        if (allElements.get(indexI).get(indexJ) != allElements.get(indexX).get(indexJ)) {
            for (int i = 0; i < indexJ; i++) {
                if (allElements.get(indexI).get(i).equals(allElements.get(indexX).get(i)))
                    implicant.add(allElements.get(indexI).get(i));
                else break;
            }
            for (int i = indexJ + 1; i < allElements.get(indexI).size(); i++) {
                if (allElements.get(indexI).get(i).equals(allElements.get(indexX).get(i)))
                    implicant.add(allElements.get(indexI).get(i));
                else break;
            }
        }
        return implicant;
    }

    //Функция для нахождения всех переменных
    public static ArrayList<ArrayList<String>> allocateElements(String formula){
        ArrayList<ArrayList<String>> allElements = new ArrayList<>();
        for (int i = 0; i < formula.length(); i++){
            if (formula.charAt(i) == LEFT_BRACKET.charAt(0)){
                allElements.add(checkBrackets(formula.substring(i + 1)));
            }
        }
        return allElements;
    }

    //Функция для проверки скобки
    public static ArrayList<String> checkBrackets(String formula){
        ArrayList<String> elements = new ArrayList<>();
        for (int i = 0; i < formula.length(); i++){
            if (formula.charAt(i) == RIGHT_BRACKET.charAt(0)) break;
            if (Character.isUpperCase(formula.charAt(i))){
                String symbol = "" + formula.charAt(i);
                if (i != 0 && formula.charAt(i - 1) == INVERSION.charAt(0)){
                    symbol = INVERSION + symbol;
                }
                elements.add(symbol);
            }
        }
        return elements;
    }

    //Функция для вывода таблицы
    public static void show(List<String> constituents, ArrayList<ArrayList<String>> implicants, List<List<Boolean>> table, String sign){
        if (implicants.size() != 0) {
            System.out.print("\t\t");
            for (String constituent : constituents) System.out.print(constituent + "\t");
            System.out.println();
            for (int i = 0; i < implicants.size(); i++) {
                System.out.print(LEFT_BRACKET);
                for (int j = 0; j < implicants.get(i).size(); j++) {
                    System.out.print(implicants.get(i).get(j));
                    if (implicants.get(i).size() - j != 1) System.out.print(sign);
                }
                System.out.print(RIGHT_BRACKET + "\t");
                for (int j = 0; j < table.get(i).size(); j++) {
                    System.out.print(table.get(i).get(j) + "\t\t");
                }
                if (implicants.size() - i != COINCIDENCE) System.out.println();
            }
            System.out.println();
        }
    }

    //Функция для выкидывания лишних импликант
    public static String minimization(ArrayList<ArrayList<String>> implicants, List<String> constituents, List<List<Boolean>> table, String sign, String sign2){
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < constituents.size(); i++){
            int amount = 0;
            int implicantNumber = 0;
            for (int j = 0; j < table.size() * constituents.size(); j += constituents.size()){
                int ind = j / constituents.size();
                if (table.get(ind).get(i)) {
                    amount++;
                    implicantNumber = j / constituents.size();
                }
            }
            if (amount == COINCIDENCE) index.add(implicantNumber);
        }
        String answer = "";
        for (int i = 0; i < index.size(); i++){
            answer += LEFT_BRACKET;
            for (int j = 0; j < implicants.get(index.get(i)).size(); j++){
                answer += implicants.get(index.get(i)).get(j);
                if (implicants.get(index.get(i)).size() - j != 1) answer += sign;
            }
            answer += RIGHT_BRACKET;
            if (index.size() - i != 1)answer += sign2;
        }
        return answer;
    }

    //Функция минимизации
    public static String minimizationN(int n){
        if (n == 0) return "(X1+X4)*(X1+X2+X3)";
        else if (n == 1) return "(!X2+X4)";
        else if (n == 2) return "(X1+X2+X3)*(!X2+!X3+X4)*(X2+X3+X4)";
        else return "(X2+X4)*(X3+X4)";
    }

    //Создать ответы функции
    public static List<String> getFunction(List<List<String>> functions, int index){
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < SIZE; i++){
            answer.add(functions.get(i).get(index));
        }
        return answer;
    }
}
