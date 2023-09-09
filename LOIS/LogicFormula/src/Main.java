/////////////////////////////////
// Лабораторная работа №1 по дисциплине ЛОИС
// Выполнена студентами группы 121702 БГУИР Костюков В.С., Кривецкий А.Э.
// Проверка ввода формулы сокращенного языка логики
// 27.03.2023
import java.util.*;

public class Main {
    public static final String SPEC_IMPLICATION = "->";
    public static final char IMPLICATION = '-';
    public static final char INVERSION = '!';
    public static final char EQUIVALENCE = '~';
    public static final String SPEC_CONJUNCTION = "/\\\\";
    public static final char CONJUNCTION = '*';
    public static final String SPEC_DISJUNCTION = "\\\\/";
    public static final char DISJUNCTION = '+';
    public static final char LEFT_BRACKET = '(';
    public static final char RIGHT_BRACKET = ')';
    public static final char SPACE = ' ';
    public static final char TRANSFER = '\n';
    public static final char[] ALL_OPERATIONS = {'(', '!', '*', '+', '-', '~', ')'};
    public static final char[] OPERATIONS = {'!', '*', '+', '-', '~'};
    public static final char ONE = '1';
    public static final char ZERO = '0';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Введите логическую формулу");
            //Ввод формулы
            String formula = scanner.nextLine();
            if (!findForbiddenSymbols(formula)) System.out.println("Формула неправильная");
            else {
                formula = newFormula(formula);
                if (!findBrackets(formula)) System.out.println("Формула неправильная");
                else if (!verificationOfTransactions(formula)) System.out.println("Формула неправильная");
                else System.out.println("Формула правильная");
            }
        }
    }

    //Упрощение формулы
    public static String newFormula(String formula){
        //Замена импликации
        formula = formula.replaceAll(SPEC_IMPLICATION, String.valueOf(IMPLICATION));
        //Замена конъюнкции
        formula = formula.replaceAll(SPEC_CONJUNCTION, String.valueOf(CONJUNCTION));
        //Замена дизъюнкции

        formula = formula.replaceAll(SPEC_DISJUNCTION, String.valueOf(DISJUNCTION));
        return formula;
    }

    //Подсчёт всех скобок
    public static boolean findBrackets(String formula){
        //Счётчик скобок
        int brackets = 0;
        //Счётчики идущих подряд скобок
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < formula.length(); i++){
            //Если находим открывающуюся скобку, то увеличиваем счётчик скобок и уменьшаем счётчик открывающихся скобок и операций
            if (formula.charAt(i) == LEFT_BRACKET){
                brackets++;
            }
            //Если находим открывающуюся скобку, то уменьшаем счётчик и начинаем идти назад
            else if (formula.charAt(i) == RIGHT_BRACKET){
                brackets--;
                //Число операций
                int operations = 0;
                //Количество правых скобок
                int rightBracket = 0;
                //Количество левых скобок
                int leftBracket = 0;
                for (int j = i; j >= 0; j--){
                    if (formula.charAt(j) == LEFT_BRACKET){
                        leftBracket++;
                        if (leftBracket == rightBracket){
                            if (operations != rightBracket) return false;
                            break;
                        }
                    }
                    else if (formula.charAt(j) == RIGHT_BRACKET){
                        rightBracket++;
                    }
                    //Если находим другой символ
                    else if (!Character.isUpperCase(formula.charAt(j)) && !Character.isDigit(formula.charAt(j))){
                        for (char operation: OPERATIONS) {
                            //Проверяем, принадлежит ли символ операции, если да, то увеличиваем счётчик открывающихся скобок и операций
                            if (formula.charAt(j) == operation){
                                operations++;
                            }
                        }
                    }
                }
            }
        }
        //Если количество открывающихся скобок и операций не равно, то выполняем содержимое условия
        return brackets == 0;
    }

    //Проверка на запрещённые символы
    public static boolean findForbiddenSymbols(String formula){
        for (int i = 0; i < formula.length(); i++){
            //Если нахлодим маленькую букву, пробел или же перенос строки
            if (Character.isLowerCase(formula.charAt(i)) || formula.charAt(i) == SPACE || formula.charAt(i) == TRANSFER) return false;
            //Если находим символ отличный от Большой буквы, цифры и скобок
            if (!Character.isUpperCase(formula.charAt(i)) && !Character.isDigit(formula.charAt(i)) && formula.charAt(i) != LEFT_BRACKET &&
            formula.charAt(i) != RIGHT_BRACKET){
                boolean key = true;
                //Если находим /
                if (formula.charAt(i) == '/'){
                    if (i == 0) return false;
                    for (int j = i + 1; j < formula.length(); j++){
                        key = false;
                        //Если не находим \, то предыдущий символ недопустимый
                        if (formula.charAt(j) != '\\') return false;
                            //Иначе символ допустимый
                        else{
                            i++;
                            break;
                        }
                    }
                }
                //Если находим \
                else if (formula.charAt(i) == '\\'){
                    if (i == 0) return false;
                    //Если не находим /, то предыдущий символ недопустимый
                    for (int j = i + 1; j < formula.length(); j++){
                        key = false;
                        if (formula.charAt(j) != '/') return false;
                            //Иначе символ допустимый
                        else{
                            i++;
                            break;
                        }
                    }
                }
                //Если находим -
                else if (formula.charAt(i) == '-'){
                    if (i == 0) return false;
                    //Если не находим >, то предыдущий символ недопустимый
                    for (int j = i + 1; j < formula.length(); j++){
                        key = false;
                        if (formula.charAt(j) != '>') return false;
                            //Иначе символ допустимый
                        else{
                            i++;
                            break;
                        }
                    }
                }
                //Если не находим ! или ~, то символ недопустимый
                else if (formula.charAt(i) != EQUIVALENCE && formula.charAt(i) != INVERSION) {
                    key = false;
                    return false;
                }
                //Если находим ! или ~ и данные символы стоят в начале, то формула неправильная
                else {
                    key = false;
                    if (i == 0) return false;
                }
                if (key) return false;
            }
            //Проверяем, является ли символ цифрой 0 или 1
            if (Character.isDigit(formula.charAt(i))){
                if (formula.charAt(i) != '0' && formula.charAt(i) != '1') return false;
            }
            //Проверяем, является ли символ большой буквой
            if (Character.isUpperCase(formula.charAt(i))){
                for (int j = i + 1; j < formula.length(); j++){
                    //Если находим цифру, то идём дальше
                    if (Character.isDigit(formula.charAt(j))) return false;
                    else break;
                }
            }
        }
        //Если формула пустая
        if (formula.length() == 0) return false;
        return true;
    }

    //Проверка операций
    public static boolean verificationOfTransactions(String formula){
        //Стек операций
        Stack<Character> operations = new Stack<>();
        //Подсчёт логических формул идущих подряд
        int variables = 0;
        for (int i = 0; i < formula.length(); i++){
            //Проверяем символ на то, является ли он заглавной буквой
            if (Character.isUpperCase(formula.charAt(i))){
                //Увеличиваем счётчик переменных
                variables++;
                int index = i;
                for (int j = i + 1; j < formula.length(); j++){
                    //Если две заглавные буквы идут подряд
                    if (Character.isUpperCase(formula.charAt(j))) return false;
                    //Если встречаем 0 сразу после заглавной буквы
                    if (j == index + 1 && formula.charAt(j) == ZERO) return false;
                    //Если находим (
                    if (formula.charAt(j) == LEFT_BRACKET) return false;
                    //Если находим цифру
                    if (Character.isDigit(formula.charAt(j))) return false;
                    else break;
                }
            }
            //Проверяем символ на то, является ли он цифрой 0 или 1
            else if (formula.charAt(i) == ONE || formula.charAt(i) == ZERO){
                //Увеличиваем счётчик переменных
                variables++;
                for (int j = i + 1; j < formula.length(); j++){
                    //Если две цифры 0 или 1 идут подряд
                    if (Character.isDigit(formula.charAt(j))) return false;
                    //Если находим (
                    if (formula.charAt(j) == LEFT_BRACKET) return false;
                    else break;
                }
            }
            //Проверяем символ на то, является ли он логической операцией
            else {
                //Если стек операций пуст, то добавляем туда операцию
                if (operations.isEmpty()) operations.push(formula.charAt(i));
                else {
                    //Ключ на нахождение двух идущих подряд логических операций
                    boolean key = false;
                    for (char operation: OPERATIONS){
                        //Если символ является операций кроме ( и )
                        if (formula.charAt(i) == operation){
                            for (char oper: OPERATIONS){
                                //Если вверху стека тоже находится логическая операция
                                if (operations.peek() == oper){
                                    //Если у нас идут две логические операции подряд
                                    if (variables != 1) key = true;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    //Если нашли две подряд идущие логические операции
                    if (key) return false;
                    //Проверяем символ на соответствие скобкам
                    if (operations.peek() == LEFT_BRACKET || operations.peek() == RIGHT_BRACKET){
                        for (char operation: OPERATIONS){
                            //Если после скобки сразу же идёт логическая операция
                            if (formula.charAt(i) == operation && formula.charAt(i) != INVERSION && variables != 1) return false;
                        }
                    }
                    //Проверка на следующие конструкции: "()" и ")("
                    if ((operations.peek() == LEFT_BRACKET && formula.charAt(i) == RIGHT_BRACKET) || (operations.peek() == RIGHT_BRACKET && formula.charAt(i) == LEFT_BRACKET)){
                        return false;
                    }
                    //Проверка на логическую операцию и идущую сразу же за ней закрывающую скобку
                    if ((operations.peek() != LEFT_BRACKET && operations.peek() != RIGHT_BRACKET) && formula.charAt(i) == RIGHT_BRACKET && variables != 1) return false;
                    if (operations.peek() == LEFT_BRACKET || operations.peek() == RIGHT_BRACKET){
                        for (char operation: OPERATIONS){
                            //Если после скобки сразу же идёт логическая операция
                            if (formula.charAt(i) == operation && formula.charAt(i) != INVERSION && variables != 1) return false;
                        }
                    }
                    //Проверка на инверсию и идущую за ней скобку
                    if (formula.charAt(i) == INVERSION){
                        for (int j = i + 1; j < formula.length(); j++){
                            //Если следующий символ - это )
                            if (formula.charAt(j) == RIGHT_BRACKET) return false;
                            else break;
                        }
                    }
                    //Добавление операции в стек
                    operations.add(formula.charAt(i));
                }
                //Обнуление счётчика логических формул
                if (formula.charAt(i) != LEFT_BRACKET && formula.charAt(i) != RIGHT_BRACKET && formula.charAt(i) != INVERSION) variables = 0;
            }
        }
        return true;
    }
}