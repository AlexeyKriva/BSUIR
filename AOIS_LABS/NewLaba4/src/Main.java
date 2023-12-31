import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final int INPUT = 3;
    public static final int LEFT = 0;
    public static final String CONJUCTION = "+";
    public static final String DISJUNCTION = "*";
    public static void main(String[] args) {
        List<List<String>> truthTable = TruthTable.createTruthTable(INPUT);
        List<String> answers = Sum.sum(truthTable, INPUT);
        TruthTable.showTruthTable(truthTable, answers, INPUT);
        System.out.println("Вывод СКНФ для четвёртого стоблца:");
        String sknf1 = BuildSDNFAndSKNF.sknfElements(truthTable, answers, INPUT, LEFT, LEFT + 1);
        System.out.println("Вывод СКНФ для пятого стоблца:");
        String sknf2 = BuildSDNFAndSKNF.sknfElements(truthTable, answers, INPUT, answers.get(LEFT).length() - 1, answers.get(LEFT).length());
        System.out.println("Вывод ТКНФ для четвёртого стоблца:");
        String tknf1 = Minimithation.kvainMakKlasski(sknf1, CONJUCTION, DISJUNCTION);
        if (!tknf1.equals("")) System.out.println("Минимизировання функция: "+tknf1);
        else System.out.println("Функция не минимизируется: "+sknf1);
        System.out.println("Вывод ТКНФ для пятого стоблца:");
        String tknf2 = Minimithation.kvainMakKlasski(sknf2, CONJUCTION, DISJUNCTION);
        if (!tknf2.equals("")) System.out.println("Минимизировання функция: "+tknf2);
        else System.out.println("Функция не минимизируется: "+sknf2);
        List<List<String>> truthTable1 = TruthTable.createTruthTable(INPUT + 1);
        List<List<String>> functions = TruthTable.tableD8421(truthTable1, INPUT + 1);
        List<String> ans1 = Minimithation.getFunction(functions, INPUT - 3);
        List<String> ans2 = Minimithation.getFunction(functions, INPUT - 2);
        List<String> ans3 = Minimithation.getFunction(functions, INPUT - 1);
        List<String> ans4 = Minimithation.getFunction(functions, INPUT);
        String sknf3 = BuildSDNFAndSKNF.sknfElementsD2481(truthTable1, ans1, INPUT + 1);
        System.out.println("Y1:\n" + sknf3 + "\n" + Minimithation.minimizationN(0));
        String sknf4 = BuildSDNFAndSKNF.sknfElementsD2481(truthTable1, ans2, INPUT + 1);
        System.out.println("Y2:\n" + sknf4 + "\n" + Minimithation.minimizationN(1));
        String sknf5 = BuildSDNFAndSKNF.sknfElementsD2481(truthTable1, ans3, INPUT + 1);
        System.out.println("Y3:\n" + sknf5 + "\n" + Minimithation.minimizationN(2));
        String sknf6 = BuildSDNFAndSKNF.sknfElementsD2481(truthTable1, ans4, INPUT + 1);
        System.out.println("Y4:\n" + sknf6 + "\n" + Minimithation.minimizationN(3));
    }
}