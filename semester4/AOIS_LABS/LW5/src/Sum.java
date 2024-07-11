public class Sum {
    public static final int FOUR = 4;
    public static final String ZERO = "0";
    public static final String ONE = "0001";
    public static final String BIG_ZERO = "0000";

    //Функция для нахождения суммы
    public static String sum(String x1, String x2){
        String binSum = "";
        String tempereryBinSum = sumBackOrder(x1, x2);
        binSum += createAnswer(tempereryBinSum);
        return binSum;
    }

    //Функция для определения знака
    public static String defineSignOfSumAndDiff(String number1, String number2){
        String integerPart1 = findIntegerPart(number1);
        String integerPart2 = findIntegerPart(number2);
        if((fromStringToLong(integerPart1) > fromStringToLong(integerPart2) && number1.charAt(0) == '0') ||
                (fromStringToLong(integerPart1) < fromStringToLong(integerPart2) && number2.charAt(0) == '0')){ return "0"; }
        else if(fromStringToLong(integerPart1) == fromStringToLong(integerPart2)){ return "0"; }
        else{ return "1"; }
    }

    //Нахождение модуля числа в двоичной системе
    public static String findIntegerPart(String binNum){
        String integerPart = "";
        for(int i = 1; i < binNum.length(); i++){
            if(binNum.charAt(i) == '1'){
                for(int j = i; j < binNum.length(); j++){ integerPart += binNum.charAt(j); }
                break;
            }
        }
        if(integerPart.equals("")){ integerPart = "0"; }
        return integerPart;
    }

    //Преобразование строки в число
    public static long fromStringToLong(String integerPart){
        return Long.parseLong(integerPart);
    }

    //Нахождение суммы с отрицательными числами
    public static String difference(String x1, String x2){
        String binSum = "";
        if(comparison(x1, x2) && x1.charAt(0) == '0' && x2.charAt(0) == '1'){
            binSum = sum(x1, backCode(x2));
            binSum = differenceResult(binSum);
            binSum = sum(ZERO + binSum, ONE);
            return binSum;
        }
        else { binSum = NumZero(x1, x2); }
        return binSum;
    }

    //Сравнение чисел в двоичной системе счисления
    public static boolean comparison(String x1, String x2){
        String integerPartX1 = findIntegerPart(x1);
        String integerPartX2 = findIntegerPart(x2);
        long num1 = fromStringToLong(integerPartX1);
        long num2 = fromStringToLong(integerPartX2);
        return num1 > num2;
    }

    //Функция для определения результата разности
    public static String differenceResult(String binNum){
        StringBuilder tempereryBinSum = new StringBuilder(binNum);
        tempereryBinSum.setCharAt(0, '0');
        binNum = tempereryBinSum.substring(1, tempereryBinSum.length());
        return binNum;
    }

    //Проверка на нули
    public static String NumZero(String x1, String x2){
        return BIG_ZERO;
    }

    //Сумма чисел
    public static String sumBackOrder(String x1, String x2){
        String binSum = "";
        boolean flag = false;
        for(int i = FOUR - 1; i > 0; i--){
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
        return createAnswer( ZERO + binSum);
    }

    //Перевод числа в обратный код
    public static String backCode(String binSum){
        String binNum = "1";
        for(int i = 1; i < binSum.length(); i++){
            if(binSum.charAt(i) == '0'){ binNum += "1"; }
            else{ binNum += "0"; }
        }
        return binNum;
    }

    //Перевод числа в дополнительный код
    public static String addCode(String binSum){
        binSum = backCode(binSum);
        binSum = sum(binSum, ONE);
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
