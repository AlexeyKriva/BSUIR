/////////////////////////////////
// Лабораторная работа №1 по дисциплине ЛОИС
// Выполнена студентами группы 121702 БГУИР Кривецкий А.Э., Костюков В.С., Кулешов И.В
// Реализация прямого нечёткого вывода, используя импликацию Лукасевича
// 09.10.2023

import java.util.*;

public class Premise {
    //Имя посылки
    private String name;
    //Определение множества
    private ArrayList<Pair> pairs;
    private static int amount = 0;
    //Английский алфавит
    public static final String[] ALPHABET = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public Premise(String name, ArrayList<Pair> pairs) {
        this.name = name;
        this.pairs = pairs;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Pair> getPairs() {
        return pairs;
    }

    //Получение случайной посылки и импликация Лукасевича с двумя посылками
    public static void getPremises() {
        amount = WorkWithMatrix.premises.size();
        //Цикл для прохождения импликаций Лукасевича
        int index1 = 0;
        int index2 = 0;
        while (index1 < WorkWithMatrix.premises.size()) {
            WorkWithMatrix.matrix.clear();
            //Получение случайной посылки
            Premise premise = WorkWithMatrix.premises.get(index1);
            //Первое множество пустое
            if (premise.getPairs().size() == 0) {
                index1++;
                continue;
            }
            //Получение случайного следствия
            Consequence consequence = WorkWithMatrix.consequences.get(index2);
            int i1 = 0;
            int i2 = 0;
            //Нахождения индекса множеств для следствия
            for (int j = 0; j < WorkWithMatrix.premises.size(); j++) {
                if (WorkWithMatrix.premises.get(j).getName().equals(consequence.getNameA()))
                    i1 = j;
                else if (WorkWithMatrix.premises.get(j).getName().equals(consequence.getNameB()))
                    i2 = j;
            }
            //Два множества пустые
            if (WorkWithMatrix.premises.get(i1).getPairs().size() == 0 && WorkWithMatrix.premises.get(i2).getPairs().size() == 0) {
                index2++;
                continue;
            }
            //Первое множество пустое
            if (WorkWithMatrix.premises.get(i1).getPairs().size() == 0) {
                index2++;
                continue;
            }
            //Второе множество пустое
            if (WorkWithMatrix.premises.get(i2).getPairs().size() == 0) {
                index2++;
                continue;
            }
            if (WorkWithMatrix.premises.get(i1).getPairs().size() != WorkWithMatrix.premises.get(i2).getPairs().size() ||
                    WorkWithMatrix.premises.get(i2).getPairs().size() != premise.getPairs().size() ||
                    WorkWithMatrix.premises.get(i1).getPairs().size() != premise.getPairs().size()) {
                index2++;
                continue;
            }
            System.out.println("___________________________________________________________");
            //Вывод нашего следствия
            System.out.println("\nПравило: " + consequence.getNameA() + "->" + consequence.getNameB());
            //Вывод посылки
            System.out.println(premise.getName() + " - является посылкой");
            System.out.println(premise.getPairs() + "\n");
            //Вызов импликации Лукасевича
            WorkWithMatrix.lukashevichsImplication(i1, i2);
            //Нахождение новой посылки
            findResult(premise, i1, i2);
            index2++;
            if (index2 >= WorkWithMatrix.consequences.size()) {
                index1++;
                index2 = 0;
            }
        }
    }

    //Нахождение новой посылки
    public static void findResult(Premise premise, int i1, int i2) {
        //Новая посылка
        ArrayList<Pair> result = new ArrayList<>();
        //Будет показывать тип не поглощаемой переменной
        boolean key = true;
        //Определение, есть ли совпадения в переменных
        if (!premise.getPairs().get(0).getName().equals(WorkWithMatrix.premises.get(i1).getPairs().get(0).getName()) &&
                !premise.getPairs().get(0).getName().equals(WorkWithMatrix.premises.get(i2).getPairs().get(0).getName()) &&
                !WorkWithMatrix.premises.get(i1).getPairs().get(0).getName().equals(WorkWithMatrix.premises.get(i2).getPairs().get(0).getName())) {
            System.out.println("Все посылки имею разные элементы");
            return;
        }
        //Определение совпадения в посылках
        for (int i = 0; i < WorkWithMatrix.premises.get(i1).getPairs().size(); i++){
            for (int j = 0; j < WorkWithMatrix.premises.get(i2).getPairs().size(); j++){
                if (WorkWithMatrix.premises.get(i1).getPairs().get(i).getName().equals(WorkWithMatrix.premises.get(i2).getPairs().get(j).getName())){
                    System.out.println("В посылках есть одинаковые элементы");
                    return;
                }
            }
        }
        //Определения типа переменной
        if (premise.getPairs().get(0).getName().equals(WorkWithMatrix.premises.get(i1).getPairs().get(0).getName()))
            key = false;
        if (premise.getPairs().get(0).getName().equals(WorkWithMatrix.premises.get(i2).getPairs().get(0).getName()))
            key = true;
        //Результат конъюнкции матриц
        ArrayList<ArrayList<Float>> tempMatrix = new ArrayList<>();
        //Проход по всем элементам посылки
        for (int i = 0; i < premise.getPairs().size(); i++) {
            tempMatrix.add(new ArrayList<>());
            //Вывод текущего элемента
            System.out.print(premise.getPairs().get(i));
            //Вывод t-нормы
            if (premise.getPairs().size() / 2 - 1 == i)
                System.out.print("   /\\    ");
                //Вывод пробела
            else
                System.out.print("         ");
            //Определения t-нормы и вывода её
            for (int j = 0; j < WorkWithMatrix.matrix.get(i).size(); j++) {
                tempMatrix.get(i).add(Math.max(premise.getPairs().get(i).getNumber() +
                                WorkWithMatrix.matrix.get(i).get(j).getNumber() - 1, 0));
                System.out.print(String.format("%.1f", WorkWithMatrix.matrix.get(i).get(j).getNumber()) + " ");
            }
            //Вывод равно
            if (premise.getPairs().size() / 2 - 1 == i)
                System.out.print("    =    ");
                //Вывод пробела
            else
                System.out.print("         ");
            //Вывод полученной строки в матрице
            for (int j = 0; j < tempMatrix.get(i).size(); j++) {
                System.out.print(String.format("%.1f", tempMatrix.get(i).get(j)) + " ");
            }
            System.out.println();
        }
        System.out.println();
        //Получение нужной посылки
        for (int i = 0; i < tempMatrix.size(); i++) {
            //Новый элемент в посылке
            float max = tempMatrix.get(0).get(i);
            //Определение максимального элемента в столбце
            for (int j = 1; j < tempMatrix.get(i).size(); j++)
                max = Math.max(max, tempMatrix.get(j).get(i));
            //Определение, какая переменная не самоуничтожилась, та и будет в новой посылке
            if (key)
                result.add(new Pair(WorkWithMatrix.premises.get(i1).getPairs().get(i).getName(), max));
            else
                result.add(new Pair(WorkWithMatrix.premises.get(i2).getPairs().get(i).getName(), max));
        }
        //Название посылки
        String symbol = "";
        while (true) {
            //Если же мы перебрали уже все латинские символы
            if (amount >= 26){
                Random random = new Random();
                //Пометка, есть ли уже такое название у посылки
                boolean flag = true;
                while (true) {
                    flag = true;
                    //Добавляем случайный символ к названию
                    symbol += ALPHABET[random.nextInt(26)];
                    //Проверяем, есть ли у нас такой элемент уже
                    for (int i = 0; i < WorkWithMatrix.premises.size(); i++) {
                        //Если нашли такой элемент, то идём дальше
                        if (symbol.equals(WorkWithMatrix.premises.get(i).getName())) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag)
                        break;
                }
                if (flag)
                    break;
            } else {
                //Пометка, есть ли уже такое название у посылки
                boolean flag = true;
                //Перебор алфавита
                for (int i = 0; i < ALPHABET.length; i++) {
                    flag = true;
                    //Перебор названий посылок
                    for (int j = 0; j < WorkWithMatrix.premises.size(); j++) {
                        //Если уже нашли такое название, то мы выходим
                        if (ALPHABET[i].equals(WorkWithMatrix.premises.get(j).getName())) {
                            flag = false;
                            break;
                        }
                    }
                    //Если такого названия нет, то мы добавляем его
                    if (flag) {
                        symbol += ALPHABET[i];
                        break;
                    }
                }
                //Если мы уже назвали посылку, то мы выходим из цикла или же мы добавляем случайную букву
                if (!symbol.isEmpty() && flag)
                    break;
            }
        }
        //Получение ответа
        String answer = "Вывод: " + symbol + " = {" + result + "}";
        answer = answer.replaceAll("\\[", "");
        answer = answer.replaceAll("\\]", "");
        //Вывод ответа
        System.out.println(answer);
        //Проверка, есть ли уже такая посылка в базе знаний
        for (int i = 0; i < WorkWithMatrix.premises.size(); i++){
            //Количество совпадений
            int sovp = 0;
            for (int j = 0; j < result.size(); j++){
                if (WorkWithMatrix.premises.get(i).pairs.size() == result.size() && result.get(j).getName().equals(WorkWithMatrix.premises.get(i).pairs.get(j).getName()) && result.get(j).getNumber() == WorkWithMatrix.premises.get(i).pairs.get(j).getNumber())
                    sovp++;
                else
                    break;
            }
            if (sovp == result.size())
                return;
        }
        //Добавление новой посылки в нашу базу знаний
        WorkWithMatrix.premises.add(new Premise(symbol, result));
        amount++;
    }

    @Override
    public String toString() {
        return String.valueOf(pairs);
    }
}