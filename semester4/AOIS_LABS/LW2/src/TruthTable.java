import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class TruthTable implements createSKNFandSDNF {
    public static final String REGEX_ELEMENTS = "([A-Z][0-9]+)|([A-Z]\\D)";
    public static final String REGEX_INVALID_COMBINATIONS = "([@\"#№$;%:&?_={}\\[\\]|/<,.])|([A-Z]0)|([a-z])|([+*~][+*~])|([+*~>][+*!~])|([+*!~][+*~-])|(->->)|(^->)";
    public static final String REGEX_LEFT_BRACKET = "\\(";
    public static final String REGEX_RIGHT_BRACKET = "\\)";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String ONE = "1";
    public static final String ZERO = "0";
    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";
    public static final String INVERSION = "!";
    public static final String CONJUCTION = "*";
    public static final String DISJUNCTION = "+";
    public static final String IMPLICATION = "-";
    public static final String EQUIVALANCE = "~";

    //Функция для нахождения количества атомарных формул
    public static List<String> findNumberOfVariables (String formula){
        Pattern pattern = Pattern.compile(REGEX_ELEMENTS);
        Matcher matcher = pattern.matcher(formula);
        List<String> variables = new ArrayList<>();
        int amountVariables = 0;
        boolean flag = true;
        while (matcher.find()) {
            String var = matcher.group();
            char symbol = var.charAt(var.length() - 1);
            if(Character.isDigit(symbol))
                var = matcher.group();
            else
                var = matcher.group().substring(0, matcher.group().length() - 1);
            for (int i = 0; i < variables.size(); i++){
                if (variables.get(i).equals(var)) flag = false;
            }
            if (flag)
                variables.add(var);
            else
                flag = true;
            amountVariables++;
        }
        return variables;
    }

    //Формула для проверки формулы на правильность её составления
    public static boolean formulaValidation(String formula){
        Pattern invalid = Pattern.compile(REGEX_INVALID_COMBINATIONS);
        Matcher matcherInvalid = invalid.matcher(formula);
        if (matcherInvalid.find()) return false;
        Pattern frontBrace = Pattern.compile(REGEX_LEFT_BRACKET);
        Matcher matcherFrontBrace = frontBrace.matcher(formula);
        int numberFront = 0;
        while (matcherFrontBrace.find()) numberFront++;
        Pattern backBrace = Pattern.compile(REGEX_RIGHT_BRACKET);
        Matcher matcherBackBrace = backBrace.matcher(formula);
        int numberBack = 0;
        while (matcherBackBrace.find()) numberBack++;
        return numberFront == numberBack;
    }

    //Функция для построения таблицы истенности
    public static List<List<String>> createTruthTable(List<String> variables){
        for (String i : variables) System.out.print(i+" ");
        System.out.println("F");
        List<List<String>> lines = new ArrayList<>();
        List<String> firstLine = new ArrayList<>();
        for (int i = 0; i < variables.size(); i++) firstLine.add(ZERO);
        lines.add(firstLine);
        while (true){
            int element = firstLine.size() - 1;
            List<String> currentLine = new ArrayList<String>(lines.get(lines.size() - 1));
            while (element >= 0){
                if (currentLine.get(element).equals(ZERO)) break;
                element--;
            }
            if (element < 0) break;
            currentLine.add(element, ONE);
            element++;
            while (element < variables.size()){
                currentLine.add(element, ZERO);
                element++;
            }
            lines.add(currentLine);
        }
        return lines;
    }

    //Функция вывода таблицы истинности
    public static void showTruthTable(List<List<String>> lines, List<String> variables, String formula){
        List<String> answer = findResultOfFunction(formula, lines, variables);
        for (int i = 0; i < Math.pow(2, variables.size()); i++){
            for(int j = 0; j < variables.size(); j++){
                int space = variables.get(j).length();
                if (lines.get(i).get(j).equals(ONE)) System.out.print(ONE);
                else System.out.print(ZERO);
                while (space > 0){
                    System.out.print(" ");
                    space--;
                }
            }
            if (answer.get(i).equals(TRUE)) System.out.println(ONE);
            else System.out.println(ZERO);
        }
        TruthTable output = new TruthTable();
        output.sknfElements(lines, answer, variables);
        output.sknfNumbers(lines, answer, variables);
        output.sknfandsdnfIndex(lines, answer, variables);
        output.sdnfElements(lines, answer, variables);
        output.sknfandsdnfIndex(lines, answer, variables);
        output.sdnfNumbers(lines, answer, variables);
    }

    //Функция для упрощения формулы
    public static String newFormula(String formula, List<String> variables){
        int size = 0;
        for (int i = 0; i < formula.length(); i++){
            if (formula.charAt(i) == IMPLICATION.charAt(0)) formula = formula.substring(0, i + 1) + formula.substring(i + 2);
        }
        return formula;
    }

    //Функция для нахождения результатов операций
    public static List<String> findResultOfFunction(String formula, List<List<String>> lines, List<String> variables){
        List<String> answers = new ArrayList<>();
        HashMap<String, Integer> operationPriorities = createMap();
        List<HashMap<String, String>> varElements = createElementsMap(formula, lines, variables);
        Stack<Boolean> elements = new Stack<>();
        Stack<String> operations = new Stack<>();
        for (int i = 0; i < Math.pow(2, variables.size()); i++){
            for (int j = 0; j < formula.length(); j++){
                if (Character.isUpperCase(formula.charAt(j))){
                    String[] result = addElement(i, j, formula, formula.charAt(j), varElements);
                    j += Integer.parseInt(result[1]);
                    elements.push(Boolean.valueOf(result[0]));
                }
                else if (!operations.empty()){
                    if ((operationPriorities.get(String.valueOf(formula.charAt(j))) > operationPriorities.get(operations.peek())) && (formula.charAt(j) != '(')) operations.push(String.valueOf(formula.charAt(j)));
                    else if (formula.charAt(j) == LEFT_BRACKET.charAt(0)) operations.push(String.valueOf(formula.charAt(j)));
                    else {
                        String operation = operations.pop();
                        elements = getOperation(elements, operation);
                        operations.push(String.valueOf(formula.charAt(j)));
                        operations = deleteBrackets(operations);
                    }
                }
                else operations.push(String.valueOf(formula.charAt(j)));
            }
            answers.add(String.valueOf(elements.pop()));
            elements.clear();
            operations.clear();
        }
        return answers;
    }

    //Функция для добавления элементов в стек элементов
    public static String[] addElement(int i, int j, String formula, char symbol, List<HashMap<String, String>> varElements){
        String[] result = new String[2];
        String fullElement = "" + symbol;
        int amountNumbers = 0;
        for (int x = j + 1; x < formula.length(); x++){
            char newSymbol = formula.charAt(x);
            if (Character.isDigit(newSymbol)){
                fullElement += newSymbol;
                amountNumbers++;
            }
            else break;
        }
        result[1] = String.valueOf(amountNumbers);
        if (varElements.get(i).get(fullElement).equals(ONE)) result[0] = TRUE;
        else result[0] = FALSE;
        return result;
    }

    //Функция для проведения операции
    public static Stack<Boolean> getOperation(Stack<Boolean> elements, String operation){
        if (operation.equals(INVERSION)) {
            elements.push(inversion(elements.pop()));
        } else {
            boolean x1 = elements.pop();
            boolean x2 = elements.pop();
            if (operation.charAt(0) == CONJUCTION.charAt(0)) elements.push(conjunction(x2, x1));
            else if (operation.charAt(0) == DISJUNCTION.charAt(0)) elements.push(disjunction(x2, x1));
            else if (operation.charAt(0) == IMPLICATION.charAt(0)) elements.push(implication(x2, x1));
            else elements.push(equivalence(x2, x1));
        }
        return elements;
    }

    //Функция для удаления скобок
    public static Stack<String> deleteBrackets(Stack<String> operations){
        int amountRightBracket = 0;
        while (!operations.empty()) {
            if (operations.peek().equals(RIGHT_BRACKET)){
                operations.pop();
                amountRightBracket++;
            }
            else break;
        }
        int i = 0;
        while (i < amountRightBracket){
            operations.pop();
            i++;
        }
        return operations;
    }

    //Функция для создания Map
    public static HashMap<String, Integer> createMap(){
        HashMap<String, Integer> operationPriorities = new HashMap<>();
        {
            operationPriorities.put(INVERSION, 5);
            operationPriorities.put(CONJUCTION, 4);
            operationPriorities.put(DISJUNCTION, 3);
            operationPriorities.put(IMPLICATION, 2);
            operationPriorities.put(EQUIVALANCE, 1);
            operationPriorities.put(LEFT_BRACKET, 0);
            operationPriorities.put(RIGHT_BRACKET, 0);
        }
        return operationPriorities;
    }

    //Функция для заполнения элементов 0 и 1;
    public static List<HashMap<String, String>> createElementsMap(String formula, List<List<String>> lines, List<String> variables){
        List<HashMap<String, String>> varElements = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, variables.size()); i++){
            HashMap<String, String> lineElements = new HashMap<>();
            for (int j = 0; j < variables.size(); j++){
                lineElements.put(variables.get(j), lines.get(i).get(j));
            }
            varElements.add(lineElements);
        }
        return varElements;
    }

    //Функция для вывода СКНФ элементами
    public void sknfElements(List<List<String>> lines, List<String> function, List<String> variables){
        System.out.println("Вывод СКНФ:");
        String outSKNF = "";
        for (int i = 0; i < Math.pow(2, variables.size()); i++){
            if (function.get(i).equals(FALSE)) {
                outSKNF += LEFT_BRACKET;
                for (int j = 0; j < variables.size(); j++) {
                    if (lines.get(i).get(j).equals(ONE)) outSKNF += INVERSION + variables.get(j);
                    else outSKNF += variables.get(j);
                    if (variables.size() - j != 1) outSKNF += DISJUNCTION;
                }
                outSKNF += RIGHT_BRACKET + CONJUCTION;
            }
        }
        System.out.println(outSKNF.substring(0, outSKNF.length() - 1));
    }

    //Функция для вывода СДНФ элементами
    public void sdnfElements(List<List<String>> lines, List<String> function, List<String> variables){
        System.out.println("Вывод СДНФ:");
        String outSDNF = "";
        for (int i = 0; i < Math.pow(2, variables.size()); i++){
            if (function.get(i).equals(TRUE)) {
                outSDNF += LEFT_BRACKET;
                for (int j = 0; j < variables.size(); j++) {
                    if (lines.get(i).get(j).equals(ONE)) outSDNF += INVERSION + variables.get(j);
                    else outSDNF += variables.get(j);
                    if (variables.size() - j != 1) outSDNF += CONJUCTION;
                }
                outSDNF += RIGHT_BRACKET + DISJUNCTION;
            }
        }
        System.out.println(outSDNF.substring(0, outSDNF.length() - 1));
    }

    //Функция для вывода СКНФ в числовом виде
    public void sknfNumbers(List<List<String>> lines, List<String> function, List<String> variables){
        String outSKNF = "*(";
        for (int i = 0; i < Math.pow(2, variables.size()); i++){
            if (function.get(i).equals(FALSE)) outSKNF += Integer.toString(i) + ", ";
        }
        System.out.println(outSKNF.substring(0, outSKNF.length() - 2) + ")");
    }

    //Функция для вывода СДНФ в числовом виде
    public void sdnfNumbers(List<List<String>> lines, List<String> function, List<String> variables){
        String outSDNF = "+(";
        for (int i = 0; i < Math.pow(2, variables.size()); i++){
            if (function.get(i).equals(TRUE)) outSDNF += Integer.toString(i) + ", ";
        }
        System.out.println(outSDNF.substring(0, outSDNF.length() - 2) + ")");
    }

    //Функция для вывода СКНФ и СДНФ индексами
    public void sknfandsdnfIndex(List<List<String>> lines, List<String> function, List<String> variables){
        int number = 0;
        int degree = 0;
        for (double i = Math.pow(2, variables.size()) - 1; i >= 0; i--){
            if (function.get((int) i).equals(TRUE)) number += Math.pow(2, degree);
            degree++;
        }
        System.out.println(number);
    }

    //Функция отрицания
    public static boolean inversion(boolean x){ return !x; }

    //Функция коньюнкция
    public static boolean conjunction(boolean x1, boolean x2){ return x1 && x2; }

    //Функция дизьюнкции
    public static boolean disjunction(boolean x1, boolean x2){ return x1 || x2; }

    //Функция импликации
    public static boolean implication(boolean x1, boolean x2){ return !(x1 && !x2); }

    //Функция эквивалентности
    public static boolean equivalence(boolean x1, boolean x2){ return x1 == x2; }
}
