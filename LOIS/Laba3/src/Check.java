/////////////////////////////////
// Лабораторная работа №1 по дисциплине ЛОИС
// Выполнена студентами группы 121702 БГУИР Кривецкий А.Э., Костюков В.С., Кулешов И.В
// Реализация прямого нечёткого вывода, используя импликацию Лукасевича
// 09.10.2023

import java.util.*;
import java.util.regex.*;

public class Check {
    //Регулярное выражение для проверки формулы
    private static final String REGEX = "^[A-Z]=\\{(<[a-z]+[0-9]+,(1\\.0|0\\.[0-9])>)*\\}$";
    private static final String REGEX1 = "^[A-Z]=\\{\\}$";
    private static final String REGEX2 = "^[A-Z](-|~)>[A-Z]$";

    //Замена всех пробелов, а также убирание запятых между парами
    public static String change(String formula) {
        formula = formula.replaceAll(" ", "");
        formula = replacement(formula);
        return formula;
    }

    //Убирание запятых между парами
    public static String replacement(String formula) {
        //Новая формула
        String newFormula = "";
        for (int i = 0; i < formula.length() - 1; i++) {
            //Если находим запятую, а после неё идёт знак меньше, то пропускаем
            if (formula.charAt(i) == ',' && formula.charAt(i + 1) == '<')
                continue;
            newFormula += formula.charAt(i);
        }
        //Если у нас строка имеет больше одного символа, то мы возвращаем её
        if (formula.length() != 0)
            return newFormula + formula.charAt(formula.length() - 1);
        return "";
    }

    //Проверка формулы на соответствие языку
    public static boolean matchFormula(String formula) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(formula);
        //Проверка назаполненное множество
        if (matcher.find())
            return true;
        Pattern pattern1 = Pattern.compile(REGEX1);
        Matcher matcher1 = pattern1.matcher(formula);
        //Проверка на пустое множество
        if (matcher1.find())
            return true;
        return false;
    }

    //Проверка следствия на соответствие языку
    public static boolean matchConsequence(String formula) {
        Pattern pattern = Pattern.compile(REGEX2);
        Matcher matcher = pattern.matcher(formula);
        //Проверка на правильность импликации
        if (matcher.find())
            return true;
        return false;
    }

    //Разделение следствия для удобного использования
    public static Consequence getConsequence(String formula){
        //Посылка
        String consequence1 = "";
        //Вывод
        String consequence2 = "";
        //Определение послыки или следствия
        boolean side = true;
        //Перебор следствия
        for (int i = 0; i < formula.length(); i++){
            //Если мы прошли посылку, то меняем считывание данных
            if (formula.charAt(i) == '-' || formula.charAt(i) == '~'){
                side = false;
                i++;
                continue;
            }
            //Определение посылки
            if (side)
                consequence1 += formula.charAt(i);
                //Определение следствия
            else
                consequence2 += formula.charAt(i);
        }
        return new Consequence(consequence1, consequence2);
    }

    //Проверка на то, есть ли у нас все посылки для следствия
    public static void checkConsequence(){
        //Переменная для определения количества посылок
        int accumulator = 0;
        //Проверка всех посылок
        for (int i = 0; i < WorkWithMatrix.premises.size(); i++){
            //Если нашли посылку
            if (WorkWithMatrix.premises.get(i).getName().equals(WorkWithMatrix.consequences.get(WorkWithMatrix.consequences.size() - 1).getNameA()) ||
                    WorkWithMatrix.premises.get(i).getName().equals(WorkWithMatrix.consequences.get(WorkWithMatrix.consequences.size() - 1).getNameB()))
                accumulator++;
            //Если есть данные посылки, то просто идём дальше
            if (accumulator == 2)
                return;
        }
        //Если не все посылки есть в базе знаний, то мы удаляем добавленное следствие
        WorkWithMatrix.consequences.remove(WorkWithMatrix.consequences.size() - 1);
    }

    //Удобная запись формул
    public static boolean checkSimilar(String formula){
        //Проверка токенов формулы
        ArrayList<String> tokens = new ArrayList<>();
        //Цикл для перебора формулы
        for (int i = 0; i < formula.length(); i++) {
            String str = "";
            //Если находим запятую, а после неё идёт знак меньше, то пропускаем
            if (formula.charAt(i) == '<') {
                //Цикл для считывания элемента
                for (int j = i + 1; j < formula.length(); j++) {
                    //Проверка на то, считали ли мы элемент
                    if (Character.isDigit(formula.charAt(j)) || formula.charAt(j) == ',')
                        break;
                    str += formula.charAt(j);
                }
                //Добавление элемента
                tokens.add(str);
            }
        }
        for(int i = 0; i < tokens.size() - 1; i++)
            if (!tokens.get(i).equals(tokens.get(i + 1)))
                return false;
        return true;
    }
}