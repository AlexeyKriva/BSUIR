import java.util.ArrayList;
import java.util.List;

public class Sum {
    public static final String ZERO = "0";
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int FOUR = 4;


    //Нахождение сумм для таблицы истинности
    public static List<String> sum(List<List<String>> truthTable, int n){
        List<String> answers = new ArrayList<>();
        for (int i = 0; i < Math.pow(TWO, n); i++){
            int summa = 0;
            for(int j = 0; j < n; j++){
                summa += Integer.parseInt(truthTable.get(i).get(j));
            }
            if (summa <= ONE){
                answers.add(summa+"\t"+ZERO);
            }
            else if (summa == TWO){
                answers.add(ZERO + "\t" + (summa - 1));
            }
            else {
                answers.add((summa - TWO) + "\t" + (summa - TWO));
            }
        }
        return answers;
    }

    //Функция для нахождения суммы
    public static String sum(String x1, String x2){
        String binSum = "";
        String tempereryBinSum = sumBackOrder(x1, x2);
        binSum += createAnswer(tempereryBinSum);
        return binSum;
    }

    //Сумма чисел
    public static String sumBackOrder(String x1, String x2){
        String binSum = "";
        boolean flag = false;
        for(int i = FOUR - 1; i >= 0; i--){
            if(x1.charAt(i) == '0' && x2.charAt(i) == '0'){
                if(!flag){ binSum += "0"; }
                else{
                    binSum += "1";
                    flag = false;
                }
            }
            if((x1.charAt(i) == '1' && x2.charAt(i) == '0') || (x1.charAt(i) == '0' && x2.charAt(i) == '1')){
                if(!flag){ binSum += "1"; }
                else{ binSum += "0"; }
            }
            if(x1.charAt(i) == '1' &&  x2.charAt(i) == '1'){
                if(!flag) {
                    binSum += "0";
                    flag = true;
                }
                else{ binSum += "1"; }
            }
        }
        return binSum;
    }

    //Перевод результата математической операции
    public static String createAnswer(String tempereryBinNum){
        StringBuilder newTempereryBinNum = new StringBuilder(tempereryBinNum);
        newTempereryBinNum.reverse();
        String binNum = "" + newTempereryBinNum;
        return binNum;
    }
}
