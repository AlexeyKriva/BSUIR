import java.util.List;

public class BuildSDNF {
    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";
    public static final String INVERSION = "!";
    public static final String CONJUCTION = "*";
    public static final String DISJUNCTION = "+";
    public static final String TRUE = "1";
    public static final String FALSE = "0";
    public static final String[] VARIABLES = {"q1", "q2", "q3", "v"};

    //Функция для вывода СДНФ элементами
    public static String sdnfElements(String[][] lines, List<List<String>> newLines, String[] answers, int n, int start){
        String outSDNF = "";
        for (int i = 0; i < 2 * Math.pow(2, n); i++){
            if (newLines.get(i).get(start).equals(TRUE)) {
                outSDNF += LEFT_BRACKET;
                for (int j = 0; j < n; j++) {
                    if (lines[i][start].equals(FALSE)) outSDNF += INVERSION;
                    outSDNF += VARIABLES[j];
                    outSDNF += CONJUCTION;
                }
                if (answers[i].equals(FALSE)) outSDNF += INVERSION;
                outSDNF += VARIABLES[VARIABLES.length - 1] + RIGHT_BRACKET + DISJUNCTION;
            }
        }
        System.out.println(outSDNF.substring(0, outSDNF.length() - 1));
        return outSDNF.substring(0, outSDNF.length() - 1);
    }

    public static String getSDNF(int n){
        if (n == 0){
            return "(!q1*!q2*!q3*v)+(q1*!q2*!q3*v)";
        }
        else if (n == 1){
            return "(!q1*!q2*!q3*v)+(!q1*q2*!q3*v)+(q1*!q2*!q3*v)+(q1*q2*!q3*v)";
        }
        else {
            return "(!q1*!q2*!q3*v)+(!q1*!q2*q3*v)+(!q1*q2*!q3*v)+(!q1*q2*q3*v)+(q1*!q2*!q3*v)+(q1*!q2*q3*v)+(q1*q2*!q3*v)+(q1*q2*q3*v)";
        }
    }
}
