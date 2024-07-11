import java.util.*;

public class Main {

    public static final String ONE = "0000000000000001";
    public static final String BIG_ZERO = "0000000000000000";
    public static final String SMALL_ZERO = "00000000";
    public static final long CENTER = 127;
    public static final int SIXTEEN = 16;
    public static final int EIGHT = 8;
    public static final int MANTISS_SIZE = 23;
    public static final String INF = "inf";


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean isAppRunning = true;
        while(isAppRunning) {
            System.out.println("1 - операции над числами:\n2 - выход из программы");
            int n=scan.nextInt();
            switch(n) {

                case 1:
                    int x1 = scan.nextInt();
                    int x2 = scan.nextInt();

                    //Положительные числа в прямом коде
                    String BinX1 = directCode(Math.abs(x1));
                    String BinX2 = directCode(Math.abs(x2));

                    //Отрицательные числа в прямом коде
                    String BinNegX1 = directCode(-Math.abs(x1));
                    String BinNegX2 = directCode(-Math.abs(x2));

                    //Отрицательные числа в обратном коде
                    String BinBackX1 = backCode(BinX1);
                    String BinBackX2 = backCode(BinX2);

                    //Отрицательные числа в дополнительном коде
                    String BinAddX1 = addCode(BinX1);
                    String BinAddX2 = addCode(BinX2);

                    //Сумма положительных чисел
                    String BinSum = sum(BinX1, BinX2);

                    //Сумма с отрицательными числами
                    String BinDiff1 = difference(BinX1, BinNegX2);
                    String BinDiff2 = difference(BinX2, BinNegX1);
                    String BinDiff3 = difference(BinNegX1, BinNegX2);

                    //Произведение двоичных чисел
                    String BinProd1 = product(BinX1, BinX2);
                    String BinProd2 = product(BinX1, BinNegX2);
                    String BinProd3 = product(BinX2, BinNegX1);
                    String BinProd4 = product(BinNegX1, BinNegX2);

                    //Вывод всего на экран
                    System.out.println("Прямой код:\n" + Math.abs(x1) + "_10=" + BinX1 + "_2\n" + Math.abs(x2) + "_10=" + BinX2 + "_2");
                    System.out.println("-"+Math.abs(x1) + "_10=" + BinNegX1 + "_2\n-" + Math.abs(x2) + "_10=" + BinNegX2 + "_2");
                    System.out.println("Обратный код:\n-" + Math.abs(x1) + "_10=" + BinBackX1 + "_2\n-" + Math.abs(x2) + "_10=" + BinBackX2 + "_2");
                    System.out.println("Дополнительный код:\n-" + Math.abs(x1) + "_10=" + BinAddX1 + "_2\n-" + Math.abs(x2) + "_10=" + BinAddX2 + "_2");
                    System.out.println("Математические операции:");
                    System.out.println("+"+Math.abs(x1)+"+(+"+Math.abs(x2)+"):\t" + BinSum+"="+ transFromBinToDecInt(BinSum));
                    System.out.println("+"+Math.abs(x1)+"+(-"+Math.abs(x2)+"):\t" + BinDiff1+"="+ transFromBinToDecInt(BinDiff1));
                    System.out.println("-"+Math.abs(x1)+"+(+"+Math.abs(x2)+"):\t" + BinDiff2+"="+ transFromBinToDecInt(BinDiff2));
                    System.out.println("-"+Math.abs(x1)+"+(-"+Math.abs(x2)+"):\t" + BinDiff3+"="+ transFromBinToDecInt(BinDiff3));
                    System.out.println("+"+Math.abs(x1)+"*(+"+Math.abs(x2)+"):\t" + BinProd1+"="+ transFromBinToDecInt(BinProd1));
                    System.out.println("+"+Math.abs(x1)+"*(-"+Math.abs(x2)+"):\t" + BinProd2+"="+ transFromBinToDecInt(BinProd2));
                    System.out.println("-"+Math.abs(x1)+"*(+"+Math.abs(x2)+"):\t" + BinProd3+"="+ transFromBinToDecInt(BinProd3));
                    System.out.println("-"+Math.abs(x1)+"*(-"+Math.abs(x2)+"):\t" + BinProd4+"="+ transFromBinToDecInt(BinProd4));

                    //Деление двоичных чисел
                    String binDiv1 = "";
                    String binDiv2 = "";
                    String binDiv3 = "";
                    String binDiv4 = "";
                    if(Math.abs(x1) >= Math.abs(x2)){
                        binDiv1 = division(BinX1, BinX2);
                        binDiv2 = division(BinX1, BinNegX2);
                        binDiv3 = division(BinNegX1, BinX2);
                        binDiv4 = division(BinNegX1, BinNegX2);
                        if(Math.abs(x2) == 0){
                            binDiv1 = INF;
                            binDiv2 = INF;
                            binDiv3 = INF;
                            binDiv4 = INF;
                        }
                        if(binDiv1.equals(INF)) {
                            System.out.println("+" + Math.abs(x1) + "/(+" + Math.abs(x2) + "):\t" + binDiv1);
                            System.out.println("-" + Math.abs(x1) + "/(+" + Math.abs(x2) + "):\t" + binDiv2);
                            System.out.println("+" + Math.abs(x1) + "/(-" + Math.abs(x2) + "):\t" + binDiv3);
                            System.out.println("-" + Math.abs(x1) + "/(-" + Math.abs(x2) + "):\t" + binDiv4);
                        }
                        else{
                            System.out.println("+" + Math.abs(x1) + "/(+" + Math.abs(x2) + "):\t" + binDiv1 + "=" + transFromBinToDecInt(binDiv1));
                            System.out.println("-" + Math.abs(x1) + "/(+" + Math.abs(x2) + "):\t" + binDiv2 + "=" + transFromBinToDecInt(binDiv2));
                            System.out.println("+" + Math.abs(x1) + "/(-" + Math.abs(x2) + "):\t" + binDiv3 + "=" + transFromBinToDecInt(binDiv3));
                            System.out.println("-" + Math.abs(x1) + "/(-" + Math.abs(x2) + "):\t" + binDiv4 + "=" + transFromBinToDecInt(binDiv4));
                        }
                    }
                    else{
                        binDiv1 = division(BinX2, BinX1);
                        binDiv2 = division(BinX2, BinNegX1);
                        binDiv3 = division(BinNegX2, BinX1);
                        binDiv4 = division(BinNegX2, BinNegX1);
                        if(Math.abs(x1) == 0){
                            binDiv1 = INF;
                            binDiv2 = INF;
                            binDiv3 = INF;
                            binDiv4 = INF;
                        }
                        if(binDiv1.equals(INF)) {
                            System.out.println("+" + Math.abs(x2) + "/(+" + Math.abs(x1) + "):\t" + binDiv1);
                            System.out.println("-" + Math.abs(x2) + "/(+" + Math.abs(x1) + "):\t" + binDiv2);
                            System.out.println("+" + Math.abs(x2) + "/(-" + Math.abs(x1) + "):\t" + binDiv3);
                            System.out.println("-" + Math.abs(x2) + "/(-" + Math.abs(x1) + "):\t" + binDiv4);
                        }
                        else{
                            System.out.println("+" + Math.abs(x2) + "/(+" + Math.abs(x1) + "):\t" + binDiv1 + "=" + transFromBinToDecInt(binDiv1));
                            System.out.println("-" + Math.abs(x2) + "/(+" + Math.abs(x1) + "):\t" + binDiv2 + "=" + transFromBinToDecInt(binDiv2));
                            System.out.println("+" + Math.abs(x2) + "/(-" + Math.abs(x1) + "):\t" + binDiv3 + "=" + transFromBinToDecInt(binDiv3));
                            System.out.println("-" + Math.abs(x2) + "/(-" + Math.abs(x1) + "):\t" + binDiv4 + "=" + transFromBinToDecInt(binDiv4));
                        }
                    }

                    System.out.println("Введите целую часть первого числа:");
                    int x3 = scan.nextInt();
                    System.out.println("Введите целую часть второго числа:");
                    int x4 = scan.nextInt();
                    System.out.println("Введите дробную часть первого числа:");
                    int x5 = scan.nextInt();
                    System.out.println("Введите дробную часть второго числа:");
                    int x6 = scan.nextInt();

                    //Перевод чисел с плавающей точкой в двоичную систему
                    String binX3 = findIntegerPart(directCode(x3));
                    String binX4 = findIntegerPart(directCode(x4));
                    String binX5 = findDecimicalIntegerPart(DirectCodeForFractionalPart(x5));
                    String binX6 = findDecimicalIntegerPart(DirectCodeForFractionalPart(x6));
                    System.out.println("Прямой код\n"+x3+"."+x5+"_10="+binX3+"."+binX5+"_2\n"+x4+"."+x6+"_10="+binX4+"."+binX6+"_2");
                    String[] orderAndMantis1 = numberOrderAndMantis(binX3+"."+binX5);
                    String[] orderAndMantis2 = numberOrderAndMantis(binX4+"."+binX6);
                    String mantis1 = addZero23(orderAndMantis1[1]);
                    String mantis2 = addZero23(orderAndMantis2[1]);
                    String order1 = resultBinNum(orderAndMantis1[0]);
                    String order2 = resultBinNum(orderAndMantis2[0]);
                    System.out.println("Сложение чисел с плавающей точке:\n0." + order1 + "." + mantis1 + "+" + "0." + order2 + "." + mantis2 + "=");
                    String[] array = orderAlignment(order1, order2, mantis1, mantis2);
                    System.out.println("=0." + array[0] + "." + array[1] + "+0." + array[0] + "." + array[2] + "=");
                    double sumMantis = mantisSum(array);
                    System.out.println(sumMantis);
                    break;

                case 2:
                    isAppRunning = false;
                    break;

                default:
                    System.out.println("Выберите либо 1, либо 2!!!");

            }
        }
    }



    //Нахождение порядка и мантисы числа
    public static String[] numberOrderAndMantis(String number){
        String mantis = "";
        String order = "";
        int counter = -1;
        for(int i = 0; i < number.length(); i++){
            if(number.charAt(i) == '.'){
                order = Integer.toString(i - 1);
                mantis = number.substring(1, i) + number.substring(i + 1);
                if(i == 1 && number.charAt(i - 1) == '0'){
                    for(int j = i + 1; j < number.length(); j++){
                        if(number.charAt(j) == '1'){ break; }
                        counter--;
                    }
                    order = Integer.toString(counter);
                    mantis = number.substring(Math.abs(counter) + 2);
                }
                break;
            }
        }
        String[] OrderAndMantis = new String[2];
        OrderAndMantis[0] = order;
        OrderAndMantis[1] = mantis;
        return OrderAndMantis;
    }



    //Нахождение порядка числа
    public static String resultBinNum(String OrderAndMantis){
        int number = (int) (CENTER + fromStringToLong(OrderAndMantis));
        String binNum = findIntegerPart(directCode(number));
        int size = binNum.length();
        for(int i = 0; i < EIGHT - size; i++){ binNum = "0" + binNum; }
        return binNum;
    }



    //Выравнивание порядков
    public static String[] orderAlignment(String order1, String order2, String mantissa1, String mantissa2){
        String numb = "1";
        if(order1.compareTo(order2) == 0){ numb = "2"; }
        String diff = "";
        long decimalDiff = 0;
        String[] array = new String[4];
        if(fromStringToLong(order1) >= fromStringToLong(order2)){
            diff = difference(SMALL_ZERO + order1, "1" + SMALL_ZERO.substring(1, 8) + order2);
            decimalDiff = fromStringToLong(diff);
            mantissa2 = mantissaOffset(decimalDiff, order2, mantissa2);
            array = createArray(order1, mantissa1, mantissa2, numb);
        }
        else{
            diff = difference(SMALL_ZERO + order2, "1" + SMALL_ZERO.substring(1, 8) + order1);
            decimalDiff = fromStringToLong(diff);
            mantissa1 = mantissaOffset(decimalDiff, order1, mantissa1);
            array = createArray(order2, mantissa1, mantissa2, numb);
        }
        array[3] = numb;
        return array;
    }



    //Заполнение массива
    public static String[] createArray(String order, String mantissa1, String mantissa2, String numb){
        String[] numbers = new String[4];
        numbers[0] = order;
        numbers[1] = mantissa1;
        numbers[2] = mantissa2;
        numbers[3] = numb;
        return numbers;
    }



    //Смещение мантисы числа
    public static String mantissaOffset(long order, String preOrder, String mantissa){
        String len = "0" + Long.toString(order);
        long size = transFromBinToDecInt(len);
        for (int i = 0; i < size; i++) {
            if (i == 0) { mantissa = "1" + mantissa; }
            else { mantissa = "0" + mantissa; }
        }
        return mantissa.substring(0, MANTISS_SIZE);
    }



    //Добавление нулей к мантиссе
    public static String addZero23(String mantis){
        int size = mantis.length();
        for(int i = 0; i < MANTISS_SIZE - size; i++){ mantis += "0"; }
        return mantis;
    }

    //Сумма чисел
    public static double mantisSum(String[] numbers){
        String[] res = fractionalSum(numbers);
        String mantisResult = res[1];
        System.out.print("=0."+ res[0]+"."+mantisResult+"=");
        mantisResult = "1" + mantisResult;
        double order = (double) (transFromBinToDecInt("0" + res[0]) - CENTER);
        double degree = 0;
        double result = 0;
        for(int i = 0; i < MANTISS_SIZE; i++){
            String numb = "" + mantisResult.charAt(i);
            result += (double) (fromStringToLong(numb)) * Math.pow(2, degree);
            degree--;
        }
        result *= Math.pow(2, order);
        return result;
    }



    //Прямой код для перевода числа
    public static String directCode(int number){
        String tempereryBinSum = "";
        int preAns = number;
        if(number != 0) {
            while (Math.abs(number) != 1) {
                number /= 2;
                tempereryBinSum += Integer.toString(Math.abs(preAns) - Math.abs(number * 2));
                preAns = number;
            }
            tempereryBinSum += "1";
        }
        else{ tempereryBinSum += "0"; }
        String binSum = "";
        if(number >= 0){ binSum = "0"; }
        else{ binSum = "1"; }
        for(int i = 0; i < 15 - tempereryBinSum.length(); i++){ binSum += "0"; }
        binSum += createAnswer(tempereryBinSum);
        return binSum;
    }



    //Прямой код дробной части
    public static String DirectCodeForFractionalPart(double number){
        String binSum = "";
        String makeDeegre = Double.toString(number);
        double degree = makeDeegre.length() - 2;
        if(number != 0){
            int i = 0;
            while(i != SIXTEEN){
                number *= 2;
                if(number >= Math.pow(10, degree)){
                    binSum += "1";
                    number = number - Math.pow(10, degree);
                }
                else{ binSum += "0"; }
                i++;
            }
        }
        return binSum;
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



    //Функция для нахождения суммы
    public static String sum(String x1, String x2){
        String binSum = "0";
        if(!defineSignOfSumAndDiff(x1, x2).equals("0")){ binSum.equals("1"); }
        String tempereryBinSum = sumBackOrder(x1, x2);
        binSum += createAnswer(tempereryBinSum);
        return binSum;
    }



    //Сумма чисел
    public static String sumBackOrder(String x1, String x2){
        String binSum = "";
        boolean flag = false;
        for(int i = SIXTEEN - 1; i > 0; i--){
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



    //Сумма дробных частей
    public static String[] fractionalSum(String[] numbers){
        String x1 = numbers[1];
        String x2 = numbers[2];
        String binNum = "";
        long numb = fromStringToLong(numbers[3]);
        String[] items = sumOfMantis(x1, x2, numb);
        binNum += createAnswer(items[0]);
        String[] result = new String[2];
        numb = fromStringToLong(items[1]);
        if(numb > 1) {
            result[0] = sum(SMALL_ZERO + numbers[0], ONE);
            result[0] = result[0].substring(EIGHT, SIXTEEN);
            result[1] = Long.toString(numb%2) + binNum;
            result[1] = result[1].substring(0, result[1].length() - 1);
        }
        else
        {
            result[0] = numbers[0];
            result[1] = binNum;
        }
        return result;
    }



    //Сумма мантисс без проверки на переполнение
    public static String[] sumOfMantis(String x1, String x2, long numb){
        String binSum = "";
        boolean flag = false;
        for(int i = MANTISS_SIZE - 1; i >= 0; i--){
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
        if(flag){ numb++; }
        String[] res = {binSum, Long.toString(numb)};
        return res;
    }



    //Нахождение суммы с отрицательными числами
    public static String difference(String x1, String x2){
        String binSum="";
        if(comparison(x1, x2) && x1.charAt(0) == '0' && x2.charAt(0) == '1'){
            binSum = sum(x1, addCode(x2));
            binSum = differenceResult(binSum);
            return "0" + binSum;
        } else if (!comparison(x1, x2) && x1.charAt(0) == '0' && x2.charAt(0) == '1') {
            binSum = sum(x1, backCode(x2));
            binSum = backCode(binSum);
            binSum = differenceResult(binSum);
            return "1" + binSum;
        } else if (x1.charAt(0) == '1' && x2.charAt(0) == '1') {
            binSum = sum(x1, x2);
            binSum = differenceResult(binSum);
            return "1" + binSum;
        } else { binSum = NumZero(x1, x2); }
        return binSum;
    }



    //Проверка на нули
    public static String NumZero(String x1, String x2){
        if(findIntegerPart(x1).equals("0") && !findIntegerPart(x2).equals("0")){ return x2; }
        else if(!findIntegerPart(x1).equals("0") && findIntegerPart(x2).equals("0")){ return x1; }
        else if(findIntegerPart(x1).equals("0") && findIntegerPart(x2).equals("0")){ return BIG_ZERO; }
        return "";
    }



    //Функция для определения результата разности
    public static String differenceResult(String binNum){
        StringBuilder tempereryBinSum = new StringBuilder(binNum);
        tempereryBinSum.setCharAt(0, '0');
        binNum = tempereryBinSum.substring(1, tempereryBinSum.length());
        return binNum;
    }



    //Функция для нахождения произведения двоичных чисел
    public static String product(String x1, String x2){
        String sign = defineSignOfProdAndDiv(x1, x2);
        String integerPartX1 = findIntegerPart(x1);
        String integerPartX2 = findIntegerPart(x2);
        ArrayList<String> components = new ArrayList<>();
        String itemOfComponent = "";
        components = componentList(components, integerPartX1, integerPartX2);
        for( int i = 0; i < components.size(); i++){
            for(int j = 0; j < SIXTEEN - components.get(i).length()-i; j++){ itemOfComponent += "0"; }
            itemOfComponent += components.get(i);
            components.set(i, itemOfComponent);
            itemOfComponent = "";
        }
        String binNum = productResult(components);
        return sign + binNum.substring(1);
    }



    //Функция для определения знака произведения и деления
    public static String defineSignOfProdAndDiv(String binX1, String binX2){
        if(findIntegerPart(binX1).equals("0") || findIntegerPart(binX2).equals("0")){ return "0"; }
        else {
            if ((binX1.charAt(0) == '0' && binX2.charAt(0) == '0') || (binX1.charAt(0) == '1' && binX2.charAt(0) == '1')) { return "0"; }
            if ((binX1.charAt(0) == '1' && binX2.charAt(0) == '0') || (binX1.charAt(0) == '0' && binX2.charAt(0) == '1')) { return "1"; }
        }
        return "";
    }



    //Функция для определения слагаемых, с помощью которых будет найдено данное произведение
    public static ArrayList<String> componentList(ArrayList<String> components, String integerPartX1, String integerPart2){
        String itemOfProduct = "";
        for(int i = integerPart2.length() - 1; i >= 0; i--){
            for(int j = integerPartX1.length() - 1; j >= 0; j--){
                if((integerPart2.charAt(i) == '0' &&integerPartX1.charAt(j) == '0') || (integerPart2.charAt(i) == '0' &&
                        integerPartX1.charAt(j) == '1') || (integerPart2.charAt(i) == '1' &&integerPartX1.charAt(j) == '0')){
                    itemOfProduct += "0";
                }
                if(integerPart2.charAt(i) == '1' &&integerPartX1.charAt(j) == '1'){ itemOfProduct += "1"; }
            }
            components.add(createAnswer(itemOfProduct));
            itemOfProduct = "";
        }
        return components;
    }



    //Функция для нахождения результата произведения
    public static String productResult(ArrayList<String> components){
        String zero = "0";
        String binNum = components.get(0);
        for(int i = 1; i < components.size(); i++){
            if(i > 1){ zero += '0'; }
            String newResult = components.get(i) + zero;
            String str = sum(newResult, binNum);
            binNum = str;
        }
        return binNum;
    }



    //Функция для нахождения частного двоичных чисел
    public static String division(String BinX1, String BinX2){
        String sign = defineSignOfProdAndDiv(BinX1, BinX2);
        String divided = findIntegerPart(BinX1);
        String divisor = findIntegerPart(BinX2);
        String binNum = sign;
        String integerPart = divisionResult(divided, divisor);
        for(int i = 0; i < SIXTEEN - 1 - integerPart.length(); i++){ binNum += "0"; }
        binNum += integerPart;
        return binNum;
    }



    //Нахождение целой части при делении
    public static String divisionResult(String divided, String divisor){
        String binNum = "";
        String itemOfDivision = "";
        boolean flag = false;
        for(int i = 0; i < divided.length(); i++){
            if(itemOfDivision.length() != divisor.length()){
                itemOfDivision += divided.charAt(i);
                if(fromStringToLong(itemOfDivision) >= fromStringToLong(divisor)){
                    itemOfDivision = Long.toString(fromStringToLong(itemOfDivision) - fromStringToLong(divisor));
                    binNum += "1";
                    if(itemOfDivision.charAt(0) == '0'){ itemOfDivision = ""; }
                    flag = true;
                }
                else if(flag){ binNum += "0"; }
            }
            else{
                itemOfDivision += divided.charAt(i);
                if(fromStringToLong(itemOfDivision) >= fromStringToLong(divisor)){
                    itemOfDivision = Long.toString(fromStringToLong(itemOfDivision) - fromStringToLong(divisor));
                    binNum += "1";
                    if(itemOfDivision.charAt(0) == '0'){ itemOfDivision = ""; }
                    flag = true;
                }
            }
        }
        return binNum;
    }



    //Перевод результата математической операции
    public static String createAnswer(String tempereryBinNum){
        StringBuilder newTempereryBinNum = new StringBuilder(tempereryBinNum);
        newTempereryBinNum.reverse();
        String binNum = "" + newTempereryBinNum;
        return binNum;
    }



    //Сравнение чисел в двоичной системе счисления
    public static boolean comparison(String x1, String x2){
        String integerPartX1 = findIntegerPart(x1);
        String integerPartX2 = findIntegerPart(x2);
        long num1 = fromStringToLong(integerPartX1);
        long num2 = fromStringToLong(integerPartX2);
        return num1 >= num2;
    }



    //Преобразование строки в число
    public static long fromStringToLong(String integerPart){
        return Long.parseLong(integerPart);
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



    //Нахождение модуля дробной части числа в двоичной системе
    public static String findDecimicalIntegerPart(String binNum){
        String integerPart="";
        for(int i = binNum.length() - 1; i >= 0; i--){
            if(binNum.charAt(i) == '1'){
                for(int j = i; j >= 0; j--){ integerPart += binNum.charAt(j); }
                break;
            }
        }
        if(integerPart.equals("")){ integerPart = "0"; }
        return createAnswer(integerPart);
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



    //Функция для перевода из двоичной системы в десятичную целой части
    public static int transFromBinToDecInt(String binNum){
        String integerPartBinNum = "";
        integerPartBinNum += findIntegerPart(binNum);
        integerPartBinNum = createAnswer(integerPartBinNum);
        int integerPart = 0;
        for(int i = integerPartBinNum.length() - 1; i >= 0; i--){
            String num = "";
            num += integerPartBinNum.charAt(i);
            integerPart += Math.pow(2, i) * Integer.parseInt(num);
        }
        if(binNum.charAt(0) == '0'){ return integerPart; }
        else{ return -integerPart; }
    }
}