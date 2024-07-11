import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Table {
    private List<String> words;
    private static final char ZERO = '0';
    private static final char ONE = '1';

    public Table(){
        this.words = new ArrayList<>();
    }

    public void randomFilling(int size){
        Random random = new Random();
        for (int i = 0; i < size; i++){
            String word = "";
            for (int j = 0; j < size; j++){
                if (random.nextInt(2) == 0)
                    word += ZERO;
                else
                    word += ONE;
            }
            this.words.add(division(word, i));
        }
    }

    public String division(String word, int index){
        String part = "";
        for (int i = word.length() - 1; i > word.length() - index - 1; i--){
            part = word.charAt(i) + part;
        }
        return part + word.substring(index);
    }

    public String getValue(int index){
        String value = "";
        for (int i = index; i < this.words.get(index).length(); i++){
            value = value + this.words.get(index).charAt(i);
        }
        for (int i = 0; i < index; i++){
            value = value + this.words.get(index).charAt(i);
        }
        return value;
    }

    public String getDiagonal(int index){
        List<String> values = newTable();
        String answer = "";
        for (int i = 0; i < values.size(); i++){
            answer = answer + values.get(i).charAt(index);
        }
        return answer;
    }

    public String f2(int index1, int index2){
        String answer = "";
        String value1 = getValue(index1);
        String value2 = getValue(index2);
        System.out.println("X1/\\!X2");
        String preValue2 = "";
        System.out.print("!"+value2+"=");
        for (int i = value2.length() - 1; i >= 0; i--){
            preValue2 = inversion(value2, i) + preValue2;
        }
        System.out.println(preValue2);
        System.out.println(value1);
        System.out.println(preValue2);
        for (int i = value1.length() - 1; i >= 0; i--){
            answer = conjuction(value1, preValue2, i) + answer;
        }
        return answer;
    }

    public String f7(int index1, int index2){
        String answer = "";
        String value1 = getValue(index1);
        String value2 = getValue(index2);
        System.out.println("X1\\/X2");
        System.out.println(value1);
        System.out.println(value2);
        for (int i = value1.length() - 1; i >= 0; i--){
            answer = disjunction(value1, value2, i) + answer;
        }
        return answer;
    }

    public String f8(int index1, int index2){
        String answer = "";
        String value1 = getValue(index1);
        String value2 = getValue(index2);
        System.out.println("!(X1\\/X2)");
        System.out.println(value1);
        System.out.println(value2);
        for (int i = value1.length() - 1; i >= 0; i--){
            answer = disjunction(value1, value2, i) + answer;
        }
        String preAnswer = "";
        System.out.print("!"+answer+"=");
        for (int i = value2.length() - 1; i >= 0; i--){
            preAnswer = inversion(answer, i) + preAnswer;
        }
        return preAnswer;
    }

    public String f13(int index1, int index2){
        String answer = "";
        String value1 = getValue(index1);
        String value2 = getValue(index2);
        System.out.println("!X1\\/X2");
        String preValue1 = "";
        System.out.print("!"+value1+"=");
        for (int i = value1.length() - 1; i >= 0; i--){
            preValue1 = inversion(value1, i) + preValue1;
        }
        System.out.println(preValue1);
        System.out.println(preValue1);
        System.out.println(value2);
        for (int i = value1.length() - 1; i >= 0; i--){
            answer = disjunction(preValue1, value2, i) + answer;
        }
        return answer;
    }

    public List<String> newTable(){
        List<String> newWords = new ArrayList<>();
        for (int i = 0; i < this.words.size(); i++){
            String value = getValue(i);
            newWords.add(value);
        }
        return newWords;
    }

    public List<String> rangeSearch(String leftRange, String rightRange){
        ArrayList<String> values = new ArrayList<>();
        ArrayList<Boolean> flags = new ArrayList<>();
        List<String> newWords = newTable();
        for (int i = 0; i < newWords.size(); i++){
            flags.add(true);
            System.out.print(newWords.get(i)+"\t");
        }
        System.out.println();
        int index = 0;
        for (String value: newWords) {
            boolean g = false;
            boolean l = false;
            for (int i = 0; i < value.length(); i++){
                g = g || (!logicOperation(leftRange.charAt(i)) && logicOperation(value.charAt(i)) && !l);
                l = l || (logicOperation(leftRange.charAt(i)) && !logicOperation(value.charAt(i)) && !g);
            }
            if (!g && l) flags.set(index, false);
            index++;
        }
        int identificator = 0;
        for (String value: words) {
            boolean g = false;
            boolean l = false;
            for (int i = 0; i < value.length(); i++){
                g = g || (!logicOperation(rightRange.charAt(i)) && logicOperation(value.charAt(i)) && !l);
                l = l || (logicOperation(rightRange.charAt(i)) && !logicOperation(value.charAt(i)) && !g);
            }
            if (g && !l) flags.set(identificator, false);
            identificator++;
        }
        for (int i = 0; i < flags.size(); i++){
            if (flags.get(i)) values.add(newWords.get(i));
        }
        return values;
    }

    public List<String> find(String template){
        List<String> values = newTable();
        List<String> answers = new ArrayList<>();
        for (int i = 0; i < this.words.size(); i++){
            if (template.equals(values.get(i).substring(0, 3)))
                answers.add(values.get(i));
        }
        for (int i = 0; i < answers.size(); i++){
            String number1 = ZERO + answers.get(i).substring(3, 7);
            String number2 = ZERO + answers.get(i).substring(7, 11);
            String ans = sum(number1, number2);
            answers.set(i, answers.get(i).substring(0, 11) + ans);
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
        for(int i = 4; i >= 0; i--){
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

    public boolean logicOperation(char number){
        return number == ONE;
    }

    public char conjuction(String value1, String value2, int i){
        if (value1.charAt(i) == ONE && value2.charAt(i) == ONE)
            return ONE;
        return ZERO;
    }

    public char disjunction(String value1, String value2, int i){
        if (value1.charAt(i) == ZERO && value2.charAt(i) == ZERO)
            return ZERO;
        return ONE;
    }

    public char inversion(String value, int i){
        if (value.charAt(i) == ONE)
            return ZERO;
        return ONE;
    }

    public void show(){
        for (int i = 0; i < this.words.size(); i++){
            for (int j = 0; j < this.words.size(); j++){
                System.out.print(this.words.get(j).charAt(i) + "\t");
            }
            System.out.println();
        }
    }
}
