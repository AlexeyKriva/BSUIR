/////////////////////////////////
// Лабораторная работа №1 по дисциплине ЛОИС
// Выполнена студентами группы 121702 БГУИР Кривецкий А.Э., Костюков В.С., Кулешов И.В
// Реализация прямого нечёткого вывода, используя импликацию Лукасевича
// 09.10.2023

import java.io.*;

public class Launch {
    //Функция, в которой вызываются остальные функции
    public static void solve() throws IOException {
        FileReader file = new FileReader("input.txt");
        BufferedReader br = new BufferedReader(file);
        //Текущий предикат
        String formula;
        while (!(formula = br.readLine()).equals("")) {
            System.out.print(formula);
            //Убирает все пробелы
            formula = Check.change(formula);
            //Проверка формулы на корректность
            if (Check.matchFormula(formula) && Check.checkSimilar(formula)) {
                System.out.println(" - формула корректна");
                WorkWithMatrix.formulas.add(formula);
            } else
                System.out.println(" - формула некорректна");
        }
        //Проверка количества посылок
        if (WorkWithMatrix.formulas.size() < 2) {
            System.out.println("У Вас только одна посылка!!!");
            return;
        }
        //Превращение посылок в удобный вид
        WorkWithMatrix.createPremises();
        //Следствие
        String consequence;
        while ((consequence = br.readLine()) != null) {
            //Проверка следствия на корректность
            if (Check.matchConsequence(consequence)) {
                System.out.println(consequence + " - следствие корректно");
                WorkWithMatrix.consequences.add(Check.getConsequence(consequence));
                Check.checkConsequence();
            } else
                System.out.println(consequence + " - следствие некорректно");
        }
        //Если нам не подошло следствие
        if (WorkWithMatrix.consequences.size() == 0) {
            System.out.println("У Вас нет следствий!!!");
            return;
        }
        //Определение новых посылок
        Premise.getPremises();
    }
}