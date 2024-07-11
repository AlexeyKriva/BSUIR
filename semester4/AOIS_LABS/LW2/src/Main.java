import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String formula = "((A*B)+C)";
        System.out.println(formula);
        if (TruthTable.formulaValidation(formula)) {
            List<String> variables = TruthTable.findNumberOfVariables(formula);
            List<List<String>> lines = TruthTable.createTruthTable(variables);
            formula = TruthTable.newFormula(formula, variables);
            TruthTable.showTruthTable(lines, variables, formula);
        } else System.out.println("Введено неверное выражение !!!");
    }
}