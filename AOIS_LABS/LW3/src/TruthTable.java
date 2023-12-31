import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class TruthTable{
    public static final String REGEX_ELEMENTS = "([A-Z][0-9]+)|([A-Z]\\D)";
    public static final String REGEX1 = "_x_";
    public static final String REGEX2 = "_!x_";
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
    public static final String X = "x";
    public static final String DELIMITER = "_";
    public static final int TWO = 2;
    public static final int COINCIDENCE = 1;
    public static final String str = "\t   00\t01\t11\t10";
    public static final int N = 3;

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

    //Функция для построения таблицы истенности
    public static List<List<String>> createTruthTable(List<String> variables){
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
                    else if (formula.charAt(j) == RIGHT_BRACKET.charAt(0)) operations = deleteBrackets(operations);
                    else {
                        String operation = operations.pop();
                        elements = getOperation(elements, operation);
                        operations.push(String.valueOf(formula.charAt(j)));
                        operations = deleteBrackets(operations);
                    }
                }
                else operations.push(String.valueOf(formula.charAt(j)));
            }
            if (!operations.isEmpty()){
                String operation = operations.pop();
                elements = getOperation(elements, operation);
                operations = deleteBrackets(operations);
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
        while (!operations.empty()) {
            if (operations.peek().equals(LEFT_BRACKET)){
                operations.pop();
                amountRightBracket++;
            }
            else break;
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

    //Функция для проведения минимизации СДНФ
    public static String makeMinimizationSDNF(String formula, String sign1, String sign2, boolean key){
        ArrayList<ArrayList<String>> implicants = calculationMethod(formula);
        String newFormula = convertImplicantsToStringSDNF(implicants);
        implicants = calculationMethod(newFormula);
        newFormula = convertImplicantsToStringSDNF(implicants);
        if (key) System.out.println("Implicants: "+newFormula);
        String answer = "";
        for (int i = 0; i < newFormula.length(); i++){
            if (String.valueOf(newFormula.charAt(i)).equals(LEFT_BRACKET)){
                String implicant = "";
                for (int j = i; j < newFormula.length(); j++){
                    if (String.valueOf(newFormula.charAt(j)).equals(RIGHT_BRACKET)){
                        implicant += RIGHT_BRACKET;
                        break;
                    }
                    implicant += newFormula.charAt(j);
                }
                List<String> variables = findNumberOfVariables(implicant);
                List<List<String>> lines = createTruthTable(variables);
                List<String> result = findResultOfFunction(implicant, lines, variables);
                for (int j = 0; j < result.size(); j++){
                    if (result.get(j).equals(TRUE)){
                        String preAnswer = answer;
                        answer += endFormulaSDNF(newFormula, lines, variables, j, implicant);
                        if (!answer.equals(preAnswer)) answer += DISJUNCTION;
                        break;
                    }
                }
            }
        }
        return tableFun(formula, sign1, sign2, false);
    }

    //Функция для проведения минимизации СКНФ
    public static String makeMinimizationSKNF(String formula, String sign1, String sign2, boolean key){
        ArrayList<ArrayList<String>> implicants = calculationMethod(formula);
        String newFormula = convertImplicantsToStringSKNF(implicants);
        implicants = calculationMethod(newFormula);
        newFormula = convertImplicantsToStringSKNF(implicants);
        if (key) System.out.println("Implicants: "+newFormula);
        String answer = "";
        for (int i = 0; i < newFormula.length(); i++){
            String implicant = "";
            if (String.valueOf(newFormula.charAt(i)).equals(LEFT_BRACKET)){
                for (int j = i; j < newFormula.length(); j++){
                    if (String.valueOf(newFormula.charAt(j)).equals(RIGHT_BRACKET)){
                        implicant += RIGHT_BRACKET;
                        break;
                    }
                    implicant += newFormula.charAt(j);
                }
                List<String> variables = findNumberOfVariables(implicant);
                List<List<String>> lines = createTruthTable(variables);
                List<String> result = findResultOfFunction(implicant, lines, variables);
                for (int j = 0; j < result.size(); j++){
                    if (result.get(j).equals(FALSE)){
                        String preAnswer = answer;
                        answer += endFormulaSKNF(newFormula, lines, variables, j, implicant);
                        if (!answer.equals(preAnswer)) answer += CONJUCTION;
                        break;
                    }
                }
            }
        }
        return tableFun(formula, sign1, sign2, false);
    }

    //Функция склеивания формулы
    public static ArrayList<ArrayList<String>> calculationMethod(String formula){
        ArrayList<ArrayList<String>> allElements = allocateElements(formula);
        ArrayList<ArrayList<String>> implicants = new ArrayList<>();
        ArrayList<ArrayList<String>> preImplicants = new ArrayList<>();
        for (int i = 0; i < allElements.size() - 1; i++){
            boolean var = true;
            boolean flag = true;
            for (int j = i + 1; j < allElements.size(); j++){
                ArrayList<String> temperary = findImplicant(allElements.get(i), allElements.get(j));
                if (temperary.size() != 0){
                    preImplicants.add(allElements.get(j));
                    boolean key = true;
                    Collections.sort(temperary);
                    flag = false;
                    for (int x = 0; x < implicants.size(); x++){
                        ArrayList<String> element = implicants.get(x);
                            Collections.sort(element);
                        key = !findKey(temperary, element);
                    }
                    for (int x = 0; x < preImplicants.size(); x++){
                        if (temperary.equals(preImplicants.get(x)) && allElements.get(j).size() == 2){
                            key = false;
                        }
                    }
                    if (key){
                        implicants.add(temperary);
                        preImplicants.add(allElements.get(j));
                    }
                }
                else if (allElements.size() - 1 - i == 1 && allElements.size() - j == 1){
                    for (int x = 0; x < preImplicants.size(); x++){
                        if (allElements.get(i).equals(preImplicants.get(x))){
                            var = false;
                        }
                    }
                }
            }
            if (flag && var) implicants.add(allElements.get(i));
        }
        boolean firstKey = true;
        for (int i = 0; i < implicants.size(); i++){
            if (allElements.get(allElements.size() - 1).equals(implicants.get(i))) firstKey = false;
        }
        for (int i = 0; i < preImplicants.size(); i++){
            if (allElements.get(allElements.size() - 1).equals(preImplicants.get(i))) firstKey = false;
        }
        if (firstKey) implicants.add(allElements.get(allElements.size() - 1));
        return implicants;
    }

    public static boolean findKey(ArrayList<String> temperary, ArrayList<String> element){
        return temperary.equals(element);
    }

    public static ArrayList<String> findImplicant(ArrayList<String> elementJ, ArrayList<String> elementX){
        ArrayList<String> implicant = new ArrayList<>();
        boolean flag = false;
        for (String element1: elementJ){
            for (String element2: elementX){
                if (element1.equals(element2)) implicant.add(element1);
                else if (elementJ.size() == 2){
                    for (int i = 0; i < element1.length(); i++){
                        for (int j = 0; j < element2.length(); j++){
                            if (!String.valueOf(element1.charAt(i)).equals(INVERSION) && element1.charAt(i) == element2.charAt(j)) flag = true;
                        }
                    }
                }
            }
        }
        if (elementJ.size() > 2 && elementJ.size() - implicant.size() == 1) return implicant;
        else if (elementJ.size() == 2 && elementJ.size() - implicant.size() == 1 && flag) return implicant;
        return new ArrayList<>();
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

    //Функция конвертации импликантов в строку СДНФ
    public static String convertImplicantsToStringSDNF(ArrayList<ArrayList<String>> implicants){
        String formula = "";
        for (int i = 0; i < implicants.size(); i++){
            formula += LEFT_BRACKET;
            for (int j = 0; j < implicants.get(i).size(); j++){
                formula += implicants.get(i).get(j);
                if (implicants.get(i).size() - j != 1){
                    formula += CONJUCTION;
                }
            }
            formula += RIGHT_BRACKET;
            if (implicants.size() - i != 1){
                formula += DISJUNCTION;
            }
        }
        return formula;
    }

    //Функция конвертации импликантов в строку СКНФ
    public static String convertImplicantsToStringSKNF(ArrayList<ArrayList<String>> implicants){
        String formula = "";
        for (int i = 0; i < implicants.size(); i++){
            formula += LEFT_BRACKET;
            for (int j = 0; j < implicants.get(i).size(); j++){
                formula += implicants.get(i).get(j);
                if (implicants.get(i).size() - j != 1){
                    formula += DISJUNCTION;
                }
            }
            formula += RIGHT_BRACKET;
            if (implicants.size() - i != 1){
                formula += CONJUCTION;
            }
        }
        return formula;
    }

    //Формула для подставления 0 и 1 вместо атомарных формул в СДНФ
    public static String endFormulaSDNF(String formula, List<List<String>> lines, List<String> variables, int index, String implicant){
        String equation = "";
        for (int i = 0; i < formula.length(); i++){
            if (String.valueOf(formula.charAt(i)).equals(LEFT_BRACKET) || String.valueOf(formula.charAt(i)).equals(RIGHT_BRACKET) ||
                    String.valueOf(formula.charAt(i)).equals(CONJUCTION) || String.valueOf(formula.charAt(i)).equals(DISJUNCTION) || String.valueOf(formula.charAt(i)).equals(INVERSION)){
                equation += formula.charAt(i);
            }
            if (Character.isUpperCase(formula.charAt(i))){
                String symbol = "" + formula.charAt(i);
                boolean flag = true;
                for (int j = 0; j < variables.size(); j++){
                    if (symbol.equals(variables.get(j))){
                        equation += lines.get(index).get(j);
                        flag = false;
                        break;
                    }
                }
                if (flag) equation += symbol;
            }
        }
        boolean flag = equationSolutionSDNF(equation);
        if (flag) return implicant;
        return "";
    }

    //Формула для подставления 0 и 1 вместо атомарных формул в СКНФ
    public static String endFormulaSKNF(String formula, List<List<String>> lines, List<String> variables, int index, String implicant){
        String equation = "";
        for (int i = 0; i < formula.length(); i++){
            if (String.valueOf(formula.charAt(i)).equals(LEFT_BRACKET) || String.valueOf(formula.charAt(i)).equals(RIGHT_BRACKET) ||
                    String.valueOf(formula.charAt(i)).equals(CONJUCTION) || String.valueOf(formula.charAt(i)).equals(DISJUNCTION) || String.valueOf(formula.charAt(i)).equals(INVERSION)){
                equation += formula.charAt(i);
            }
            if (Character.isUpperCase(formula.charAt(i))){
                String symbol = "" + formula.charAt(i);
                boolean flag = true;
                for (int j = 0; j < variables.size(); j++){
                    if (symbol.equals(variables.get(j))){
                        equation += lines.get(index).get(j);
                        flag = false;
                        break;
                    }
                }
                if (flag) equation += symbol;
            }
        }
        boolean flag = equationSolutionSKNF(equation);
        if (flag) return implicant;
        return "";
    }

    //Функция для решения уравнения СДНФ
    public static Boolean equationSolutionSDNF(String equation){
        String equationWithX = newEquation(equation);
        String result = "";
        String example = "";
        int element = 0;
        for (int i = 0; i < equationWithX.length(); i++){
            if (String.valueOf(equationWithX.charAt(i)).equals(RIGHT_BRACKET)){
                example += RIGHT_BRACKET;
                if (element != 0) result += getAnswerSDNF(example);
                element = 0;
                example = "";
            }
            if (String.valueOf(equationWithX.charAt(i)).equals(INVERSION)){
                if (String.valueOf(equationWithX.charAt(i + 1)).equals(ONE)) example += ZERO;
                else if (String.valueOf(equationWithX.charAt(i + 1)).equals(X)){
                    example += INVERSION + X;
                    element++;
                }
                else example += ONE;
                i++;
                continue;
            }
            if (Character.isDigit(equationWithX.charAt(i))) example += equationWithX.charAt(i);
            if (String.valueOf(equationWithX.charAt(i)).equals(CONJUCTION)) example += equationWithX.charAt(i);
            if (String.valueOf(equationWithX.charAt(i)).equals(LEFT_BRACKET)) example += equationWithX.charAt(i);
            if (Character.isLowerCase(equationWithX.charAt(i))){
                example += equationWithX.charAt(i);
                element++;
            }
        }
        int amountInverse = 0;
        int amountVariables = 0;
        for (int i = 0; i < result.length(); i++){
            if (String.valueOf(result.charAt(i)).equals(X)) amountVariables++;
            if (String.valueOf(result.charAt(i)).equals(INVERSION)) amountInverse++;
        }
        if (Math.abs(amountVariables - amountInverse) != 1 || (amountVariables == 1 && amountInverse == 0)) return true;
        else return false;
    }

    //Функция для решения уравнения СКНФ
    public static Boolean equationSolutionSKNF(String equation){
        String equationWithX = newEquation(equation);
        String result = DELIMITER;
        String example = "";
        int element = 0;
        for (int i = 0; i < equationWithX.length(); i++){
            if (String.valueOf(equationWithX.charAt(i)).equals(RIGHT_BRACKET)){
                example += RIGHT_BRACKET;
                if (element != 0) result += getAnswerSKNF(example)+DELIMITER;
                element = 0;
                example = "";
            }
            if (String.valueOf(equationWithX.charAt(i)).equals(INVERSION)){
                if (String.valueOf(equationWithX.charAt(i + 1)).equals(ONE)) example += ZERO;
                else if (String.valueOf(equationWithX.charAt(i + 1)).equals(X)){
                    example += INVERSION + X;
                    element++;
                }
                else example += ONE;
                i++;
                continue;
            }
            if (Character.isDigit(equationWithX.charAt(i))) example += equationWithX.charAt(i);
            if (String.valueOf(equationWithX.charAt(i)).equals(DISJUNCTION)) example += equationWithX.charAt(i);
            if (String.valueOf(equationWithX.charAt(i)).equals(LEFT_BRACKET)) example += equationWithX.charAt(i);
            if (Character.isLowerCase(equationWithX.charAt(i))){
                example += equationWithX.charAt(i);
                element++;
            }
        }
        boolean regex1 = false;
        boolean regex2 = false;
        Pattern pattern = Pattern.compile(REGEX1);
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) regex1 = true;
        Pattern newPattern = Pattern.compile(REGEX2);
        Matcher newMatcher = newPattern.matcher(result);
        if (newMatcher.find()) regex2 = true;
        if (regex1 && regex2) return false;
        else return true;
    }

    //Функция для замены переменной на x
    public static String newEquation(String equation){
        for (int i = 0; i < equation.length(); i++){
            if (Character.isUpperCase(equation.charAt(i))){
                equation = equation.replaceAll(String.valueOf(equation.charAt(i)), X);
            }
        }
        return equation;
    }

    //Функция для подсчёта примера СДНФ
    public static String getAnswerSDNF(String example){
        String answer = "";
        for (int i = 0; i < example.length(); i++){
            if (String.valueOf(example.charAt(i)).equals(INVERSION)){
                answer += String.valueOf(example.charAt(i)) + example.charAt(i + 1);
                i++;
                continue;
            }
            if (Character.isLowerCase(example.charAt(i))) answer += example.charAt(i);
            if (String.valueOf(example.charAt(i)).equals(ZERO)){
                answer = ZERO;
                break;
            }
        }
        if (answer.isEmpty()) answer = ONE;
        if (!answer.equals(ZERO) && !answer.equals(ONE)) {
            int amount = 0;
            for (int i = 0; i < answer.length(); i++) {
                if (Character.isLowerCase(answer.charAt(i))) amount++;
                if (String.valueOf(answer.charAt(i)).equals(INVERSION)) amount--;
            }
            if (answer.length() != 1 && amount % 2 != 0) return ZERO;
            else return answer;
        }
        return answer;
    }

    //Функция для подсчёта примера СКНФ
    public static String getAnswerSKNF(String example){
        String answer = "";
        for (int i = 0; i < example.length(); i++){
            if (String.valueOf(example.charAt(i)).equals(INVERSION)){
                answer += String.valueOf(example.charAt(i)) + example.charAt(i + 1);
                i++;
                continue;
            }
            if (Character.isLowerCase(example.charAt(i))) answer += example.charAt(i);
            if (String.valueOf(example.charAt(i)).equals(ONE)) answer += ONE;
        }
        return answer;
    }

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
    public static String tableFun(String formula, String sign, String sign2, boolean flag){
        List<String> constituents = createConstituence(formula);
        ArrayList<ArrayList<String>> implicants = calculationMethod(formula);
        String newFormula = "";
        if (sign.equals(CONJUCTION)) newFormula = convertImplicantsToStringSDNF(implicants);
        else newFormula = convertImplicantsToStringSKNF(implicants);
        implicants = calculationMethod(newFormula);
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
        if (flag) show(constituents, implicants, table, sign);
        return minimization(implicants, constituents, table, sign, sign2);
    }

    //Функция для вывода таблицы
    public static void show(List<String> constituents, ArrayList<ArrayList<String>> implicants, List<List<Boolean>> table, String sign){
        System.out.print("\t\t");
        for (String constituent: constituents) System.out.print(constituent+"\t");
        System.out.println();
        for (int i = 0; i < implicants.size(); i++){
            System.out.print(LEFT_BRACKET);
            for (int j = 0; j < implicants.get(i).size(); j++){
                System.out.print(implicants.get(i).get(j));
                if (implicants.get(i).size() - j != 1) System.out.print(sign);
            }
            System.out.print(RIGHT_BRACKET+"\t");
            for (int j = 0; j < table.get(i).size(); j++){
                System.out.print(table.get(i).get(j)+"\t\t");
            }
            System.out.println("\n");
        }
    }

    //Функция для выкидывания лишних импликант
    public static String minimization(ArrayList<ArrayList<String>> implicants, List<String> constituents, List<List<Boolean>> table, String sign, String sign2){
        List<Integer> index = new ArrayList<>();
        int k = 0;
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
            if (amount == COINCIDENCE){
                boolean key = true;
                for (Integer element: index){
                    if (implicantNumber == element) key = false;
                }
                if (key) index.add(implicantNumber);
            }
        }
        int find = 0;
        for (int i = 0; i < index.size(); i++){
            for (int j = 0; j < constituents.size(); j++){
                if (table.get(index.get(i)).get(j)) find++;
            }
        }
        if (find < constituents.size()){
            List<Integer> newIndex = new ArrayList<>();
            for (int i = 0; i < implicants.size(); i++){
                boolean findIndex = true;
                for (int j = 0; j < index.size(); j++){
                    if (i == index.get(j)) findIndex = false;
                }
                if (findIndex) newIndex.add(i);
            }
            List<Integer> values = new ArrayList<>();
            for (int i = 0; i < newIndex.size(); i++){
                int value = 0;
                for (int j = 0; j < constituents.size(); j++){
                    if (table.get(newIndex.get(i)).get(j)) value++;
                }
                values.add(value);
            }
            index.add(Collections.min(values));
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

    public static List<List<String>> createTableCarno(){
        List<List<String>> table = new ArrayList<>();
        List<String> line = new ArrayList<>();
        for (int i = 0; i < N + 1; i++){
            line.add(ZERO);
        }
        table.add(line);
        table.add(line);
        return table;
    }
    public static List<List<String>> makeTable(List<String> constituents, List<String> variables, List<List<String>> truthTable, boolean flag){
        List<List<String>> table = createTableCarno();
        int index = 0;
        int numberOfLine = 0;
        boolean num = true;
        if (flag){
            for (int i = 0; i < truthTable.size(); i++){
                if (truthTable.get(i).get(0).equals(ONE)){
                    index = 1;
                    if (num) numberOfLine = 0;
                    num = false;
                }
                String line = LEFT_BRACKET;
                for (int j = 0; j < N; j++){
                    if (truthTable.get(i).get(j).equals(ONE)) line += variables.get(j);
                    else line += INVERSION + variables.get(j);
                    if (N - j != 1) line += CONJUCTION;
                }
                line += RIGHT_BRACKET;
                boolean key = false;
                for (int j = 0; j < constituents.size(); j++){
                    if (line.equals(constituents.get(j))) key = true;
                }
                if (key){
                    List<String> list = new ArrayList<>();
                    for (int j = 0; j < table.size() * 2; j++){
                        list.add(table.get(index).get(j));
                    }
                    list.set(numberOfLine, ONE);
                    table.set(index, list);
                }
                numberOfLine++;
            }
        }
        else {
            for (int i = 0; i < truthTable.size(); i++){
                if (truthTable.get(i).get(0).equals(ONE)){
                    index = 1;
                    if (num) numberOfLine = 0;
                    num = false;
                }
                String line = LEFT_BRACKET;
                for (int j = 0; j < N; j++){
                    if (truthTable.get(i).get(j).equals(ZERO)) line += variables.get(j);
                    else line += INVERSION + variables.get(j);
                    if (N - j != 1) line += DISJUNCTION;
                }
                line += RIGHT_BRACKET;
                boolean key = true;
                for (int j = 0; j < constituents.size(); j++){
                    if (line.equals(constituents.get(j))) key = false;
                }
                if (key) {
                    List<String> list = new ArrayList<>();
                    for (int j = 0; j < table.size() * 2; j++){
                        list.add(table.get(index).get(j));
                    }
                    list.set(numberOfLine, ONE);
                    table.set(index, list);
                }
                numberOfLine++;
            }
        }
        String swap = table.get(0).get(3);
        table.get(0).set(3, table.get(0).get(2));
        table.get(0).set(2, swap);
        swap = table.get(1).get(3);
        table.get(1).set(3, table.get(1).get(2));
        table.get(1).set(2, swap);
        return table;
    }
    public static String minimizationKarno(String formula, String sign1, String sign2, boolean flag){
        List<String> vars = findNumberOfVariables(formula);
        List<List<String>> truthTable = createTruthTable(vars);
        List<String> constituents = createConstituence(formula);
        List<String> variables = findNumberOfVariables(formula);
        List<List<String>> table = makeTable(constituents, variables, truthTable, flag);
        showTable(vars, table, truthTable);
        if (flag) return tableFun(formula, sign1, sign2,false);
        else return makeMinimizationSKNF(formula, sign1, sign2, false);
    }

    public static void showTable(List<String> vars, List<List<String>> table, List<List<String>> truthTable){
        System.out.print("\t   ");
        for (int i = vars.size() - vars.size() / 2 - 1; i < vars.size(); i++) {
            System.out.print(vars.get(i) + "\t\t");
        }
        System.out.println();
        System.out.println(str);
        String a = vars.get(0)+"  0\t";
        String b = "   1\t";
        for (int i = 0; i < table.size(); i++){
            for (int j = 0; j < 4; j++){
                if (i == 0) a += table.get(i).get(j) + "\t";
                else b += table.get(i).get(j) + "\t";
            }
        }
        System.out.println(a + "\n" + b);
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
