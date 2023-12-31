/////////////////////////////////
// Лабораторная работа №1 по дисциплине ЛОИС
// Выполнена студентами группы 121702 БГУИР Кривецкий А.Э., Костюков В.С., Кулешов И.В
// Реализация прямого нечёткого вывода, используя импликацию Лукасевича
// 09.10.2023

import java.util.*;

public class WorkWithMatrix {
    //Список, который будет удобно хранить наши посылки
    public static ArrayList<Premise> premises = new ArrayList<>();
    //Список формул
    public static ArrayList<String> formulas = new ArrayList<>();
    //Список импликаций
    public static ArrayList<Consequence> consequences = new ArrayList<>();
    //Матрица результата импликации Лукасевича
    public static ArrayList<ArrayList<Matrix>> matrix = new ArrayList<>();

    //Создание списка для удобного представления посылок
    public static void createPremises() {
        //Перебираем строку
        for (int i = 0; i < formulas.size(); i++) {
            //Индекс строки
            int ind = 0;
            //Строка для названия посылки
            String element = "";
            //Считываем название посылки
            while (true) {
                if (formulas.get(i).charAt(ind) == '=')
                    break;
                element += formulas.get(i).charAt(ind);
                ind++;
            }
            ArrayList<Pair> pairs1 = new ArrayList<>();
            //Считываем переменную и степень принадлежности
            for (int j = ind + 2; j < formulas.get(i).length(); j++) {
                //Проверяем, если дошли до начала пары
                if (formulas.get(i).charAt(j) == '<') {
                    String var = "";
                    String prob = "";
                    //Считываем нашу переменную
                    for (int x = j + 1; x < formulas.get(i).length(); x++) {
                        if (formulas.get(i).charAt(x) == ',') {
                            //Считываем нашу степень принадлежности
                            for (int l = x + 1; l < x + 4; l++) {
                                prob += formulas.get(i).charAt(l);
                            }
                            break;
                        }
                        var += formulas.get(i).charAt(x);
                    }
                    //Добавляем новую пару
                    pairs1.add(new Pair(var, Float.parseFloat(prob)));
                }
            }
            //Добавляем новое множество
            premises.add(new Premise(element, pairs1));
        }
    }

    //Импликация Лукасевича
    public static void lukashevichsImplication(int i1, int i2) {
        //Первое множество
        ArrayList<Pair> set1 = premises.get(i1).getPairs();
        //Второе множество
        ArrayList<Pair> set2 = premises.get(i2).getPairs();
        //Производим импликацию Лукасевича
        for (int i = 0; i < set1.size(); i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < set2.size(); j++) {
                matrix.get(i).add(new Matrix(set1.get(i).getName(),
                        set2.get(j).getName(), Math.min(1 - set1.get(i).getNumber() + set2.get(j).getNumber(), 1)));
            }
        }
        System.out.println();
        //Вывод результата импликации
        for (int i = 0; i < set1.size(); i++) {
            for (int j = 0; j < set2.size(); j++) {
                System.out.print(matrix.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println();
    }
}