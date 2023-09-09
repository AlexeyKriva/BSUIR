public class Main {
    public static void main(String[] args) {
        String sdnf = "(!A*!B*C)+(!A*B*!C)+(!A*B*C)";
        String sknf = "(A+B+C)*(!A+B+C)*(!A+B+!C)*(A+!B+C)*(!A+!B+!C)";
        System.out.println("Расчётный метод:\nСДНФ: "+sdnf);
        System.out.println("ТДНФ: "+TruthTable.makeMinimizationSDNF(sdnf, TruthTable.CONJUCTION, TruthTable.DISJUNCTION,true));
        System.out.println("СКНФ: "+sknf);
        System.out.println("ТКНФ: "+TruthTable.makeMinimizationSKNF(sknf, TruthTable.DISJUNCTION, TruthTable.CONJUCTION,true));
        System.out.println("_______________________________\nМетод Квайна-Макласски:");
        System.out.println("ТДНФ: "+TruthTable.tableFun(sdnf, TruthTable.CONJUCTION, TruthTable.DISJUNCTION, true)+"\n");
        System.out.println("ТКНФ: "+TruthTable.tableFun(sknf, TruthTable.DISJUNCTION, TruthTable.CONJUCTION, true)+"\n");
        System.out.println("_______________________________\nМетод Вейча-Карно:");
        System.out.println("ТДНФ: "+TruthTable.minimizationKarno(sdnf, TruthTable.CONJUCTION, TruthTable.DISJUNCTION, true));
        System.out.println("ТКНФ: "+TruthTable.minimizationKarno(sknf, TruthTable.DISJUNCTION, TruthTable.CONJUCTION, false));
    }
}