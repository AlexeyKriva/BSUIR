import java.util.List;

public class BuildSDNFAndSKNF {
    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";
    public static final String INVERSION = "!";
    public static final String CONJUCTION = "*";
    public static final String DISJUNCTION = "+";
    public static final String TRUE = "1";
    public static final String FALSE = "0";
    public static final char[] VARIABLES = {'X', 'Y', 'Z'};
    public static final String[] VARIABLES1 = {"X4", "X3", "X2", "X1"};
    //Функция для вывода СКНФ элементами
    public static String sknfElements(List<List<String>> lines, List<String> answers, int n, int left, int right){
        String outSKNF = "";
        for (int i = 0; i < Math.pow(2, n); i++){
            if (answers.get(i).substring(left, right).equals(FALSE)) {
                outSKNF += LEFT_BRACKET;
                for (int j = 0; j < n; j++) {
                    if (lines.get(i).get(j).equals(TRUE)) outSKNF += INVERSION;
                    outSKNF += VARIABLES[j];
                    if (n - j != 1) outSKNF += DISJUNCTION;
                }
                outSKNF += RIGHT_BRACKET + CONJUCTION;
            }
        }
        System.out.println(outSKNF.substring(0, outSKNF.length() - 1));
        return outSKNF.substring(0, outSKNF.length() - 1);
    }

    public static String sknfElementsD2481(List<List<String>> lines, List<String> answers, int n){
        String outSKNF = "";
        for (int i = 0; i < answers.size(); i++){
            if (answers.get(i).equals(FALSE)) {
                outSKNF += LEFT_BRACKET;
                for (int j = n - 1; j >= 0; j--) {
                    if (lines.get(i).get(j).equals(TRUE)) outSKNF += INVERSION;
                    outSKNF += VARIABLES1[j];
                    if (n - j != 4) outSKNF += DISJUNCTION;
                }
                outSKNF += RIGHT_BRACKET + CONJUCTION;
            }
        }
        return outSKNF.substring(0, outSKNF.length() - 1);
    }
}
